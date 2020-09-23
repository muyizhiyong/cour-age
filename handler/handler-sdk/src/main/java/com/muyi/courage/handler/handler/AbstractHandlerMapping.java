package com.muyi.courage.handler.handler;

import com.muyi.courage.handler.method.HandlerMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodIntrospector;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 该实现定义了请求和HandlerMethod之间的映射。
 * 对于每个已注册的HandlerMethod对象，使用一个字符串`request key`映射到子类实例。
 */
@Slf4j
public abstract class AbstractHandlerMapping implements InitializingBean, HandlerMapping, BeanNameAware {


	private final Map<String, HandlerMethod> mappingLookup = new LinkedHashMap<>();
	private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	/**
	 * 默认不从父容器查找
	 */
	private boolean detectHandlerMethodsInAncestorContexts = false;

	@Nullable
	private String beanName;

	/**
	 * Detects handler methods at initialization.
	 *
	 * @see #initHandlerMethods
	 */
	@Override
	public void afterPropertiesSet() {
		initHandlerMethods();
	}

	/**
	 * Scan beans in the ApplicationContext, detect and register handler methods.
	 *
	 * @see #getCandidateBeanNames()
	 * @see #processCandidateBean
	 * @see #handlerMethodsInitialized
	 */
	protected void initHandlerMethods() {
		for (String beanName : getCandidateBeanNames()) {
			processCandidateBean(beanName);
		}
		handlerMethodsInitialized(getHandlerMethods());
	}

	/**
	 * Determine the names of candidate beans in the application context.
	 *
	 * @see #setDetectHandlerMethodsInAncestorContexts
	 * @see BeanFactoryUtils#beanNamesForTypeIncludingAncestors
	 * @since 5.1
	 */
	protected String[] getCandidateBeanNames() {
		return (this.detectHandlerMethodsInAncestorContexts ?
				BeanFactoryUtils.beanNamesForTypeIncludingAncestors(obtainApplicationContext(), Object.class) :
				obtainApplicationContext().getBeanNamesForType(Object.class));
	}

	/**
	 * Whether to detect handler methods in beans in ancestor ApplicationContexts.
	 * <p>Default is "false": Only beans in the current ApplicationContext are
	 * considered, i.e. only in the context that this HandlerMapping itself
	 * is defined in (typically the current DispatcherServlet's context).
	 * <p>Switch this flag on to detect handler beans in ancestor contexts
	 * (typically the Spring root WebApplicationContext) as well.
	 *
	 * @see #getCandidateBeanNames()
	 */
	public void setDetectHandlerMethodsInAncestorContexts(boolean detectHandlerMethodsInAncestorContexts) {
		this.detectHandlerMethodsInAncestorContexts = detectHandlerMethodsInAncestorContexts;
	}

	/**
	 * Determine the type of the specified candidate bean and call
	 * {@link #detectHandlerMethods} if identified as a handler type.
	 * <p>This implementation avoids bean creation through checking
	 * {@link org.springframework.beans.factory.BeanFactory#getType}
	 * and calling {@link #detectHandlerMethods} with the bean name.
	 *
	 * @param beanName the name of the candidate bean
	 * @see #isHandler
	 * @see #detectHandlerMethods
	 * @since 5.1
	 */
	protected void processCandidateBean(String beanName) {
		Class<?> beanType = null;
		try {
			beanType = obtainApplicationContext().getType(beanName);
		} catch (Throwable ex) {
			// An unresolvable bean type, probably from a lazy bean - let's ignore it.
			if (log.isTraceEnabled()) {
				log.trace("Could not resolve type for bean '" + beanName + "'", ex);
			}
		}
		if (beanType != null && isHandler(beanType)) {
			detectHandlerMethods(beanName);
		}
	}

	/**
	 * Obtain the ApplicationContext for actual use.
	 *
	 * @return the ApplicationContext (never {@code null})
	 * @throws IllegalStateException in case of no ApplicationContext set
	 */
	protected final ApplicationContext obtainApplicationContext() {
		ApplicationContext applicationContext = getApplicationContext();
		Assert.state(applicationContext != null, "No ApplicationContext");
		return applicationContext;
	}

	/**
	 * Return the ApplicationContext that this object is associated with.
	 *
	 * @throws IllegalStateException if not running in an ApplicationContext
	 */
	protected abstract ApplicationContext getApplicationContext();

	/**
	 * Whether the given type is a handler with handler methods.
	 *
	 * @param beanType the type of the bean being checked
	 * @return "true" if this a handler type, "false" otherwise.
	 */
	protected abstract boolean isHandler(Class<?> beanType);

	/**
	 * Look for handler methods in the specified handler bean.
	 *
	 * @param handler either a bean name or an actual handler instance
	 * @see #getMappingForMethod
	 */
	protected void detectHandlerMethods(Object handler) {
		Class<?> handlerType = (handler instanceof String ?
				obtainApplicationContext().getType((String) handler) : handler.getClass());

		if (handlerType != null) {
			Class<?> userType = ClassUtils.getUserClass(handlerType);
			// <方法反射对象，方法mapping字符串>
			Map<Method, String> methods = MethodIntrospector.selectMethods(
					userType, (MethodIntrospector.MetadataLookup<String>) method -> {
						try {
							return getMappingForMethod(method, userType);
						} catch (Throwable ex) {
							throw new IllegalStateException("Invalid mapping on handler class [" +
									userType.getName() + "]: " + method, ex);
						}
					});
			formatMappings(userType, methods);
			methods.forEach((method, mapping) -> {
				Method invocableMethod = AopUtils.selectInvocableMethod(method, userType);
				register(mapping, handler, invocableMethod);
			});
		}
	}

