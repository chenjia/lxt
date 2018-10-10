package com.lxt.ms.common.bean.web;

import java.io.Serializable;

public class RequestWrapper implements Serializable {
	private static final long serialVersionUID = -4437251842787588081L;

	private Packages request;

	public RequestWrapper() {
	}

	public RequestWrapper(Packages request) {
		this.request = request;
	}

	public Packages getRequest() {
		return request;
	}

	public void setRequest(Packages request) {
		this.request = request;
	}

	public Object data(){
		return request.getBody().getData();
	}
}
