package com.lxt.ms.workflow.graph.utils;

import com.lxt.ms.workflow.bpmn.entity.AbstractActivity;
import com.lxt.ms.workflow.bpmn.entity.StartEvent;
import com.lxt.ms.workflow.graph.entity.Start;
import com.lxt.ms.workflow.graph.entity.Vertex;

public class StartBuilder extends NodeBuilder{
	@Override
	public AbstractActivity build(Vertex vertex) {
		StartEvent startEvent = new StartEvent();
		Start graphStart = (Start)vertex;
		startEvent.setId(graphStart.getId());
		startEvent.setName(graphStart.getLabel());
		return startEvent;
	}
}
