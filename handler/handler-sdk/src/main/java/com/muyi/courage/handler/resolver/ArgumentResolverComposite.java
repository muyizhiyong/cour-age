package com.muyi.courage.handler.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 方法处理器参数解析
 */
@Slf4j
public class ArgumentResolverComposite implements ArgumentResolver {

	/**
	 * 在HandlerAdapter里初始化 argumentResolvers
	 */
	private final List<ArgumentResolver> argumentResolvers = new LinkedList<>();

	/**
	 * 参数类型和Resolver 的映射缓存
	 */
	private final Map<MethodParameter, ArgumentResolver> argumentResolverCache =
			new ConcurrentHashMap<>(256);

	public ArgumentResolverComposite() {
	}

	/**
	 * Add the given {@link ArgumentResolver}.
	 */
	public ArgumentResolverComposite addResolver(ArgumentResolver resolver) {
		this.argumentResolvers.add(resolver);
		return this;
	}

	/**
	 * Add the given {@link ArgumentResolver HandlerMethodArgumentResolvers}.
	 */
	public ArgumentResolverComposite addResolvers(@Nullable ArgumentResolver... resolvers) {
		if (resolvers != null) {
			Collections.addAll(this.argumentResolvers, resolvers);
		}
		return this;
	}

	/**
	 * Add the given {@link ArgumentResolver HandlerMethodArgumentResolvers}.
	 */
	public ArgumentResolverComposite addResolvers(
			@Nullable List<? extends ArgumentResolver> resolvers) {

		if (resolvers != null) {
			this.argumentResolvers.addAll(resolvers);
		}
		return this;
	}

	/**
	 * Return a read-only list with the contained resolvers, or an empty list.
	 */
	public List<ArgumentResolver> getResolvers() {
		return Collections.unmodifiableList(this.argumentResolvers);
	}

	/**
	 * Clear the list of configured resolvers.
	 */
	public void clear() {
		this.argumentResolvers.clear();
	}


	/**
	 * Whether the given {@linkplain MethodParameter method parameter} is
	 * supported by any registered {@link ArgumentResolver}.
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return this.getArgumentResolver(parameter) != null;
	}


	/**
	 * Iterate over registered {@link ArgumentResolver HandlerMethodArgumentResolvers}
	 * and invoke the one that supports it.
	 *
	 * @throws IllegalStateException if no suitable {@link ArgumentResolver} is found.
	 */
	@Override
	@Nullable
	public Object resolveArgument(MethodParameter parameter, Object arg) throws Exception {
		ArgumentResolver resolver = this.getArgumentResolver(parameter);
		if (resolver == null) {
			throw new IllegalArgumentException("Unsupported parameter type ["
					+ parameter.getParameterType().getName() + "]. supportsParameter should be called first.");
		} else {
			return resolver.resolveArgument(parameter, arg);
		}
	}

	/**
	 * Find a registered {@link ArgumentResolver} that supports
	 * the given method parameter.
	 */
	@Nullable
	private ArgumentResolver getArgumentResolver(MethodParameter parameter) {
		ArgumentResolver result = this.argumentResolverCache.get(parameter);
		if (result == null) {
			for (ArgumentResolver methodArgumentResolver : this.argumentResolvers) {
				if (methodArgumentResolver.supportsParameter(parameter)) {
					result = methodArgumentResolver;
					this.argumentResolverCache.put(parameter, result);
					break;
				}
			}
		}
		return result;
	}
}
