package com.muyi.courage.quartz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 加载配置文件中的配置
 */
@Component
@ConfigurationProperties("quartz.datasource")
public class PrivateQuartzDatasourceConfig {
    private String driver;
    private String url;
    private String user;
    private String password;
    private String maxConnections;

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMaxConnections() {
		return maxConnections;
	}

	public void setMaxConnections(String maxConnections) {
		this.maxConnections = maxConnections;
	}

	String getQuartzDatasourceQuartzDataSourceConnectionProviderClass() {
		return "com.muyi.courage.quartz.config.QuartzConnectionProvider";
	}

}
