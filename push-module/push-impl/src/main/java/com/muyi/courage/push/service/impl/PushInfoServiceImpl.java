package com.muyi.courage.push.service.impl;

import com.muyi.courage.common.dto.DTO;
import com.muyi.courage.common.util.RetCodeEnum;
import com.muyi.courage.push.service.PushInfoService;
import com.muyi.courage.push.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 杨志勇
 * @date 2021-02-02 14:52
 */
@Slf4j
@Service
public class PushInfoServiceImpl implements PushInfoService {
    @Override
    public DTO sendInfo2All(String message) {
        DTO dto = new DTO(RetCodeEnum.FAIL);
        WebSocketServer.sendInfo2User(message,null);
        dto.setResult(RetCodeEnum.SUCCEED);
        return dto;
    }
}
