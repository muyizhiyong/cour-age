package com.muyi.courage.quartz.service.impl;

import com.muyi.courage.common.util.RetCodeEnum;
import com.muyi.courage.common.util.StringUtil;
import com.muyi.courage.quartz.repository.TimeJobLogMapper;
import com.muyi.courage.quartz.service.TimeJobLogService;
import com.muyi.courage.quartz.domain.TimeJobLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service("timeJobLogService")
public class TimeJobLogServiceImpl implements TimeJobLogService {

	@Resource
	private TimeJobLogMapper timeJobLogMapper;

	@Override
	public Map<String, Object> createTimeJobLog(String jobTask) {
		Map<String, Object> retMap = new HashMap<>();
		retMap.put("retCode", RetCodeEnum.FAIL.getCode());
		retMap.put("retMsg", RetCodeEnum.FAIL.getTip());
		try {
			TimeJobLog timeJobLog = new TimeJobLog();
			timeJobLog.setLogicId(UUID.randomUUID().toString().replaceAll("\\-", ""));
			timeJobLog.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			timeJobLog.setResultDesc("执行中");
			timeJobLog.setJobType(0);
			timeJobLog.setJobResult(0);
			timeJobLog.setErrorLevel(0);

			timeJobLog.setJobName(jobTask);
			timeJobLogMapper.insertSelective(timeJobLog);
			retMap.put("jobLogId", timeJobLog.getLogicId());
			retMap.put("retCode", RetCodeEnum.SUCCEED.getCode());
			retMap.put("retMsg", "添加定时任务日志列表成功！");
		} catch (Exception e) {
			retMap.put("retCode", RetCodeEnum.EXCEPTION.getCode());
			retMap.put("retMsg", "添加定时任务日志列表异常!");
			log.error("addTimeJobLog Fail: ", e);
		}
		return retMap;
	}

	@Override
	public Map<String, Object> updateTimeJobLog(Map<String, Object> paramMap) {
		Map<String, Object> retMap = new HashMap<>();
		retMap.put("retCode", RetCodeEnum.FAIL.getCode());
		retMap.put("retMsg", RetCodeEnum.FAIL.getTip());
		log.debug("+++[updateTimeJobLog]+++");
		try {
			TimeJobLog timeJobLog = new TimeJobLog();
			timeJobLog.setLogicId(StringUtil.parseString(paramMap.get("jobLogId")));
			timeJobLog.setJobResult((Integer) paramMap.get("exeResult"));
			timeJobLog.setResultDesc(StringUtil.parseString(paramMap.get("exeDesp")));
			timeJobLog.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

			timeJobLogMapper.updateByPrimaryKey(timeJobLog);
			retMap.put("retCode", RetCodeEnum.SUCCEED.getCode());
			retMap.put("retMsg", "更新定时任务日志列表成功！");
		} catch (Exception e) {
			retMap.put("retCode", RetCodeEnum.EXCEPTION.getCode());
			retMap.put("retMsg", "更新定时任务日志列表异常!");
			log.error("updateTimeJobLog Fail: ", e);
		}
		return retMap;
	}

}
