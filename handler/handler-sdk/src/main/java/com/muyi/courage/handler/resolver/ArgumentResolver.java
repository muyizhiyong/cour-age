package com.muyi.courage.handler.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;


/**
 * Strategy interface for resolving method parameters into argument values in
 * the context of a given request.
 */
public interface ArgumentResolver {

	/**
	 * Whether the given {@linkplain MethodParameter method parameter} is
	 * supported by this resolver.
	 *
	 * @param parameter the method parameter to check
	 * @return {@code true} if this resolver supports the supplied parameter;
	 * {@code false} otherwise
	 */
	boolean supportsParameter(MethodParameter parameter);

	/**
	 * Resolves a method parameter into an argument value from a given request.
	 *
	 * @param parameter the method parameter to resolve. This parameter must
	 *                  have previously been passed to {@link #supportsParameter} which must
	 *                  have returned {@code true}.
	 * @param args      the current request args
	 * @return the resolved argument value, or {@code null} if not resolvable
	 * @throws Exception in case of errors with the preparation of argument values
	 */
	@Nullable
	Object resolveArgument(MethodParameter parameter, Object args) throws Exception;
}
