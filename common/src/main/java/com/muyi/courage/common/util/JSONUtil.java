package com.muyi.courage.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JSONUtil {

	/**
	 * create a plain com.zjft.insight.json string from a java object
	 * @param object
	 * @return if object is null, then return null.
	 */
	public static String createJsonString(Object object) {
		return object == null ? null : JSON.toJSONString(object, false);
	}
	
	/**
	 * create a JSONObject from parsing a plain com.zjft.insight.json string
	 * @param text
	 * @return if text is null, or empty string, then return null.
	 */
	public static JSONObject parseJSONObject(String text) {
		return JSON.parseObject(text);
	}
	
	/**
	 * create a JSONArray from parsing a plain com.zjft.insight.json string
	 * @param text
	 * @return
	 */
	public static JSONArray parseJSONArray(String text) {
		return JSON.parseArray(text);
	}
	
	
	/**
	 * create a specific java bean from parsing a plain text
	 * @param <T>
	 * @param text
	 * @param clazz
	 * @return
	 */
	public static <T> T parseBean(String text, Class<T> clazz) {
		return JSON.parseObject(text, clazz);
	}
	
	/**
	 * create a Java List from parsing a plain com.zjft.insight.json string
	 * @param text
	 * @return
	 */
	public static <T> List<T> parseList(String text, Class<T> clazz) {
		return JSON.parseArray(text, clazz);
	}


}
