package com.muyi.courage.rabbitMq.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.muyi.courage.common.util.StringUtil;
import io.netty.util.internal.ObjectUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MQDataModule {
	
	public MQDataModule() {
	}

	public static String convertDOMToText(MQDocument mqDocument) {
		return mqDocument != null && mqDocument.getDoc() != null ? JSON.toJSONString(mqDocument.getDoc(), new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty}) : "";
	}
	public static void setNodeValue(String path, String newValue, MQDocument mqDocument) {
		setNodeObjValue(path, newValue, mqDocument);
	}

	public static void setNodeObjValue(String path, Object newValue, MQDocument mqDocument) {
		if (newValue instanceof MQDocument) {
			Iterator iter = ((MQDocument)newValue).getDoc().entrySet().iterator();
			while(iter.hasNext()) {
				Map.Entry<String, Object> objs = (Map.Entry)iter.next();
				String scope = (String)objs.getKey();
				Object jsonObj = objs.getValue();
				mergeNode(path, scope, jsonObj, mqDocument);
			}
		} else {
			if (!(newValue instanceof String) && !(newValue instanceof Integer) && !(newValue instanceof Boolean) && !(newValue instanceof Float) && !(newValue instanceof Double) && !(newValue instanceof BigDecimal) && !(newValue instanceof Long) && !(newValue instanceof Character) && !(newValue instanceof Byte) && !(newValue instanceof Number) && !(newValue instanceof Date)) {
				boolean needConvert;
				if (newValue instanceof List) {
					needConvert = StringUtil.checkIfHasUserDefObjects(newValue);
					if (needConvert) {
						JSONArray jsonArray = JSONObject.parseArray(JSON.toJSONString(newValue, new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty}));
						newValue = jsonArray;
					}
				} else if (newValue instanceof Map) {
					needConvert = StringUtil.checkIfHasUserDefObjects(newValue);
					if (needConvert) {
						JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(newValue, new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty}));
						newValue = jsonObject;
					}
				} else {
					JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(newValue, new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty}));
					newValue = jsonObject;
				}
			}
			String[] scopes = path.split("\\.", -1);
			Object jsonObj = mqDocument.getDoc();
			boolean isArray = false;
			int arrayIndex = -1;

			for(int i = 0; i <= scopes.length - 1; ++i) {
				isArray = false;
				int penultIndex = StringUtil.getPenultIndex(scopes[i], '_');
				String scope;
				if (penultIndex >= 0 && scopes[i].endsWith("_") && scopes[i].lastIndexOf(95) - penultIndex > 1) {
					scope = scopes[i].substring(0, penultIndex);
					arrayIndex = Integer.parseInt(scopes[i].substring(penultIndex + 1, scopes[i].length() - 1));
					isArray = true;
				} else {
					scope = scopes[i];
				}

				if (jsonObj instanceof JSONObject) {
					if (((JSONObject)jsonObj).get(scope) != null && !(((JSONObject)jsonObj).get(scope) instanceof String) && (i != scopes.length - 1 || isArray)) {
						if (isArray && ((JSONObject)jsonObj).get(scope) instanceof JSONObject) {
							((JSONObject)jsonObj).put(scope, new JSONArray());
						} else if (!isArray && ((JSONObject)jsonObj).get(scope) instanceof JSONArray) {
							((JSONObject)jsonObj).put(scope, new JSONObject());
						}
					} else if (isArray) {
						((JSONObject)jsonObj).put(scope, new JSONArray());
					} else if (i == scopes.length - 1) {
						if (newValue instanceof JSONArray) {
							((JSONObject)jsonObj).put(scope, newValue);
						} else if (newValue instanceof JSONObject) {
							((JSONObject)jsonObj).put(scope, newValue);
						} else if (newValue instanceof List) {
							((JSONObject)jsonObj).put(scope, new JSONArray((List)newValue));
						} else if (newValue instanceof Map) {
							((JSONObject)jsonObj).put(scope, new JSONObject((Map)newValue));
						} else {
							((JSONObject)jsonObj).put(scope, newValue);
						}
					} else {
						((JSONObject)jsonObj).put(scope, new JSONObject());
					}

					jsonObj = ((JSONObject)jsonObj).get(scope);
				}

				if (jsonObj instanceof JSONArray && isArray) {
					if (((JSONArray)jsonObj).size() > arrayIndex && i == scopes.length - 1) {
						((JSONArray)jsonObj).set(arrayIndex, newValue);
					} else {
						label174:
						while(true) {
							while(true) {
								if (arrayIndex <= ((JSONArray)jsonObj).size() - 1) {
									break label174;
								}

								if (i == scopes.length - 1) {
									if (arrayIndex == ((JSONArray)jsonObj).size()) {
										if (newValue instanceof JSONArray) {
											((JSONArray)jsonObj).add(newValue);
										} else if (newValue instanceof JSONObject) {
											((JSONArray)jsonObj).add(newValue);
										} else if (newValue instanceof List) {
											((JSONArray)jsonObj).add(new JSONArray((List)newValue));
										} else if (newValue instanceof Map) {
											((JSONArray)jsonObj).add(new JSONObject((Map)newValue));
										} else {
											((JSONArray)jsonObj).add(newValue);
										}
									} else {
										((JSONArray)jsonObj).add((Object)null);
									}
								} else if (i < scopes.length - 1) {
									String nextScope = scopes[i + 1];
									int tempindex = StringUtil.getPenultIndex(nextScope, '_');
									if (tempindex == 0 && nextScope.endsWith("_") && nextScope.lastIndexOf(95) - tempindex > 1) {
										((JSONArray)jsonObj).add(new JSONArray());
									} else {
										((JSONArray)jsonObj).add(new JSONObject());
									}
								} else {
									((JSONArray)jsonObj).add(new JSONObject());
								}
							}
						}
					}

					jsonObj = ((JSONArray)jsonObj).get(arrayIndex);
				}
			}
		}
	}

	private static void mergeNode(String appendScope, String scope, Object jsonNode, MQDocument mqDocument) {
		if (jsonNode == null) {
			setNodeObjValue(appendScope.length() == 0 ? scope : appendScope + "." + scope, jsonNode, mqDocument);
		} else {
			if (jsonNode instanceof JSONArray) {
				if (((JSONArray)jsonNode).size() == 0) {
					try {
						if (!(checkNodeExist(appendScope.length() == 0 ? scope : appendScope + "." + scope, mqDocument) instanceof List)) {
							setNodeObjValue(appendScope.length() == 0 ? scope : appendScope + "." + scope, jsonNode, mqDocument);
						}
					} catch (IndexOutOfBoundsException var9) {
						setNodeObjValue(appendScope.length() == 0 ? scope : appendScope + "." + scope, jsonNode, mqDocument);
					}
				} else {
					for(int i = 0; i < ((JSONArray)jsonNode).size(); ++i) {
						mergeNode(appendScope, scope + "_" + i + "_", ((JSONArray)jsonNode).get(i), mqDocument);
					}
				}
			} else if (jsonNode instanceof JSONObject) {
				if (((JSONObject)jsonNode).size() == 0) {
					try {
						if (!(checkNodeExist(appendScope.length() == 0 ? scope : appendScope + "." + scope, mqDocument) instanceof Map)) {
							setNodeObjValue(appendScope.length() == 0 ? scope : appendScope + "." + scope, jsonNode, mqDocument);
						}
					} catch (IndexOutOfBoundsException var8) {
						setNodeObjValue(appendScope.length() == 0 ? scope : appendScope + "." + scope, jsonNode, mqDocument);
					}
				} else {
					Iterator iter = ((JSONObject)jsonNode).entrySet().iterator();

					while(iter.hasNext()) {
						Map.Entry<String, Object> objs = (Map.Entry)iter.next();
						String jsonScope = (String)objs.getKey();
						Object jsonObj = objs.getValue();
						mergeNode(appendScope.length() == 0 ? scope : appendScope + "." + scope, jsonScope, jsonObj, mqDocument);
					}
				}
			} else {
				setNodeObjValue(appendScope.length() == 0 ? scope : appendScope + "." + scope, jsonNode, mqDocument);
			}
		}
	}

	public static Object checkNodeExist(String path, MQDocument mqDocument) {
		String[] scopes = path.split("\\.", -1);
		Object jsonObj = mqDocument.getDoc();
		boolean isArray;
		int arrayIndex;

		for(int i = 0; i < scopes.length; ++i) {
			isArray = false;
			arrayIndex = -1;
			int penultIndex = StringUtil.getPenultIndex(scopes[i], '_');
			String scope;
			if (penultIndex >= 0 && scopes[i].endsWith("_") && scopes[i].lastIndexOf(95) - penultIndex > 1) {
				scope = scopes[i].substring(0, penultIndex);
				arrayIndex = Integer.parseInt(scopes[i].substring(penultIndex + 1, scopes[i].length() - 1));
				isArray = true;
			} else {
				scope = scopes[i];
			}

			if (!(jsonObj instanceof JSONObject) && !(jsonObj instanceof Map)) {
				if ((jsonObj instanceof JSONArray || jsonObj instanceof List) && isArray) {
					jsonObj = ((List)jsonObj).get(arrayIndex);
				} else {
					jsonObj = null;
				}
			} else {
				jsonObj = ((Map)jsonObj).get(scope);
				if ((jsonObj instanceof JSONArray || jsonObj instanceof List) && isArray) {
					jsonObj = ((List)jsonObj).get(arrayIndex);
				}
			}
		}
		return jsonObj;
	}


}