	private String formatMappings(Class<?> userType, Map<Method, String> methods) {
		String formattedType = Arrays.stream(ClassUtils.getPackageName(userType).split("\\."))
				.map(p -> p.substring(0, 1))
				.collect(Collectors.joining(".", "", "." + userType.getSimpleName()));
		Function<Method, String> methodFormatter = method -> Arrays.stream(method.getParameterTypes())
				.map(Class::getSimpleName)
				.collect(Collectors.joining(",", "(", ")"));
		return methods.entrySet().stream()
				.map(e -> {
					Method method = e.getKey();
					return e.getValue() + ": " + method.getName() + methodFormatter.apply(method);
				})
				.collect(Collectors.joining("\n\t", "\n\t" + formattedType + ":" + "\n\t", ""));
	}

	/**
	 * Provide the mapping for a handler method. A method for which no
	 * mapping can be provided is not a handler method.
	 *
	 * @param method      the method to provide a mapping for
	 * @param handlerType the handler type, possibly a sub-type of the method's
	 *                    declaring class
	 * @return the mapping, or {@code null} if the method is not mapped
	 */
	@Nullable
	protected abstract String getMappingForMethod(Method method, Class<?> handlerType);

	/**
	 * Invoked after all handler methods have been detected.
	 *
	 * @param handlerMethods a read-only map with handler methods and mappings.
	 */
	protected void handlerMethodsInitialized(Map<String, HandlerMethod> handlerMethods) {
		// Total includes detected mappings + explicit registrations via registerMapping
		int total = handlerMethods.size();
		if ((log.isTraceEnabled() && total == 0) || (log.isDebugEnabled() && total > 0)) {
			log.info(total + " mappings in " + formatMappingName());
		}
	}

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}

	protected String formatMappingName() {
		return this.beanName != null ? "'" + this.beanName + "'" : "<unknown>";
	}


	@Override
	public Object getHandler(String key) throws Exception {
		return getHandlerInternal(key);
	}


	/**
	 * Create the HandlerMethod instance.
	 * <p>
	 * you can implements ApplicationContextAware
	 * if (handler instanceof String) {
	 * return new HandlerMethod((String) handler,
	 * obtainApplicationContext().getAutowireCapableBeanFactory(), method);
	 * }
	 *
	 * @param handler either a bean name or an actual handler instance
	 * @param method  the target method
	 * @return the created HandlerMethod
	 */
	protected HandlerMethod createHandlerMethod(Object handler, Method method) {
		if (handler instanceof String) {
			return new HandlerMethod((String) handler,
					obtainApplicationContext().getAutowireCapableBeanFactory(), method);
		}
		return new HandlerMethod(handler, method);
	}

	/**
	 * Look up a handler method for the given request.
	 */
	protected HandlerMethod getHandlerInternal(String lookupPath) throws Exception {
		this.acquireReadLock();
		try {
			HandlerMethod handlerMethod = lookupHandlerMethod(lookupPath, null);
			return (handlerMethod != null ? handlerMethod.createWithResolvedBean() : null);
		} finally {
			this.releaseReadLock();
		}
	}

	/**
	 * Look up the best-matching handler method for the current request.
	 * If multiple matches are found, the best match is selected.
	 *
	 * @param lookupPath mapping lookup path within the current servlet mapping
	 * @param request    the current request
	 * @return the best-matching handler method, or {@code null} if no match
	 */
	@Nullable
	protected HandlerMethod lookupHandlerMethod(String lookupPath, Object request) throws Exception {
		return getHandlerMethods().get(lookupPath);
	}


	/**
	 * Return all mappings and handler methods. Not thread-safe.
	 *
	 * @see #acquireReadLock()
	 */
	public Map<String, HandlerMethod> getMappings() {
		return this.mappingLookup;
	}

	/**
	 * Acquire the read lock when using getMappings and getMappingsByUrl.
	 */
	public void acquireReadLock() {
		this.readWriteLock.readLock().lock();
	}

	/**
	 * Release the read lock after using getMappings and getMappingsByUrl.
	 */
	public void releaseReadLock() {
		this.readWriteLock.readLock().unlock();
	}

	/**
	 * Return a (read-only) map with all mappings and HandlerMethod's.
	 */
	public Map<String, HandlerMethod> getHandlerMethods() {
		this.acquireReadLock();
		try {
			return Collections.unmodifiableMap(this.getMappings());
		} finally {
			this.releaseReadLock();
		}
	}


	public void register(String mapping, Object handler, Method method) {
		this.readWriteLock.writeLock().lock();
		try {
			HandlerMethod handlerMethod = createHandlerMethod(handler, method);
			assertUniqueMethodMapping(handlerMethod, mapping);
			this.mappingLookup.put(mapping, handlerMethod);
		} finally {
			this.readWriteLock.writeLock().unlock();
		}
	}

	public void unregister(String mapping) {
		this.readWriteLock.writeLock().lock();
		try {
			this.mappingLookup.remove(mapping);
		} finally {
			this.readWriteLock.writeLock().unlock();
		}
	}

	private void assertUniqueMethodMapping(HandlerMethod newHandlerMethod, String mapping) {
		HandlerMethod handlerMethod = this.mappingLookup.get(mapping);
		if (handlerMethod != null && !handlerMethod.equals(newHandlerMethod)) {
			throw new IllegalStateException(
					"Ambiguous mapping. Cannot map '" + newHandlerMethod.getBean() + "' method \n" +
							newHandlerMethod + "\nto " + mapping + ": There is already '" +
							handlerMethod.getBean() + "' bean method\n" + handlerMethod + " mapped.");
		}
	}


}
