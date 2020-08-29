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


	/**
	 * JSON报文转XML报文第一部分（适用于对接上海银行生物识别平台）
	 *
	 * @param jsonHead json报文头
	 * @return
	 */
	public static String jsonToXmlSHAuth(JSONObject jsonHead, JSONObject jsonBody){
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
			buffer.append("<Message>\r\n");
			buffer.append("<Head>\r\n");
			jsToXmlStr(jsonHead,buffer);
			buffer.append("</Head>\r\n");
			buffer.append("<Body>\r\n");
			jsToXmlStr(jsonBody,buffer);
			buffer.append("</Body>\r\n");
			buffer.append("</Message>");
			return buffer.toString();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * JSON报文转XML报文第二部分（适用于对接上海银行生物识别平台）
	 *
	 * @param jsonObject
	 * @param buffer
	 * @return
	 */
	private static String jsToXmlStr(JSONObject jsonObject, StringBuffer buffer) {
		Set<Map.Entry<String, Object>> se = jsonObject.entrySet();
		for (Iterator<Map.Entry<String, Object>> it = se.iterator(); it.hasNext();) {
			Map.Entry<String, Object> en = it.next();
			if (en.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONObject")) {
				buffer.append("<"+en.getKey()+">");
				JSONObject jo = jsonObject.getJSONObject(en.getKey());
				jsToXmlStr(jo, buffer);
				buffer.append("</"+en.getKey()+">\r\n");
			} else if (en.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONArray")) {
				JSONArray jsonArray = jsonObject.getJSONArray(en.getKey());
				for (int i = 0; i<jsonArray.size(); i++){
					buffer.append("<"+en.getKey()+">");
					JSONObject jsonObject1 = jsonArray.getJSONObject(i);
					jsToXmlStr(jsonObject1,buffer);
					buffer.append("</"+en.getKey()+">\r\n");
				}
			} else if (en.getValue().getClass().getName().equals("java.lang.String")) {
				buffer.append("<"+en.getKey()+">"+en.getValue());
				buffer.append("</"+en.getKey()+">\r\n");
			}
		}
		return buffer.toString();
	}
}
