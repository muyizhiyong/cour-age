package com.muyi.courage.quartz.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;


public class SchedulerCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata annotatedTypeMetadata) {
		return Boolean.parseBoolean(context.getEnvironment().getProperty("quartz.enable"));
	}
}
