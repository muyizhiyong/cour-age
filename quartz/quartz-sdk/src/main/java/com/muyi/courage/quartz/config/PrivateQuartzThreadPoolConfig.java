package com.muyi.courage.quartz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 加载配置文件中的配置
 */
@Component
@ConfigurationProperties("quartz.thread-pool")
public class PrivateQuartzThreadPoolConfig {
    private String className;
    private String threadCount;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(String threadCount) {
		this.threadCount = threadCount;
	}
}
