package com.muyi.courage.handler.handler;

import org.springframework.lang.Nullable;

/**
 * Interface to be implemented by objects that define a mapping between
 * requests and handler objects.
 * 维护和管理请求和HandlerMethod之间的映射
 *
 */
public interface HandlerMapping {

	/**
	 * Return a handler for this request.
	 *
	 * @param key 方法唯一名称
	 * @return Object 返回值
	 * @throws Exception 异常
	 */
	@Nullable
	Object getHandler(String key) throws Exception;
}
