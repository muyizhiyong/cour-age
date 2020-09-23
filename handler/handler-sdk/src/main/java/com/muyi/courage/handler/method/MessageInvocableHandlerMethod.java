package com.muyi.courage.handler.method;

import java.lang.reflect.Method;

/**
 * 一个可以执行Message数据的Java类方法
 */
public class MessageInvocableHandlerMethod extends InvocableHandlerMethod {

	public MessageInvocableHandlerMethod(HandlerMethod handlerMethod) {
		super(handlerMethod);
	}

	public MessageInvocableHandlerMethod(Object bean, Method method) {
		super(bean, method);
	}

	/**
	 * Invoke the method and handle the return value through one of the
	 * configured {@link HandlerMethodReturnValueHandler HandlerMethodReturnValueHandlers}.
	 *
	 * @param request      the current request
	 * @param providedArgs the ModelAndViewContainer for this request
	 */
	public Object invokeAndHandle(Object request, Object... providedArgs) throws Exception {

		return invokeForRequest(request, providedArgs);
	}
}
