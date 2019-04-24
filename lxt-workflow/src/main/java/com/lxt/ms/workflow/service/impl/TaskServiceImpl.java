package com.lxt.ms.workflow.service.impl;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.workflow.model.User;
import com.lxt.ms.workflow.service.api.TaskService;
import com.lxt.ms.workflow.utils.TaskRollbackCmd;
import org.activiti.bpmn.model.*;
import org.activiti.engine.*;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("taskService")
public class TaskServiceImpl implements TaskService {
    @Autowired
    private ProcessEngine processEngine = null;

    @Autowired
    private RepositoryService repositoryService = null;

    @Autowired
    private RuntimeService runtimeService = null;

    @Autowired
    private org.activiti.engine.TaskService taskService = null;

    @Autowired
    private HistoryService historyService = null;

    @Autowired
    private ManagementService managementService = null;

    @Override
    public Packages submit(String $userId, String taskId, String targetId, String assignee) throws APIException {
        Packages pkg = new Packages();
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        Collection<FlowElement> list = bpmnModel.getMainProcess().getFlowElements();
        Map<String, Object> vars = new HashMap<>();
        List<SequenceFlow> removeList = new ArrayList<SequenceFlow>();
        UserTask currentUserTask = null;
        for (FlowElement element : list) {
            if (element instanceof UserTask) {
                UserTask userTask = (UserTask) element;
                if(userTask.getName().equals(task.getName())){
                    currentUserTask = userTask;
                    Iterator<SequenceFlow> iter = userTask.getOutgoingFlows().iterator();
                    while(iter.hasNext()){
                        SequenceFlow flow = iter.next();
                        if(!flow.getTargetRef().equals(targetId)){
                            removeList.add(flow);
                            iter.remove();
                        }
                    }
                }
            }
        }

        if(assignee != null){
            vars.put("ASSIGNEE_"+taskId, assignee);
        }
        if(targetId != null){
            vars.put("OUT_"+taskId, currentUserTask.getOutgoingFlows().get(0).getTargetFlowElement().getName());
        }

        taskService.complete(taskId, vars);
        for(SequenceFlow flow : removeList){
            currentUserTask.getOutgoingFlows().add(flow);
        }

        return pkg;
    }

    @Override
    public Packages rollback(String taskId, String nodeId, String target) throws APIException {
        Packages pkg = new Packages();

        managementService.executeCommand(new TaskRollbackCmd(taskId));

        return pkg;
    }

    @Override
    public Packages nextStep(String taskId) throws APIException {
     Packages pkg = new Packages();

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        Collection<FlowElement> list = bpmnModel.getMainProcess().getFlowElements();
        List<Map<String, Object>> nextFlowList = new ArrayList<Map<String, Object>>();
        for (FlowElement element : list) {
            if (element instanceof SequenceFlow) {
                SequenceFlow flow = (SequenceFlow) element;
                if (task.getTaskDefinitionKey().equals(flow.getSourceRef())) {
                    Map<String, Object> activityMap = new HashMap<>();
                    FlowElement target = flow.getTargetFlowElement();
                    activityMap.put("activity", target);

                    List<User> userList = new ArrayList<>();
                    User user = new User();
                    user.setUsername("chenjia");
                    user.setUserId("chenjia");
                    user.setRealname("陈佳");
                    userList.add(user);

                    User user1 = new User();
                    user1.setUsername("admin");
                    user1.setUserId("admin");
                    user1.setRealname("admin");
                    userList.add(user1);

                    User user2 = new User();
                    user2.setUsername("xiaoting");
                    user2.setUserId("xiaoting");
                    user2.setRealname("xiaoting");
                    userList.add(user2);
                    activityMap.put("assignee", userList);
                    nextFlowList.add(activityMap);
                }
            }
        }
        pkg.getBody().setData(nextFlowList);

        return pkg;
    }

    @Override
    public Packages historyList(String userId, PageData pageData) throws APIException {
        return null;
    }

    @Override
    public Packages historyActivity(String instanceId) throws APIException {
        return null;
    }

    @Override
    public Packages transition(String taskId) throws APIException {
        return null;
    }

    @Override
    public Packages details(String taskId) throws APIException {
        return null;
    }

    public Packages list(String $userId, PageData pageData){
        Packages pkg = new Packages();

        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned($userId);

        List<Task> list = taskQuery.listPage((pageData.getPageNumber()-1)*pageData.getPageSize(), pageData.getPageSize());
        long total = taskQuery.count();
        List<Map> taskList = new ArrayList<Map>();

        for(Task task : list){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", task.getId());
            map.put("name", task.getName());
            map.put("insertTime", task.getCreateTime());
            map.put("description", task.getDescription());
            map.put("formKey", task.getFormKey());
            map.put("owner", task.getOwner());
            map.put("assignee", task.getAssignee());
            map.put("category", task.getCategory());
            map.put("processInstanceId", task.getProcessInstanceId());
            map.put("processDefinitionId", task.getProcessDefinitionId());

            taskList.add(map);
        }

        pageData.setData(taskList);
        pageData.setTotal(total);
        pkg.getBody().setData(pageData);

        return pkg;
    }
}