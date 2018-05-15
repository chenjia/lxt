package com.lxt.chenjia.base.bean.web;

public class Response {

	private Head head = new Head();

	private Body body = new Body();

	public Response() {
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
