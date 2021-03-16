package com.muyi.courage.push.web;

import com.muyi.courage.common.dto.DTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;

@Api(value = "推送中心：推送邮件消息", tags = "推送中心：推送邮件消息")
public interface SendMailInfoResource {

	String PREFIX = "/push-module/mailInfo";

	@GetMapping(PREFIX + "/user")
	@ApiOperation(value = "发送邮件给某用户", notes = "发送邮件给某用户")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userName", value = "用户编号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "noticeTitle", value = "通知标题", required = true, paramType = "query"),
			@ApiImplicitParam(name = "message", value = "通知信息", required = true, paramType = "query"),
			@ApiImplicitParam(name = "noticeAddress", value = "通知地址", paramType = "query")
		})
	DTO sendInfo2User(@ApiIgnore @RequestParam HashMap hashMap);

	@GetMapping(PREFIX + "/role")
	@ApiOperation(value = "发送邮件给某角色", notes = "发送邮件给某角色")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "roles", value = "角色编号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "noticeTitle", value = "通知标题", required = true, paramType = "query"),
			@ApiImplicitParam(name = "message", value = "通知信息", required = true, paramType = "query")
			})
	DTO sendInfo2Roles(@ApiIgnore @RequestParam HashMap hashMap);

	@GetMapping(PREFIX + "/all")
	@ApiOperation(value = "发送邮件给所有人", notes = "发送邮件给所有人")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "message", value = "通知信息", required = true, paramType = "query"),
			@ApiImplicitParam(name = "noticeTitle", value = "通知标题", required = true, paramType = "query")
	})
	DTO sendInfo2All(@ApiIgnore @RequestParam HashMap hashMap);
}
