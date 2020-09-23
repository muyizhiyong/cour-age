package com.muyi.courage.handler.adapter;

import org.springframework.lang.Nullable;


public interface HandlerAdapter {

	/**
	 * Use the given handler to handle this request.
	 * The workflow that is required may vary widely.
	 *
	 * @param request current request
	 * @param handler handler to use. This object must have previously been passed
	 *                to the {@code supports} method of this interface, which must have
	 *                returned {@code true}.
	 * @param args    请求方法的参数
	 * @return a object
	 * @throws Exception in case of errors
	 */
	@Nullable
	Object handle(Object request, Object handler, Object[] args) throws Exception;


	/**
	 * Given a handler instance, return whether or not this {@code HandlerAdapter}
	 * can support it. Typical HandlerAdapters will base the decision on the handler
	 * type. HandlerAdapters will usually only support one handler type each.
	 * <p>A typical implementation:
	 * <p>{@code
	 * return (handler instanceof MyHandler);
	 * }
	 *
	 * @param handler handler object to check
	 * @return whether or not this object can use the given handler
	 */
	boolean supports(Object handler);
}
