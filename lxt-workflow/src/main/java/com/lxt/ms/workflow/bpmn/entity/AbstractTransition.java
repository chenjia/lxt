package com.lxt.ms.workflow.bpmn.entity;

public abstract class AbstractTransition {
	protected String id;
	protected String sourceRef;
	protected String targetRef;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(!id.startsWith("_")){
			id = "_" + id;
		}
		this.id = id;
	}

	public String getSourceRef() {
		return sourceRef;
	}

	public void setSourceRef(String sourceRef) {
		if(!sourceRef.startsWith("_")){
			sourceRef = "_" + sourceRef;
		}
		this.sourceRef = sourceRef;
	}

	public String getTargetRef() {
		return targetRef;
	}

	public void setTargetRef(String targetRef) {
		if(!targetRef.startsWith("_")){
			targetRef = "_" + targetRef;
		}
		this.targetRef = targetRef;
	}

}
