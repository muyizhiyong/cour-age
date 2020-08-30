package com.muyi.courage.quartz.config;

import com.zaxxer.hikari.HikariDataSource;
import org.quartz.SchedulerException;
import org.quartz.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接池的Quartz扩展类
 */
public class QuartzConnectionProvider implements ConnectionProvider {

	/*
	 * 常量配置，与quartz.properties文件的key保持一致(去掉前缀)，同时提供set方法，Quartz框架自动注入值。
	 */
	private String driver;
	private String URL;
	private String user;
	private String password;
	private int maxConnections;

	private HikariDataSource datasource;

	@Override
	public Connection getConnection() throws SQLException {
		return datasource.getConnection();
	}

	@Override
	public void shutdown() throws SQLException {
		datasource.close();
	}

	@Override
	public void initialize() throws SQLException {
		if (this.URL == null) {
			throw new SQLException("DBPool could not be created: DB URL cannot be null");
		}
		if (this.driver == null) {
			throw new SQLException("DBPool driver could not be created: DB driver class name cannot be null!");
		}
		if (this.maxConnections < 0) {
			throw new SQLException("DBPool maxConnectins could not be created: Max connections must be greater than zero!");
		}
		datasource = new HikariDataSource();
		try {
			datasource.setDriverClassName(this.driver);
		} catch (Exception e) {
			try {
				throw new SchedulerException("Problem setting driver class name on datasource: " + e.getMessage(), e);
			} catch (SchedulerException e1) {
				e1.printStackTrace();
			}
		}
		datasource.setJdbcUrl(this.URL);
		datasource.setUsername(this.user);
		datasource.setPassword(this.password);
		datasource.setMaximumPoolSize(this.maxConnections);
		datasource.setMaxLifetime(1);
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String URL) {
		this.URL = URL;
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

	public int getMaxConnections() {
		return maxConnections;
	}

	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	public HikariDataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(HikariDataSource datasource) {
		this.datasource = datasource;
	}

}
