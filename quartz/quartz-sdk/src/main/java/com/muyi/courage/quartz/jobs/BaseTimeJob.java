package com.muyi.courage.quartz.jobs;

import com.muyi.courage.common.util.RetCodeEnum;
import com.muyi.courage.common.util.StringUtil;
import com.muyi.courage.quartz.service.TimeJobLogService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装定时任务的日志记录的模板类
 */
@Slf4j
public abstract class BaseTimeJob extends QuartzJobBean {

	@Resource
	TimeJobLogService timeJobLogService;

	/**
	 * 封装定时任务日志，如果任务需要自行决定日志记录情况，可以覆盖本方法
	 *
	 * @param jobExecutionContext 定时任务执行参数
	 * @throws JobExecutionException 定时任务执行异常
	 */
	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException{

		//新增定时任务执行记录
		int exeResult = 0;
		String exeDesp = "执行成功";
		JobDetail jobDetail = jobExecutionContext.getJobDetail();
		String jobTask = StringUtil.parseString(jobDetail.getKey().getName());
		Map<String, Object> retCreateLog = timeJobLogService.createTimeJobLog(jobTask);
		if (!RetCodeEnum.SUCCEED.getCode().equals(retCreateLog.get("retCode"))) {
			log.error("create taskLog for [" + jobTask + "] fail");
			return;
		}

		log.info("Job "+jobTask+" start");

		try {

			RetCodeEnum resultEnum = taskPerform(jobExecutionContext);
			//响应信息判断
			if(!RetCodeEnum.SUCCEED.getCode().equals(resultEnum.getCode())){
				exeResult = 1;
				exeDesp = "执行失败";
				log.error("Job "+jobTask+" failed");
			}

		} catch (Exception e) {
			exeResult = 2;
			String tmpExMsg = "[" + jobTask + "]:" + StringUtil.parseString(e.getMessage());
			exeDesp = tmpExMsg.length() > 256 ? tmpExMsg.substring(0, 256) : tmpExMsg;
			log.error(jobTask+" Job Fail: ", e);
		}

		log.info("Job "+jobTask+" end");

		//更新定时任务执行情况
		Map<String, Object> paramMap = new HashMap<>(3);
		paramMap.put("jobLogId", retCreateLog.get("jobLogId"));
		paramMap.put("exeResult", exeResult);
		paramMap.put("exeDesp", exeDesp);
		timeJobLogService.updateTimeJobLog(paramMap);

	}

	/**
	 * 执行任务的主要业务
	 *
	 * @param jobExecutionContext 定时任务执行参数
	 * @return 执行结果 retCode 执行码:0-成功 1-失败 2-发送异常 retMsg 执行信息
	 * @throws Exception 异常信息
	 */
	protected abstract RetCodeEnum taskPerform(JobExecutionContext jobExecutionContext) throws Exception;
}
