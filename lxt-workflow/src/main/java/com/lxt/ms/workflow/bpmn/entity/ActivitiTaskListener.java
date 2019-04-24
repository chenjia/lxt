package com.lxt.ms.workflow.bpmn.entity;

public class ActivitiTaskListener {
	private String event = "create";
//	private String clazz = "com.lxt.ms.workflow.bpmn.listener.ActivitiListener";
	private String delegateExpression ="${activitiListener}";

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

//	public String getClazz() {
//		return clazz;
//	}

//	public void setClazz(String clazz) {
//		this.clazz = clazz;
//	}

	public String getDelegateExpression(){
		return delegateExpression;
	}

	public void setDelegateExpression(String delegateExpression){
		this.delegateExpression = delegateExpression;
	}
}
