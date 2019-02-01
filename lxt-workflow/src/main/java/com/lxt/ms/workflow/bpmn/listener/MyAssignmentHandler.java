package com.lxt.ms.workflow.bpmn.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 任务分配监听器
 */
public class MyAssignmentHandler implements TaskListener {

	public void notify(DelegateTask delegateTask) {
		delegateTask.setAssignee("chenjia");
		delegateTask.addCandidateUser("fozzie");
		delegateTask.addCandidateGroup("management");
	}

}