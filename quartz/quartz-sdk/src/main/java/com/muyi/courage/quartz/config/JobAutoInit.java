package com.muyi.courage.quartz.config;

import com.alibaba.fastjson.JSONObject;
import com.muyi.courage.quartz.domain.TimeJobDO;
import com.muyi.courage.quartz.repository.TimeJobTableMapper;
import com.muyi.courage.quartz.service.JobManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
@Conditional(SchedulerCondition.class)
public class JobAutoInit implements ApplicationRunner {
	private static final String GROUP = "default";

	private TimeJobTableMapper timeJobTableMapper;
	private JobManagerService jobManagerService;

	public JobAutoInit(TimeJobTableMapper timeJobTableMapper, JobManagerService jobManagerService) {
		this.timeJobTableMapper = timeJobTableMapper;
		this.jobManagerService = jobManagerService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<TimeJobDO> timeJobDOS = this.timeJobTableMapper.selectAutoInit();
		for (TimeJobDO timeJobDO : timeJobDOS) {
			try {
				timeJobDO.setInit_flag(0);
				timeJobTableMapper.updateAutoInitByPrimaryKey(timeJobDO);
				log.info("init timejob task: {}", timeJobDO);
				if (timeJobDO.getJobState() == 1) {
					String jsonString = timeJobDO.getArguments();
					JSONObject jsonObject = JSONObject.parseObject(jsonString);
					this.jobManagerService.updateJob(timeJobDO.getJobName(), timeJobDO.getJobName(), GROUP, timeJobDO.getCron(), timeJobDO.getObjName(), jsonObject);
				} else {
					this.jobManagerService.delJob(timeJobDO.getJobName(), GROUP);
				}
				log.info("init timejob task successful!: {}", timeJobDO);
			} catch (Exception e) {
				log.error("init timejob task failed!: {}", timeJobDO);
			}

		}
	}
}
