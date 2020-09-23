package com.muyi.courage.handler.handler;

import com.muyi.courage.handler.annotation.CourageMessageMapping;
import com.muyi.courage.handler.annotation.CourageMessageResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 针对来自消息队列的请求，HandlerMapping的实现
 *
 */
@Slf4j
@Component("messageHandlerMapping")
public class MessageHandlerMapping extends AbstractHandlerMapping implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public MessageHandlerMapping(ApplicationContext context) {
		this.applicationContext = context;
	}


	@Override
	protected boolean isHandler(Class<?> beanType) {
		return (AnnotatedElementUtils.hasAnnotation(beanType, CourageMessageMapping.class) ||
				AnnotatedElementUtils.hasAnnotation(beanType, CourageMessageResource.class));
	}

	/**
	 * 1.尝试获取此方法上的注解 和此方法的父接口上的注解
	 * 2.返回注解value值，或者返回null
	 * @param method      the method to provide a mapping for
	 * @param handlerType the handler type, possibly a sub-type of the method's
	 *                    declaring class
	 * @return String
	 */
	@Nullable
	@Override
	protected String getMappingForMethod(Method method, Class<?> handlerType) {
		CourageMessageMapping annotation = AnnotatedElementUtils.findMergedAnnotation(method, CourageMessageMapping.class);
		if (annotation == null) {
			return null;
		}
		return annotation.value()[0];
	}


	@Override
	public final ApplicationContext getApplicationContext() throws IllegalStateException {
		if (this.applicationContext == null) {
			throw new IllegalStateException(
					"ApplicationObjectSupport instance [" + this + "] does not run in an ApplicationContext");
		}
		return this.applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
