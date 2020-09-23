package com.muyi.courage.handler.adapter;

import com.muyi.courage.handler.method.HandlerMethod;
import org.springframework.lang.Nullable;


public abstract class AbstractHandlerAdapter implements HandlerAdapter {


	/**
	 * This implementation expects the handler to be an {@link HandlerMethod}.
	 */
	@Override
	public Object handle(Object request, Object handler, Object[] args) throws Exception {
		return handleInternal(request, (HandlerMethod) handler, args);
	}

	/**
	 * Use the given handler method to handle the request.
	 *
	 * @param request       current request
	 * @param handlerMethod handler method to use. This object must have previously been passed to the
	 *                      {@link #supportsInternal(HandlerMethod)} this interface, which must have returned {@code true}.
	 * @return a ModelAndView object with the name of the view and the required model data,
	 * or {@code null} if the request has been handled directly
	 * @throws Exception in case of errors
	 */
	@Nullable
	protected abstract Object handleInternal(Object request, HandlerMethod handlerMethod, Object[] args) throws Exception;


	/**
	 * This implementation expects the handler to be an {@link HandlerMethod}.
	 *
	 * @param handler the handler instance to check
	 * @return whether or not this adapter can adapt the given handler
	 */
	@Override
	public final boolean supports(Object handler) {
		return (handler instanceof HandlerMethod && supportsInternal((HandlerMethod) handler));
	}

	/**
	 * Given a handler method, return whether or not this adapter can support it.
	 *
	 * @param handlerMethod the handler method to check
	 * @return whether or not this adapter can adapt the given method
	 */
	protected abstract boolean supportsInternal(HandlerMethod handlerMethod);


}
