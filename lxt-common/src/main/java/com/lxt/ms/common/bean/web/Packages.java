package com.lxt.ms.common.bean.web;

import java.io.Serializable;

public class Packages implements Serializable{
	private static final long serialVersionUID = -3289042842274897416L;

	private Head head = new Head();

	private Body body = new Body();
	
	public Packages() {}
	
	public Packages(Object obj) {
		this.body.setData(obj);
	}

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

}
