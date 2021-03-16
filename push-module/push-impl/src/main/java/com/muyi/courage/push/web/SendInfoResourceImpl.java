package com.muyi.courage.push.web;

import com.muyi.courage.common.dto.DTO;
import com.muyi.courage.common.util.RetCodeEnum;
import com.muyi.courage.push.service.SendMailInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author 杨志勇
 * @date 2021-03-16 16:48
 */
@RestController
@Slf4j
public class SendInfoResourceImpl implements SendMailInfoResource {

    @Resource
	private SendMailInfoService sendMailInfoService;

    @Override
	public DTO sendInfo2User(HashMap hashMap) {
		DTO dto = new DTO(RetCodeEnum.FAIL);
		try {
			return sendMailInfoService.sendInfo2User(hashMap);
		}catch (Exception e) {
			log.error("发送邮件失败！", e);
			dto.setRetMsg("发送邮件到用户失败！");
			return dto;
		}
	}

	@Override
	public DTO sendInfo2Roles(HashMap hashMap) {
		DTO dto = new DTO(RetCodeEnum.FAIL);
		try {
			return sendMailInfoService.sendInfo2Roles(hashMap);
		}catch (Exception e) {
			log.error("发送邮件失败！", e);
			dto.setRetMsg("发送邮件到角色是失败！");
			return dto;
		}
	}

	@Override
	public DTO sendInfo2All(HashMap hashMap) {
		DTO dto = new DTO(RetCodeEnum.FAIL);
		try {
			return sendMailInfoService.sendInfo2All(hashMap);
		}catch (Exception e) {
			log.error("发送邮件失败！", e);
			dto.setRetMsg("发送邮件到角色是失败！");
			return dto;
		}
	}
}
