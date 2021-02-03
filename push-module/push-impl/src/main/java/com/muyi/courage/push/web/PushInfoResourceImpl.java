package com.muyi.courage.push.web;

import com.muyi.courage.common.dto.DTO;
import com.muyi.courage.push.service.PushInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 杨志勇
 * @date 2021-02-02 14:49
 */
@Slf4j
@RestController
public class PushInfoResourceImpl implements PushInfoResource {

    @Resource
    private PushInfoService pushInfoService;

    @Override
    public DTO sendInfo2All(String message) {
        return pushInfoService.sendInfo2All(message);
    }
}
