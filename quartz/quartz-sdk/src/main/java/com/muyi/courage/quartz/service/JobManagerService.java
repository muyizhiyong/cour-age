package com.muyi.courage.quartz.service;

import org.quartz.SchedulerException;

import java.util.Map;


public interface JobManagerService {
	void addJob(String jobName, String group, String cron, String objName, Map<String, Object> param) throws Exception;

	void delJob(String jobName, String group) throws SchedulerException;

	void updateJob(String oldJobName, String jobName, String group, String cron, String objName, Map<String, Object> param) throws SchedulerException, Exception;

	void executeJob(String jobName, String group, Map<String, Object> param) throws Exception;
}
