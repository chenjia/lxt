package com.lxt.ms.workflow.graph.utils;


import com.lxt.ms.workflow.bpmn.entity.AbstractActivity;
import com.lxt.ms.workflow.graph.entity.Vertex;

public abstract class NodeBuilder {
	public abstract AbstractActivity build(Vertex vertex);
}
