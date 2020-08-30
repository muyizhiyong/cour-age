package com.muyi.courage.quartz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 加载配置文件中的配置
 */
@Component
@ConfigurationProperties("quartz.scheduler")
public class PrivateQuartzSchedulerConfig {
    private String instanceName;
    private String instanceId;
    private String skipUpdateCheck;

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getSkipUpdateCheck() {
		return skipUpdateCheck;
	}

	public void setSkipUpdateCheck(String skipUpdateCheck) {
		this.skipUpdateCheck = skipUpdateCheck;
	}
}
