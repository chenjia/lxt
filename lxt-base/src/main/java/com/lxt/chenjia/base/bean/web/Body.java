package com.lxt.chenjia.base.bean.web;

import java.io.Serializable;

import com.lxt.chenjia.base.utils.FormatUtils;

public class Body implements Serializable {

	private static final long serialVersionUID = -8089310335871372357L;

	private Object data;

	@Override
	public String toString() {
		return FormatUtils.obj2Json(data);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
