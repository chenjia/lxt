package com.lxt.chenjia.base.bean.web;

public class ServiceBean {
	private String name;
	private String method;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public ServiceBean(String name, String method) {
		super();
		this.name = name;
		this.method = method;
	}

}
