package com.muyi.courage.rabbitMq.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.muyi.courage.common.dto.DTO;
import com.muyi.courage.common.util.RetCodeEnum;
import com.muyi.courage.rabbitMq.boot2other.service.RabbitMqMsgService;
import com.muyi.courage.rabbitMq.service.Other2BootService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 杨志勇
 * @since 2020/9/22
 */
@Slf4j
@Service
public class Other2BootServiceImpl implements Other2BootService {

    @Resource
    private RabbitMqMsgService rabbitMqMsgService;

    @Override
    public DTO send2Boot(Map<String, Object> params) {
        DTO dto = new DTO(RetCodeEnum.FAIL.getCode(), "发送失败");

        String msg = JSONObject.toJSONString(params);
        Map<String,Object> resultMap = rabbitMqMsgService.sendInfo(msg,Map.class,"RabbitMqInput");

        String retMsg = JSONObject.toJSONString(resultMap);
        log.info("[send2Boot] receive msg:"+retMsg);
        dto.setResult(RetCodeEnum.SUCCEED);
        return dto;
    }
}
