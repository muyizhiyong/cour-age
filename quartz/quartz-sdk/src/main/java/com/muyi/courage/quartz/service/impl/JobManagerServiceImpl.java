package com.muyi.courage.quartz.service.impl;

import com.muyi.courage.quartz.service.JobManagerService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class JobManagerServiceImpl implements JobManagerService {

	private final Scheduler scheduler;

	@Autowired
	public JobManagerServiceImpl(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	@Override
	public void addJob(String jobName, String group, String cron, String objName, Map<String, Object> param) throws Exception {
		if (checkExists(jobName, group)) {
			throw new Exception(String.format("Job已经存在, jobName:{%s},jobGroup:{%s}", jobName, group));
		}
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, group);
		JobKey jobKey = JobKey.jobKey(jobName, group);

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing();
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

		Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(objName);
		JobDataMap jobDataMap = new JobDataMap(param);
		JobDetail jobDetail = JobBuilder.newJob(clazz).setJobData(jobDataMap).withIdentity(jobKey).build();
		scheduler.scheduleJob(jobDetail, trigger);

	}

	@Override
	public void delJob(String jobName, String group) throws SchedulerException {
		if (checkExists(jobName, group)) {
			// 关闭定时任务
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, group);
			JobKey jobKey = JobKey.jobKey(jobName, group);
			scheduler.unscheduleJob(triggerKey);
			scheduler.deleteJob(jobKey);
		}
	}

	@Override
	public void updateJob(String oldJobName, String jobName, String group, String cron, String objName, Map<String, Object> param) throws Exception {
		if (checkExists(jobName, group)) {
			delJob(oldJobName, group);
			addJob(jobName, group, cron, objName, param);
		} else {
			addJob(jobName, group, cron, objName, param);
		}
	}

	@Override
	public void executeJob(String jobName, String group, Map<String, Object> param) throws Exception {
		JobKey jobKey=JobKey.jobKey(jobName,group);
		JobDataMap jobDataMap=new JobDataMap(param);
		scheduler.triggerJob(jobKey,jobDataMap);
	}

	private boolean checkExists(String jobName, String jobGroup) throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		return scheduler.checkExists(triggerKey);
	}
}
