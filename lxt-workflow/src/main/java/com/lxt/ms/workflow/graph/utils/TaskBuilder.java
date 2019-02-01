package com.lxt.ms.workflow.graph.utils;

import com.lxt.ms.workflow.bpmn.entity.AbstractActivity;
import com.lxt.ms.workflow.bpmn.entity.UserTask;
import com.lxt.ms.workflow.graph.entity.Task;
import com.lxt.ms.workflow.graph.entity.Vertex;

public class TaskBuilder extends NodeBuilder{
	@Override
	public AbstractActivity build(Vertex vertex) {
		UserTask userTask = new UserTask();
		Task graphTask = (Task)vertex;
		userTask.setId(graphTask.getId());
		userTask.setName(graphTask.getLabel());
		return userTask;
	}
}
