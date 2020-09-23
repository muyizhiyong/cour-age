package com.muyi.courage.handler.adapter;

import com.muyi.courage.handler.annotation.CourageMessageMapping;
import com.muyi.courage.handler.annotation.CourageMessageResource;
import com.muyi.courage.handler.method.HandlerMethod;
import com.muyi.courage.handler.method.MessageInvocableHandlerMethod;
import com.muyi.courage.handler.resolver.ArgumentResolver;
import com.muyi.courage.handler.resolver.ArgumentResolverComposite;
import com.muyi.courage.handler.resolver.JsonArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component("messageHandlerAdapter")
public class MessageHandlerAdapter extends AbstractHandlerAdapter implements InitializingBean {

	@Nullable
	private ArgumentResolverComposite argumentResolvers;

	private ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

	@Override
	protected boolean supportsInternal(HandlerMethod handlerMethod) {
		CourageMessageResource annotation = handlerMethod.getBeanType().getAnnotation(CourageMessageResource.class);
		return annotation != null;
	}


	@Override
	protected Object handleInternal(Object request, HandlerMethod handlerMethod, Object[] args) throws Exception {
		return invokeHandlerMethod(request, handlerMethod, args);
	}

	/**
	 * Invoke the {@link CourageMessageMapping} handler method preparing a {@link Object}
	 * if view resolution is required.
	 * e.g. https://www.cnblogs.com/fangjian0423/p/springMVC-request-param-analysis.html
	 *
	 * @see #createInvocableHandlerMethod(HandlerMethod)
	 */
	@Nullable
	protected Object invokeHandlerMethod(Object request, HandlerMethod handlerMethod, Object[] args) throws Exception {

		try {

			MessageInvocableHandlerMethod invocableMethod = createInvocableHandlerMethod(handlerMethod);

			if (this.argumentResolvers != null) {
				invocableMethod.setHandlerMethodArgumentResolvers(this.argumentResolvers);
			}

			invocableMethod.setParameterNameDiscoverer(this.parameterNameDiscoverer);

			return invocableMethod.invokeAndHandle(request, args);

		} finally {
			// TODO something
		}
	}

	/**
	 * Set the ParameterNameDiscoverer to use for resolving method parameter names if needed
	 * (e.g. for default attribute names).
	 * <p>Default is a {@link DefaultParameterNameDiscoverer}.
	 */
	public void setParameterNameDiscoverer(ParameterNameDiscoverer parameterNameDiscoverer) {
		this.parameterNameDiscoverer = parameterNameDiscoverer;
	}


	/**
	 * Create a {@link MessageInvocableHandlerMethod} from the given {@link HandlerMethod} definition.
	 *
	 * @param handlerMethod the {@link HandlerMethod} definition
	 * @return the corresponding {@link MessageInvocableHandlerMethod} (or custom subclass thereof)
	 */
	protected MessageInvocableHandlerMethod createInvocableHandlerMethod(HandlerMethod handlerMethod) {
		return new MessageInvocableHandlerMethod(handlerMethod);
	}


	@Override
	public void afterPropertiesSet() {
		if (this.argumentResolvers == null) {
			List<ArgumentResolver> resolvers = getDefaultArgumentResolvers();
			this.argumentResolvers = new ArgumentResolverComposite().addResolvers(resolvers);
		}
	}


	/**
	 * Return the list of argument resolvers to use including built-in resolvers
	 */
	private List<ArgumentResolver> getDefaultArgumentResolvers() {
		List<ArgumentResolver> resolvers = new ArrayList<>();
		resolvers.add(new JsonArgumentResolver());
		return resolvers;
	}


}
