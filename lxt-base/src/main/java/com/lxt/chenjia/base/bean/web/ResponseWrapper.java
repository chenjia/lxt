package com.lxt.chenjia.base.bean.web;

import java.io.Serializable;
import java.util.Map;

import com.lxt.chenjia.base.utils.FormatUtils;
import com.lxt.chenjia.base.utils.SecurityUtils;

public class ResponseWrapper implements Serializable {
	private static final long serialVersionUID = -4437251842787588081L;

	private Response response;

	public ResponseWrapper() {
	}

	public ResponseWrapper(Response response) {
		this.response = response;
	}

	public String getResponse() {
		String json = "";
		
		try {
			json = SecurityUtils.encrypt(FormatUtils.obj2Json(response));
			System.out.println(FormatUtils.obj2Json(response));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return json;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	
}
