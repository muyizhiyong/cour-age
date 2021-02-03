package com.muyi.courage.push.service;

import com.muyi.courage.common.dto.DTO;

/**
 * @author 杨志勇
 * @date 2021-02-02 14:51
 */
public interface PushInfoService {

    DTO sendInfo2All(String message);
}
