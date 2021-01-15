package com.muyi.courage.user.web;

import com.muyi.courage.common.dto.DTO;
import com.muyi.courage.user.dto.UserDTO;
import com.muyi.courage.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 杨志勇
 * @date 2021-01-13 22:22
 */
@Slf4j
@RestController
public class UserResourceImpl implements  UserResource{

    @Resource
    UserService userService;

    @Override
    public DTO addUser(UserDTO userDTO) {
        return userService.addUser(userDTO);
    }
}
