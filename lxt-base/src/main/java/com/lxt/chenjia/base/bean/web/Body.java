package com.lxt.chenjia.base.bean.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.lxt.chenjia.base.utils.FormatUtils;

public class Body implements Serializable {

	private static final long serialVersionUID = -8089310335871372357L;

	private Map<String, Object> data = new HashMap<String, Object>();

	@Override
	public String toString() {
		return FormatUtils.obj2Json(data);
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	

}
