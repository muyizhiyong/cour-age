package com.muyi.courage.handler.resolver;

import org.springframework.core.Conventions;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Optional;

/**
 * A base class for resolving method argument values by reading from the Object
 *
 * @author ygsama
 */
public abstract class AbstractArgumentResolver implements ArgumentResolver {


	@Override
	public boolean supportsParameter(MethodParameter var1) {
		return true;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, Object providedArg) throws Exception {
		parameter = parameter.nestedIfOptional();
		Object arg = readInternal(providedArg, parameter, parameter.getNestedGenericParameterType());
		String name = Conventions.getVariableNameForParameter(parameter);
		return adaptArgumentIfNecessary(arg, parameter);
	}

	/**
	 * Create the method argument value of the expected parameter type by
	 * reading from the given request.
	 *
	 * @param <T>       the expected type of the argument value to be created
	 * @param request   the current request
	 * @param parameter the method parameter descriptor (may be {@code null})
	 * @param paramType the type of the argument value to be created
	 * @return the created method argument value
	 * @throws IOException if the reading from the request fails
	 */
	public abstract <T> Object readInternal(Object request, MethodParameter parameter, Type paramType) throws Exception ;


	/**
	 * Adapt the given argument against the method parameter, if necessary.
	 *
	 * @param arg       the resolved argument
	 * @param parameter the method parameter descriptor
	 * @return the adapted argument, or the original resolved argument as-is
	 */
	@Nullable
	private Object adaptArgumentIfNecessary(@Nullable Object arg, MethodParameter parameter) {
		if (parameter.getParameterType() == Optional.class) {
			if (arg == null || (arg instanceof Collection && ((Collection<?>) arg).isEmpty()) ||
					(arg instanceof Object[] && ((Object[]) arg).length == 0)) {
				return Optional.empty();
			} else {
				return Optional.of(arg);
			}
		}
		return arg;
	}
}
