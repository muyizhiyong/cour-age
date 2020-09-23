package com.muyi.courage.handler.method;

import com.muyi.courage.handler.resolver.ArgumentResolver;
import com.muyi.courage.handler.resolver.ArgumentResolverComposite;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 一个可以执行的Java类方法的元数据对象

 */
public class InvocableHandlerMethod extends HandlerMethod {

	private static final Object[] EMPTY_ARGS = new Object[0];

	private ArgumentResolverComposite resolvers = new ArgumentResolverComposite();

	private ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();


	public InvocableHandlerMethod(HandlerMethod handlerMethod) {
		super(handlerMethod);
	}


	public InvocableHandlerMethod(Object bean, Method method) {
		super(bean, method);
	}


	/**
	 * Invoke the method after resolving its argument values in the context of the given request.
	 * <p>Argument values are commonly resolved through
	 * {@link ArgumentResolver HandlerMethodArgumentResolvers}.
	 * The {@code providedArgs} parameter however may supply argument values to be used directly,
	 * Provided argument values are checked before argument resolvers.
	 * <p>Delegates to {@link #getMethodArgumentValues} and calls {@link #doInvoke} with the
	 * resolved arguments.
	 *
	 * @param request      the current request
	 * @param providedArgs "given" arguments matched by type, not resolved
	 * @return the raw value returned by the invoked method
	 * @throws Exception raised if no suitable argument resolver can be found,
	 *                   or if the method raised an exception
	 * @see #getMethodArgumentValues
	 * @see #doInvoke
	 */
	@Nullable
	public Object invokeForRequest(Object request, Object[] providedArgs) throws Exception {
		Object[] args = this.getMethodArgumentValues(request, providedArgs);

		if (logger.isTraceEnabled()) {
			logger.trace("Arguments: " + Arrays.toString(args));
		}
		return doInvoke(args);
	}

	/**
	 * Get the method argument values for the current request, checking the provided
	 * argument values and falling back to the configured argument resolvers.
	 * <p>The resulting array will be passed into {@link #doInvoke}.
	 */
	protected Object[] getMethodArgumentValues(Object request, Object[] providedArgs) throws Exception {

		if (ObjectUtils.isEmpty(getMethodParameters())) {
			return EMPTY_ARGS;
		}
		MethodParameter[] parameters = getMethodParameters();
		Object[] args = new Object[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			MethodParameter parameter = parameters[i];
			parameter.initParameterNameDiscovery(this.parameterNameDiscoverer);
			// 如果参数跟方法的参数类型匹配，直接返回这个参数数据
			args[i] = findProvidedArgument(parameter, providedArgs[i]);
			if (args[i] != null) {
				continue;
			}
			// 判断是否有resolvers支持这个参数类型
			if (!this.resolvers.supportsParameter(parameter)) {
				throw new IllegalStateException(formatArgumentError(parameter, "No suitable resolver"));
			}
			// 遍历resolvers，找一个resolve来处理参数
			try {
				args[i] = this.resolvers.resolveArgument(parameter, providedArgs[i]);

			} catch (Exception ex) {
				// Leave stack trace for later, exception may actually be resolved and handled..
				if (logger.isDebugEnabled()) {
					String error = ex.getMessage();
					if (error != null && !error.contains(parameter.getExecutable().toGenericString())) {
						logger.debug(formatArgumentError(parameter, error));
					}
				}
				throw ex;
			}
		}
		return args;
	}

	/**
	 * Set {@link ArgumentResolver HandlerMethodArgumentResolvers} to use to use for resolving method argument values.
	 */
	public void setHandlerMethodArgumentResolvers(ArgumentResolverComposite argumentResolvers) {
		this.resolvers = argumentResolvers;
	}

	/**
	 * Set the ParameterNameDiscoverer for resolving parameter names when needed
	 * (e.g. default request attribute name).
	 * <p>Default is a {@link DefaultParameterNameDiscoverer}.
	 */
	public void setParameterNameDiscoverer(ParameterNameDiscoverer parameterNameDiscoverer) {
		this.parameterNameDiscoverer = parameterNameDiscoverer;
	}


	/**
	 * Invoke the handler method with the given argument values.
	 */
	@Nullable
	protected Object doInvoke(Object[] args) throws Exception {
		ReflectionUtils.makeAccessible(getBridgedMethod());
		try {
			return getBridgedMethod().invoke(getBean(), args);
		} catch (IllegalArgumentException ex) {
			assertTargetBean(getBridgedMethod(), getBean(), new Object[]{args});
			String text = (ex.getMessage() != null ? ex.getMessage() : "Illegal argument");
			throw new IllegalStateException(formatInvokeError(text, new Object[]{args}), ex);
		} catch (InvocationTargetException ex) {
			// Unwrap for HandlerExceptionResolvers ...
			Throwable targetException = ex.getTargetException();
			if (targetException instanceof RuntimeException) {
				throw (RuntimeException) targetException;
			} else if (targetException instanceof Error) {
				throw (Error) targetException;
			} else if (targetException instanceof Exception) {
				throw (Exception) targetException;
			} else {
				throw new IllegalStateException(formatInvokeError("Invocation failure", new Object[]{args}), targetException);
			}
		}
	}

}
