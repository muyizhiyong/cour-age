package com.muyi.courage.user.web;

import com.muyi.courage.common.dto.DTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 杨志勇
 * @date 2021-01-13 22:22
 */
@Api(value = "用户模块：用户管理接口", tags = {"用户模块：用户管理接口"})
public class UserResourceImpl implements  UserResource{


    @Override
    public DTO addUser(Map<String, Object> map) {
        return null;
    }
}
