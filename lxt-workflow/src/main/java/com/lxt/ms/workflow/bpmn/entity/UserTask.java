package com.lxt.ms.workflow.bpmn.entity;

public class UserTask extends AbstractActivity{
	protected ExtensionElements extensionElements = new ExtensionElements();

	public ExtensionElements getExtensionElements() {
		return extensionElements;
	}

	public void setExtensionElements(ExtensionElements extensionElements) {
		this.extensionElements = extensionElements;
	}

}
