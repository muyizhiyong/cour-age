package com.muyi.courage.push.web;

import com.muyi.courage.common.dto.DTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 杨志勇
 * @date 2021-02-02 14:20
 */
public interface PushInfoResource {

    String PREFIX = "/push-module/info";

    @GetMapping(PREFIX + "/sendInfo2All")
    @ApiOperation(value = "推送消息给所有人", notes = "推送消息给所有人")
    DTO sendInfo2All(@RequestParam("message") String message);

}
