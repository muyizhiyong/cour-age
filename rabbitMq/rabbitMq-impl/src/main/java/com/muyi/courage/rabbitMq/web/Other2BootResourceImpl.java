package com.muyi.courage.rabbitMq.web;

import com.muyi.courage.common.dto.DTO;
import com.muyi.courage.common.util.RetCodeEnum;
import com.muyi.courage.rabbitMq.service.Other2BootService;
import com.muyi.courage.rabbitMq.web.Other2BootResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 杨志勇
 * @since 2020/9/22
 */
@Slf4j
@RestController
public class Other2BootResourceImpl implements Other2BootResource {

	@Resource
	private Other2BootService other2BootService;

	@Override
	public DTO send2Boot(Map<String, Object> params){
		DTO dto = new DTO(RetCodeEnum.FAIL.getCode(), "发送失败");
		try {
			dto =other2BootService.send2Boot(params);
		} catch (Exception e) {
			log.error("[send2Boot]：",e);
			dto.setRetMsg(e.getMessage());
		}
		return dto;
	}


}
