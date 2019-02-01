package com.lxt.ms.workflow.graph.utils;

import com.lxt.ms.workflow.bpmn.entity.AbstractActivity;
import com.lxt.ms.workflow.bpmn.entity.EndEvent;
import com.lxt.ms.workflow.graph.entity.End;
import com.lxt.ms.workflow.graph.entity.Vertex;

public class EndBuilder extends NodeBuilder {
	@Override
	public AbstractActivity build(Vertex vertex) {
		EndEvent endEvent = new EndEvent();
		End graphEnd = (End) vertex;
		endEvent.setId(graphEnd.getId());
		endEvent.setName(graphEnd.getLabel());
		return endEvent;
	}
}
