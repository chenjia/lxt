package com.lxt.ms.workflow.utils;

import com.lxt.ms.common.utils.SpringUtils;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.cmd.NeedsActiveTaskCmd;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntityManagerImpl;
import org.springframework.beans.BeansException;

import java.util.List;

public class TaskRollbackCmd extends NeedsActiveTaskCmd<String> {
    private RepositoryService repositoryService = (RepositoryService) SpringUtils.getApplicationContext().getBean("repositoryService");

    public TaskRollbackCmd(String taskId){
        super(taskId);
    }

    public String execute(CommandContext commandContext, TaskEntity taskEntity){
        TaskEntityManagerImpl taskEntityManager = (TaskEntityManagerImpl)commandContext.getTaskEntityManager();
        ExecutionEntity executionEntity = taskEntity.getExecution();
        taskEntityManager.deleteTask(taskEntity, "rollback by " + taskEntity.getAssignee(), false, false);

        Process process = repositoryService.getBpmnModel(taskEntity.getProcessDefinitionId()).getMainProcess();
        FlowNode taskNode = (FlowNode)process.getFlowElement(taskEntity.getName());

        //获取目标节点的来源连线
        List<SequenceFlow> flows = taskNode.getIncomingFlows();
        if(flows==null || flows.size()<1){
            throw new ActivitiException("回退错误，目标节点没有来源连线");
        }

        executionEntity.setCurrentFlowElement(flows.get(0));
        commandContext.getAgenda().planTakeOutgoingSequenceFlowsOperation(executionEntity, true);
        return null;
    }
}