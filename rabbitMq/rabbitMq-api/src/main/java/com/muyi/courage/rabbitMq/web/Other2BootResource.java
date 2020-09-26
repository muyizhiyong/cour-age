package com.muyi.courage.rabbitMq.web;


import com.alibaba.fastjson.JSONObject;
import com.muyi.courage.common.dto.DTO;
import com.muyi.courage.handler.annotation.CourageMessageMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author 杨志勇
 * @since 2020/9/22
 */
@Api(value = "通讯：rabbitMq",tags = {"通讯：rabbitMq"})
public interface Other2BootResource {

	String PREFIX = "rabbitMq/other2boot";

	@GetMapping(PREFIX+"/send2Boot")
	@ApiOperation(value = "向应用发送消息", notes = "向应用发送消息")
    DTO send2Boot(@RequestParam Map<String, Object> params);


	@CourageMessageMapping("getMsgByType1")
	JSONObject getMsgByType1(@RequestBody Map<String, Object> req);
}
