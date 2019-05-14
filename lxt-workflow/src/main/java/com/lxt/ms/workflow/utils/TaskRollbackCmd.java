package com.lxt.ms.workflow.utils;

import com.lxt.ms.common.utils.SpringUtils;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cmd.NeedsActiveTaskCmd;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntityManagerImpl;
import org.springframework.beans.BeansException;

import java.util.*;

public class TaskRollbackCmd extends NeedsActiveTaskCmd<String> {
    private TaskService taskService = null;
    private RepositoryService repositoryService = null;
    private RuntimeService runtimeService = null;

    public TaskRollbackCmd(String taskId, TaskService taskService, RepositoryService repositoryService, RuntimeService runtimeService){
        super(taskId);
        this.taskService = taskService;
        this.repositoryService = repositoryService;
        this.runtimeService = runtimeService;
    }

    public String execute(CommandContext commandContext, TaskEntity taskEntity){
        TaskEntityManagerImpl taskEntityManager = (TaskEntityManagerImpl)commandContext.getTaskEntityManager();
        ExecutionEntity executionEntity = taskEntity.getExecution();

        Process process = repositoryService.getBpmnModel(taskEntity.getProcessDefinitionId()).getMainProcess();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(taskEntity.getProcessDefinitionId());
        Collection<FlowElement> list = bpmnModel.getMainProcess().getFlowElements();
        List<SequenceFlow> removeList = new ArrayList<SequenceFlow>();
        String transition = null;
        SequenceFlow rollbackFlow = null;
        UserTask currentUserTask = null;
        for (FlowElement element : list) {
            if (element instanceof UserTask) {
                UserTask userTask = (UserTask) element;
                if (taskEntity.getTaskDefinitionKey().equals(userTask.getId())) {
                    currentUserTask = userTask;
                    transition = (String) runtimeService.getVariable(taskEntity.getProcessInstanceId(), "IN_"+taskEntity.getTaskDefinitionKey());
                    Iterator<SequenceFlow> iter = userTask.getOutgoingFlows().iterator();
                    while(iter.hasNext()){
                        SequenceFlow flow = iter.next();
                        removeList.add(flow);
                        iter.remove();
                    }
                    rollbackFlow = new SequenceFlow(taskEntity.getTaskDefinitionKey(), transition.split("\\.")[1]);
                    userTask.getOutgoingFlows().add(rollbackFlow);
                }
            }
        }

        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("ROLLBACK_"+taskEntity.getTaskDefinitionKey(), transition);
        taskService.complete(taskId, vars);
        currentUserTask.getOutgoingFlows().clear();
        for(SequenceFlow flow : removeList){
            currentUserTask.getOutgoingFlows().add(flow);
        }

        return null;
    }
}