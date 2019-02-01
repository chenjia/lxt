package com.lxt.ms.workflow.bpmn.entity;

import org.apache.commons.lang.StringUtils;

public abstract class AbstractActivity {
	protected String id;
	protected String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(!id.startsWith("_")){
			id = "_" + id;
		}
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
