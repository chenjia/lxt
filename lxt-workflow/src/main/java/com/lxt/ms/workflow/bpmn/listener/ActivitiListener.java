package com.lxt.ms.workflow.bpmn.listener;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ActivitiListener implements TaskListener {

	@Autowired
	private RepositoryService repositoryService;

	@Override
	public void notify(DelegateTask delegateTask) {
		String assignee = null;

		BpmnModel bpmnModel = repositoryService.getBpmnModel(delegateTask.getProcessDefinitionId());
		Collection<FlowElement> list = bpmnModel.getMainProcess().getFlowElements();
		Map<String, Object> vars = new HashMap<>();
		List<SequenceFlow> removeList = new ArrayList<SequenceFlow>();
		UserTask currentUserTask = null;
		for (FlowElement element : list) {
			if(delegateTask.getName().equals(element.getName())){
				assignee = (String) delegateTask.getVariable("ASSIGNEE_"+delegateTask.getId());
			}
		}

		if(assignee == null){
			assignee = (String) delegateTask.getVariable("INITIATOR");
		}
		delegateTask.setAssignee(assignee);
	}

}