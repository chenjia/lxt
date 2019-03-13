package com.lxt.ms.workflow.utils;

import com.lxt.ms.common.utils.SpringUtils;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.cmd.NeedsActiveTaskCmd;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.TaskEntity;

import java.util.List;

public class TaskTransitionsCmd extends NeedsActiveTaskCmd<List<SequenceFlow>> {
	private RepositoryService repositoryService = (RepositoryService) SpringUtils.getApplicationContext().getBean("repositoryService");
	private String taskId;

	public TaskTransitionsCmd(String taskId) {
		super(taskId);
	}

	@Override
	public List<SequenceFlow> execute(CommandContext commandContext, TaskEntity taskEntity) {
		Process process = repositoryService.getBpmnModel(taskEntity.getProcessDefinitionId()).getMainProcess();
		FlowNode taskNode = (FlowNode)process.getFlowElement(taskEntity.getName());
		List<SequenceFlow> flows = taskNode.getIncomingFlows();
		return flows;
	}
}
