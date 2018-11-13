package com.lxt.ms.common.bean.web;

import java.io.Serializable;

public class Head implements Serializable {

	private static final long serialVersionUID = 2690917287978962548L;

	private String userId;

	private String url;

	/**
	 * 终端类型 D:Desktop M:Mobile A:App
	 */
	private String terminal;

	/**
	 * 令牌
	 */
	private String token;

	private int status = 200;

	private String msg = "接口调用成功";

	private boolean debug = true;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}