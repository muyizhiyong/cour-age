package com.muyi.courage.quartz.service;
import java.util.Map;


public interface TimeJobLogService {
	/**
	 * 插入定时任务执行记录
	 *
	 * @param jobTask 定时任务名
	 * @return 插入记录结果 记录编号
	 */
	Map<String, Object> createTimeJobLog(String jobTask);

	/**
	 * 更新定时任务执行记录
	 *
	 * @param paramMap 记录编号 执行结果 结果描述
	 * @return 更新结果
	 */
	Map<String, Object> updateTimeJobLog(Map<String, Object> paramMap);
}
