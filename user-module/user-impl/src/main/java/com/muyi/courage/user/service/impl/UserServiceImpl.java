package com.muyi.courage.user.service.impl;

import com.muyi.courage.common.annotation.DBMaster;
import com.muyi.courage.common.dto.DTO;
import com.muyi.courage.common.util.RetCodeEnum;
import com.muyi.courage.user.domain.UserDO;
import com.muyi.courage.user.dto.UserDTO;
import com.muyi.courage.user.mapstruct.UserConverter;
import com.muyi.courage.user.po.UserPO;
import com.muyi.courage.user.repository.UserMapper;
import com.muyi.courage.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 杨志勇
 * @date 2021-01-15 13:54
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DTO addUser(UserDTO userDTO) {
        DTO dto = new DTO(RetCodeEnum.FAIL);

        //TODO check userDTO

        UserDO domain = new UserDO();
        domain.setUserName(userDTO.getUserName());
        domain.setName(userDTO.getName());
        domain.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()).replace("{bcrypt}", ""));
        domain.setStatus(1);

        if (userMapper.insert(domain) == 1) {
            dto.setResult(RetCodeEnum.SUCCEED);
            return dto;
        }
        return new DTO(RetCodeEnum.FAIL);
    }

//    @DBMaster
    @Override
    public UserDTO qryByName(String name) {
        UserDTO userDTO = new UserDTO();

        UserDO userDO = userMapper.selectByName(name);

        userDTO = UserConverter.INSTANCE.domain2dto(userDO);
        return userDTO;
    }
}
