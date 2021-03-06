package com.muyi.courage.common.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.muyi.courage.common.util.RetCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "响应数据传输对象", description = "响应数据传输对象")
public class DTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "响应信息编码")
	private String retCode;

	@ApiModelProperty(value = "响应信息")
	private String retMsg;


	public DTO() {
	}

	public DTO(RetCodeEnum e) {
		this.retCode = e.getCode();
		this.retMsg = e.getTip();
	}

	public DTO(String retCode, String retMsg) {
		this.retCode = retCode;
		this.retMsg = retMsg;
	}

	@ApiModelProperty(value = "响应指定信息", hidden = true)
	public void setResult(RetCodeEnum e) {
		this.retCode = e.getCode();
		this.retMsg = e.getTip();
	}


}
