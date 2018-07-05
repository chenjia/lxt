package com.lxt.chenjia.common.bean.web;

public class Packages {

	private Head head = new Head();

	private Body body = new Body();
	
	public Packages() {
		// TODO Auto-generated constructor stub
	}
	
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
