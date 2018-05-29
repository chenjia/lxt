package com.lxt.chenjia.base.bean.web;

import java.io.Serializable;

import com.lxt.chenjia.base.utils.JsonUtils;
import com.lxt.chenjia.base.utils.SecurityUtils;

public class ResponseWrapper implements Serializable {
	private static final long serialVersionUID = -4437251842787588081L;

	private Packages response;

	public ResponseWrapper() {
	}

	public ResponseWrapper(Packages response) {
		this.response = response;
	}

	public String getResponse() throws Exception {
		return SecurityUtils.encrypt(JsonUtils.obj2Json(response));
	}

	public void setResponse(Packages response) {
		this.response = response;
	}

	public String toJson(){
		return JsonUtils.obj2Json(this);
	}
}
