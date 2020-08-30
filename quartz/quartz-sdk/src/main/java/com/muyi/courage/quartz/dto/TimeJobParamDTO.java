package com.muyi.courage.quartz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@ApiModel(value = "定时任务请求参数", description = "定时任务请求参数")
public class TimeJobParamDTO {
	private static final long serialVersionUID = 1L;

	//private int totalRow;
	//private int totalPage;
	@ApiModelProperty(value = "当前页（1表示第一页）")
	private int curPage;
	@ApiModelProperty(value = "每页记录数")
	private int pageSize;

	@ApiModelProperty(value = "任务名称（可模糊匹配）")
	private String jobNameQuery;
}
