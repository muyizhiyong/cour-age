package com.muyi.courage.aop.po;

import lombok.Data;

@Data
public class WebLogPO {

	/**
	 * 操作用户
	 */
	private String username;

	/**
	 * 操作描述
	 */
	private String description;

	/**
	 * 请求类型
	 */
	private String method;

	/**
	 * URL
	 */
	private String url;

	/**
	 * 操作时间
	 */
	private String startTime;

	/**
	 * 执行花费的时间
	 */
	private Integer costTime;

	/**
	 * 用户端IP地址
	 */
	private String clientIp;

	/**
	 * 服务端IP地址
	 */
	private String serverIp;


	/**
	 * 请求返回的结果码
	 */
	private String resultCode;

	/**
	 * 请求返回的结果
	 */
	private String resultMsg;


}
