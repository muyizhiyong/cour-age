package com.muyi.courage.handler.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;


/**
 * 使用在类上，表示这是一个可以被消息队列请求调用的资源
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface CourageMessageResource {
	String value() default "";
}
