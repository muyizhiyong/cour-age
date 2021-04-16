package com.muyi.courage.push.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 杨志勇
 * @date 2020-09-23 20:19
 */
@Getter
@Setter
@ApiModel(value = "用户", description = "用户")
public class UserDTO {
    private static final long serialVersionUID = 1L;

    private String userNo;

    private String name;

    private String password;

    @ApiModelProperty(value = "status", example = "1")
    private Integer status;
}
