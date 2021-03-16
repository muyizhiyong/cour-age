package com.muyi.courage.push.service;

import com.muyi.courage.common.dto.DTO;

import java.util.HashMap;

/**
 * @author 杨志勇
 * @date 2021-03-16 16:49
 */
public interface SendMailInfoService {
    DTO sendInfo2User(HashMap hashMap);

    DTO sendInfo2Roles(HashMap hashMap);

    DTO sendInfo2All(HashMap hashMap);
}
