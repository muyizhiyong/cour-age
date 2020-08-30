package com.muyi.courage.quartz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cwang
 */
@Data
@ApiModel(value = "定时任务对象", description = "定时任务对象")
public class TimeJobDTO {

	@ApiModelProperty(value = "id（添加时不用填，填写无效）")
	private String id;

	@ApiModelProperty(value = "触发器名称")
	private String trigName;

	@ApiModelProperty(value = "cron表达式")
	private String cron;

	@ApiModelProperty(value = "任务名称")
	private String jobName;

	@ApiModelProperty(value = "任务实现类")
	private String objName;

	@ApiModelProperty(value = "是否允许并发（添加时不用填，填写无效）")
	private Integer concurrent;

	@ApiModelProperty(value = "任务状态")
	private Integer jobState;

	@ApiModelProperty(value = "描述")
	private String desp;

	@ApiModelProperty(value = "任务执行参数")
	private String arguments;
}
