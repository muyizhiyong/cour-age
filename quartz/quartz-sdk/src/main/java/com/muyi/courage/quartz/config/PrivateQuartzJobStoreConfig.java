package com.muyi.courage.quartz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 加载配置文件中的配置
 */
@Component
@ConfigurationProperties("quartz.job-store")
public class PrivateQuartzJobStoreConfig {
    private String className;
    private String driverDelegateClass;
    private String datasource;
    private String tablePrefix;
    private String isClustered;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDriverDelegateClass() {
		return driverDelegateClass;
	}

	public void setDriverDelegateClass(String driverDelegateClass) {
		this.driverDelegateClass = driverDelegateClass;
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	public String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public String getIsClustered() {
		return isClustered;
	}

	public void setIsClustered(String isClustered) {
		this.isClustered = isClustered;
	}
}
