package com.muyi.courage.quartz.dto;

import com.muyi.courage.common.dto.DTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class CityInfoDTO extends DTO {

	@ApiModelProperty(value = "城市编号")
	private String cityNo;

	@ApiModelProperty(value = "城市名称")
	private String cityName;

	@ApiModelProperty(value = "省份")
	private String province;

	@ApiModelProperty(value = "获取天气对应的ID")
	private String weatherId;

	@ApiModelProperty(value = "备注")
	private String note;
}
