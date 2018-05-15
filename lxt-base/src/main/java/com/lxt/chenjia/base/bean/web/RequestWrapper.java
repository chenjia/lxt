package com.lxt.chenjia.base.bean.web;

import java.io.Serializable;
import java.util.Map;

public class RequestWrapper implements Serializable {
	private static final long serialVersionUID = -4437251842787588081L;

	private Request request;

	public RequestWrapper() {
	}

	public RequestWrapper(Request request) {
		this.request = request;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Map<String, Object> data(){
		return request.getBody().getData();
	}
}
