package com.lxt.ms.workflow.service.impl;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.workflow.service.api.TaskService;
import org.activiti.engine.*;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntityImpl;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Packages submit(String taskId) throws APIException {
        return null;
    }

    @Override
    public Packages rollback(String taskId, String nodeId, String target) throws APIException {
        return null;
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

        Map<String, Object> map = null;
        for(Task task : list){
            map = new HashMap<String, Object>();
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