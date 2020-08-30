package com.muyi.courage.quartz.config;

import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;


@Configuration
@Conditional(SchedulerCondition.class)
public class QuartzConfiguration {

	private final SpringJobFactory springJobFactory;
	private final PrivateQuartzDatasourceConfig quartzDatasourceConfig;
	private final PrivateQuartzJobStoreConfig quartzJobStoreConfig;
	private final PrivateQuartzSchedulerConfig quartzSchedulerConfig;
	private final PrivateQuartzThreadPoolConfig quartzThreadPoolConfig;

	@Autowired
	public QuartzConfiguration(PrivateQuartzSchedulerConfig quartzSchedulerConfig, SpringJobFactory springJobFactory, PrivateQuartzDatasourceConfig quartzDatasourceConfig, PrivateQuartzJobStoreConfig quartzJobStoreConfig, PrivateQuartzThreadPoolConfig quartzThreadPoolConfig) {
		this.quartzSchedulerConfig = quartzSchedulerConfig;
		this.springJobFactory = springJobFactory;
		this.quartzDatasourceConfig = quartzDatasourceConfig;
		this.quartzJobStoreConfig = quartzJobStoreConfig;
		this.quartzThreadPoolConfig = quartzThreadPoolConfig;
	}

	@Bean(name = "SchedulerFactory")
	public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();

		factory.setStartupDelay(2);
		factory.setAutoStartup(true);
		factory.setOverwriteExistingJobs(true);
		factory.setJobFactory(springJobFactory);
		factory.setQuartzProperties(quartzProperties());
		return factory;
	}

	/*
	 * quartz初始化监听器
	 */
	@Bean
	public QuartzInitializerListener executorListener() {
		return new QuartzInitializerListener();
	}

	@Bean
	public Properties quartzProperties() throws IOException {
		Properties prop = new Properties();
		prop.put("quartz.scheduler.instanceName", quartzSchedulerConfig.getInstanceName());
		prop.put("org.quartz.scheduler.instanceId", quartzSchedulerConfig.getInstanceId());
		prop.put("org.quartz.scheduler.skipUpdateCheck", quartzSchedulerConfig.getSkipUpdateCheck());
		//prop.put("org.quartz.scheduler.jobFactory.class", zjQuartzConfig.getQuartzSchedulerJobFactoryClass());
		prop.put("org.quartz.jobStore.class", quartzJobStoreConfig.getClassName());
		prop.put("org.quartz.jobStore.driverDelegateClass", quartzJobStoreConfig.getDriverDelegateClass());
		prop.put("org.quartz.jobStore.dataSource", quartzJobStoreConfig.getDatasource());
		prop.put("org.quartz.jobStore.tablePrefix", quartzJobStoreConfig.getTablePrefix());
		prop.put("org.quartz.jobStore.isClustered", quartzJobStoreConfig.getIsClustered());
		prop.put("org.quartz.threadPool.class", quartzThreadPoolConfig.getClassName());
		prop.put("org.quartz.threadPool.threadCount", quartzThreadPoolConfig.getThreadCount());
		prop.put("org.quartz.dataSource.quartzDataSource.connectionProvider.class", quartzDatasourceConfig.getQuartzDatasourceQuartzDataSourceConnectionProviderClass());
		prop.put("org.quartz.dataSource.quartzDataSource.driver", quartzDatasourceConfig.getDriver());
		prop.put("org.quartz.dataSource.quartzDataSource.URL", quartzDatasourceConfig.getUrl());
		prop.put("org.quartz.dataSource.quartzDataSource.user", quartzDatasourceConfig.getUser());
		prop.put("org.quartz.dataSource.quartzDataSource.password", quartzDatasourceConfig.getPassword());
		prop.put("org.quartz.dataSource.quartzDataSource.maxConnections", quartzDatasourceConfig.getMaxConnections());

		return prop;
	}
}
