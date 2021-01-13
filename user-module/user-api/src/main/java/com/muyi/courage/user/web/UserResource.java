package com.muyi.courage.auth.web;

import com.muyi.courage.common.dto.DTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 杨志勇
 * @date 2021-01-13 22:22
 */
@Api(value = "用户模块：用户管理接口", tags = {"用户模块：用户管理接口"})
public interface UserResource {

    @PostMapping("/user")
    @ApiOperation(value = "登录", notes = "登录")
    DTO authLogin(@RequestBody user);


}
