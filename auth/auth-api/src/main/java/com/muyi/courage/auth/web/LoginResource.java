package com.muyi.courage.auth.web;

import com.muyi.courage.auth.dto.LoginResultDTO;
import com.muyi.courage.auth.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 杨志勇
 * @date 2020-09-23 20:16
 */
@Api(value = "权限模块：系统登录接口", tags = {"权限模块：系统接口"})
public interface LoginResource {

    @PostMapping("/auth/login")
    @ApiOperation(value = "PC端，回调登录", notes = "PC端，回调登录")
    LoginResultDTO authLogin(@RequestBody UserDTO user);

}
