package com.muyi.courage.rabbitMq.util;

import com.alibaba.fastjson.JSONObject;

public class MQDocument {
	private JSONObject jsonObject;

	public MQDocument() {
		this.jsonObject = new JSONObject();
	}

	protected MQDocument(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public JSONObject getDoc() {
		return this.jsonObject;
	}

	@Override
	public String toString() {
		return MQDataModule.convertDOMToText(this);
	}
}
