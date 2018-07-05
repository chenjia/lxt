package com.lxt.chenjia.common.bean.web;

import java.io.Serializable;

import com.lxt.chenjia.common.utils.JSONUtils;
import com.lxt.chenjia.common.utils.SecurityUtils;

public class ResponseWrapper implements Serializable {
	private static final long serialVersionUID = -4437251842787588081L;

	private Packages response;

	public ResponseWrapper() {
	}

	public ResponseWrapper(Packages response) {
		this.response = response;
	}

	public String getResponse() throws Exception {
		return SecurityUtils.encrypt(JSONUtils.obj2Json(response));
	}

	public void setResponse(Packages response) {
		this.response = response;
	}

	public String toJson(){
		return JSONUtils.obj2Json(this);
	}
}
