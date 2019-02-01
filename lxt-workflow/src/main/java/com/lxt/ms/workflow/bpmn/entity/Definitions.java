package com.lxt.ms.workflow.bpmn.entity;

public class Definitions {
	private String id;
	private String targetNamespace = "http://activiti.org/bpmn20" ;
	private String xmlnsActiviti = "http://activiti.org/bpmn";
	private String xmlns = "http://www.omg.org/spec/BPMN/20100524/MODEL";
	private Process process;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTargetNamespace() {
		return targetNamespace;
	}

	public void setTargetNamespace(String targetNamespace) {
		this.targetNamespace = targetNamespace;
	}

	public String getXmlnsActiviti() {
		return xmlnsActiviti;
	}

	public void setXmlnsActiviti(String xmlnsActiviti) {
		this.xmlnsActiviti = xmlnsActiviti;
	}

	public String getXmlns() {
		return xmlns;
	}

	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

}
