package com.muyi.courage.handler.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;


public class JsonArgumentResolver extends AbstractArgumentResolver {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public <T> T readInternal(Object arg, MethodParameter parameter, Type paramType) throws Exception {

		Class<?> contextClass = parameter.getContainingClass();
		Class<T> targetClass = (paramType instanceof Class ? (Class<T>) paramType : null);
		if (targetClass == null) {
			ResolvableType resolvableType = ResolvableType.forMethodParameter(parameter);
			targetClass = (Class<T>) resolvableType.resolve();
		}

		T t = this.objectMapper.readValue(String.valueOf(arg), targetClass);
		return t;

	}


}
