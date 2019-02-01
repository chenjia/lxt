package com.lxt.ms.workflow.graph.entity;

import com.lxt.ms.workflow.graph.utils.NodeBuilder;
import com.lxt.ms.workflow.graph.utils.StartBuilder;

import java.util.HashMap;
import java.util.Map;

public class Start extends Vertex {
	private String nodeType = "start";
	private boolean isSymbol = true;

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public boolean isSymbol() {
		return isSymbol;
	}

	public void setSymbol(boolean isSymbol) {
		this.isSymbol = isSymbol;
	}

	public Map<String, Object> getNodeAttributes() {
		return new HashMap<String, Object>();
	}

	@Override
	public NodeBuilder getBuilder() {
		return new StartBuilder();
	}
}
