package com.lxt.ms.workflow.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.common.utils.UUIDUtils;
import com.lxt.ms.workflow.bpmn.entity.Definitions;
import com.lxt.ms.workflow.constant.ProcessConstant;
import com.lxt.ms.workflow.graph.entity.*;
import com.lxt.ms.workflow.mapper.NodeMapper;
import com.lxt.ms.workflow.mapper.ProcessMapper;
import com.lxt.ms.workflow.mapper.ext.ProcessExtMapper;
import com.lxt.ms.workflow.model.Node;
import com.lxt.ms.workflow.model.NodeExample;
import com.lxt.ms.workflow.model.Process;
import com.lxt.ms.workflow.model.ProcessExample;
import com.lxt.ms.workflow.model.ProcessWithBLOBs;
import com.lxt.ms.workflow.service.api.ProcessService;
import com.lxt.ms.workflow.utils.WorkflowUtils;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricDetailQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.HistoricActivityInstanceEntityImpl;
import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.TaskInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service("processService")
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private ProcessEngine processEngine = null;

    @Autowired
    private RepositoryService repositoryService = null;

    @Autowired
    private RuntimeService runtimeService = null;

    @Autowired
    private TaskService taskService = null;

    @Autowired
    private HistoryService historyService = null;

    @Autowired
    private ManagementService managementService = null;

    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private NodeMapper nodeMapper;

    @Autowired
    private ProcessExtMapper processExtMapper;

    @Override
    public Packages save(String graphXml) throws APIException {
        Packages pkg = new Packages();
        Date date = new Date();
        Map<String, Object> idMap = new HashMap<String, Object>();

        MxGraphModel graphModel = WorkflowUtils.toGraphObj(graphXml);
        Root root = graphModel.getRoot();
        Workflow graphWorkflow = root.getWorkflow();
        List<Vertex> vertexs = root.getVertexs();
        List<Edge> edges = root.getEdges();

        Definitions definitions = null;
        try{
            definitions = WorkflowUtils.parseBpmn(graphModel);
        }catch(Exception e){
            e.printStackTrace();
            throw new APIException("流程xml转化失败！");
        }

        ProcessWithBLOBs processWithBLOBs = null;
        List<Node> nodeList = null;
        String graphWorkflowId = graphWorkflow.getWorkflowId();
        if(StringUtils.isEmpty(graphWorkflowId) || "0".equalsIgnoreCase(graphWorkflowId)){
            processWithBLOBs = new ProcessWithBLOBs();
            processWithBLOBs.setProcessId(UUIDUtils.UUID());
            processWithBLOBs.setInsertTime(date);
            processWithBLOBs.setPublishStatus(ProcessConstant.UNPUBLISH);
            nodeList = new ArrayList<Node>();
        }else{
            processWithBLOBs = processMapper.selectByPrimaryKey(graphWorkflowId);
            NodeExample nodeExample = new NodeExample();
            NodeExample.Criteria criteria = nodeExample.createCriteria();
            System.out.println("criteria:"+criteria);
            criteria.andProcessIdEqualTo(processWithBLOBs.getProcessId());
            System.out.println("criteria:"+criteria);
            nodeList = nodeMapper.selectByExampleWithBLOBs(nodeExample);
        }

        try {
            BeanUtils.copyProperties(processWithBLOBs, graphWorkflow);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new APIException(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new APIException(e);
        }


        for(Vertex vertex : vertexs){
            String graphId = vertex.getId();
            String nodeId = vertex.getNodeId();
            Node node = null;
            for(Node item : nodeList){
                if(!StringUtils.isEmpty(nodeId) && nodeId.equals(item.getNodeId())){
                    node = item;
                    node.setCellId(graphId);
                    break;
                }
            }

            try {
                if(node == null){
                    node = new Node();
                    BeanUtils.copyProperties(node, vertex);
                    node.setCellId(graphId);
                    node.setProcessId(processWithBLOBs.getProcessId());
                    node.setNodeId(UUIDUtils.UUID());
                    nodeMapper.insert(node);
                }else{
                    BeanUtils.copyProperties(node, vertex);
                    nodeMapper.updateByPrimaryKey(node);
                }
                idMap.put(node.getCellId(), node.getNodeId());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new APIException(e);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                throw new APIException(e);
            }
        }

        for(Vertex vertex : vertexs){
            vertex.setNodeId((String) idMap.get(vertex.getId()));
        }



        graphWorkflow.setWorkflowId(processWithBLOBs.getProcessId());
        graphXml = WorkflowUtils.toGraphXml(graphModel);
        processWithBLOBs.setProcessKey(graphWorkflow.getWorkflowKey());
        processWithBLOBs.setName(graphWorkflow.getLabel());
        processWithBLOBs.setGraphXml(graphXml);
        processWithBLOBs.setVersionNo(graphWorkflow.getVersion());
        processWithBLOBs.setUpdateTime(date);
        String bpmnXml = WorkflowUtils.toBpmnXml(definitions);
        if(!bpmnXml.equalsIgnoreCase(processWithBLOBs.getBpmnXml())){
            DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
            Deployment deployment = deploymentBuilder.addString(processWithBLOBs.getProcessKey()+".bpmn20.xml", bpmnXml).name(processWithBLOBs.getProcessKey()).deploy();
            String deployId = deployment.getId();
            processWithBLOBs.setDeployeId(deployId);
        }
        processWithBLOBs.setBpmnXml(bpmnXml);

        idMap.put("workflowId", processWithBLOBs.getProcessId());
        if(StringUtils.isEmpty(graphWorkflowId)){
            processMapper.insert(processWithBLOBs);
        }else{
            processMapper.updateByPrimaryKeyWithBLOBs(processWithBLOBs);
        }

        pkg.getBody().setData(idMap);

        return pkg;
    }

    @Override
    public Packages publish(String pid, int status) throws APIException {
        Packages pkg = new Packages();

        Date date = new Date();
        ProcessWithBLOBs process = processMapper.selectByPrimaryKey(pid);
        process.setPublishStatus(status);
        process.setUpdateTime(date);
        process.setPublishTime(date);
        processMapper.updateByPrimaryKey(process);

        return pkg;
    }

    @Override
    public Packages delete(String pid) throws APIException {
        Packages pkg = new Packages();

        ProcessWithBLOBs process = processMapper.selectByPrimaryKey(pid);
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(process.getDeployeId()).singleResult();
        if(deployment!=null){
            repositoryService.deleteDeployment(process.getDeployeId(), true);
        }
        processMapper.deleteByPrimaryKey(pid);

        return pkg;
    }

    @Override
    public Packages deleteAll() throws APIException {
        Packages pkg = new Packages();

        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        for(ProcessDefinition pd : list){
            repositoryService.deleteDeployment(pd.getDeploymentId(), true);
        }
        processMapper.deleteByExample(new ProcessExample());
        nodeMapper.deleteByExample(new NodeExample());

        return pkg;
    }

    @Override
    public Packages start(String $userId, String pid, Map<String, Object> variables) throws APIException {
        Packages pkg = new Packages();

        ProcessWithBLOBs process = processMapper.selectByPrimaryKey(pid);

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(process.getDeployeId()).singleResult();
        if(processDefinition != null){
            String instanceId = null;
            if(variables == null){
                variables = new HashMap<String, Object>();
                variables.put("INITIATOR" , $userId);
            }
            instanceId = runtimeService.startProcessInstanceById(processDefinition.getId(), variables).getId();
            pkg.getBody().setData(instanceId);
        }else{
            pkg.getHead().setStatus(500);
            pkg.getHead().setMsg("启动流程不存在");
        }

        return pkg;
    }

    @Override
    public Packages details(String pid) throws APIException {
        Packages pkg = new Packages();

        ProcessWithBLOBs process = processMapper.selectByPrimaryKey(pid);
        pkg.getBody().setData(process);

        return pkg;
    }

    public Packages list(ProcessExample example, PageData pageData) throws APIException {
        Packages pkg = new Packages();

        Page page = PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize(), true);
        List<com.lxt.ms.workflow.model.Process> processList = processMapper.selectByExample(example);
        pageData.setData(processList);
        pageData.setTotal(page.getTotal());
        pkg.getBody().setData(pageData);

        return pkg;
    }

    @Override
    public Packages logs(String instanceId) throws APIException {
        Packages pkg = new Packages();



        return pkg;
    }

    @Override
    public Packages history(String processInstanceId) throws APIException {
        Packages pkg = new Packages();

        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list();
        List<Map<String, Object>> currentList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> historyList = new ArrayList<Map<String, Object>>();
        List<String> keyList = new ArrayList<String>();
        Map<String, Object> varMap = new HashMap<String, Object>();
        Map<String, Object> tempMap = null;

        for(HistoricTaskInstance item : list){
            keyList.add("OUT_"+item.getTaskDefinitionKey());
        }
        varMap = runtimeService.getVariables(processInstanceId, keyList);

        for(HistoricTaskInstance item : list){
            tempMap = new HashMap<String, Object>();
            tempMap.put("activity", item.getName());
            String transition = (String) varMap.get("OUT_"+item.getTaskDefinitionKey());
            if(transition != null){
                tempMap.put("transition", transition.split("\\.")[0]);
            }
            if(item.getEndTime() == null){
                currentList.add(tempMap);
            }else{
                historyList.add(tempMap);
            }
        }

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("current", currentList);
        map.put("history", historyList);

        pkg.getBody().setData(map);

        return pkg;
    }

    @Override
    public Packages graph(String taskId, String $userId) throws APIException {
        Packages pkg = new Packages();

        Map<String, Object> params = new HashMap<>();
        params.put("taskId", taskId);
        params.put("userId", $userId);
        Process process = processExtMapper.queryGraphXmlByTaskId(params);
        pkg.getBody().setData(process);

        return pkg;
    }

    /*
    public Map<String, Object> queryHistoryStep(String processInstanceId)
            throws ServiceException {
        Map<String,Object> historyMap = new HashMap<String, Object>();

        try {
            Map<String,Object> json = new HashMap<String, Object>();
            List<HistoryActivityInstance> history = jbpmUtils.getHistoryService().createHistoryActivityInstanceQuery().processInstanceId(processInstanceId).list();
            List<Map<String,Object>> currentList = new ArrayList<Map<String,Object>>();
            List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
            for(HistoryActivityInstance temp : history){
                HistoryTaskInstanceImpl haii = (HistoryTaskInstanceImpl) temp;
                if(haii.getEndTime()==null){
                    TaskImpl task = new TaskImpl();
                    List<Task> tasks = jbpmUtils.getTaskService().createTaskQuery().activityName(haii.getActivityName()).executionId(haii.getExecutionId()).list();
                    if(tasks.size()==1){
                        task = (TaskImpl) tasks.get(0);
                    }
                    Map<String, Object> currentMap = new HashMap<String, Object>();
                    currentMap.put("activity", haii.getActivityName());
                    currentMap.put("eid", haii.getExecutionId());
                    if(!CheckUtils.isNotEmpty(task.getAssignee())){

                        LxtUser assigner = userDAO.getPOJOById(task.getAssignee());
                        if(assigner!=null){
                            currentMap.put("assigner", assigner.getUsername());
                        }else{
                            currentMap.put("assigner", "");
                        }
                    }

                    Set<ParticipationImpl> participations = task.getParticipations();
                    Set<String> parSet = new HashSet<String>(participations.size());
                    if(participations.size()!=0){
                        for(Participation p : participations){
                            parSet.add(p.getUserId());
                        }
                        List<LxtUser> candidate = userDAO.queryByIds(parSet);
                        parSet.clear();
                        for(LxtUser user : candidate){
                            parSet.add(user.getUsername());
                        }
                        currentMap.put("candidate", parSet);
                    }

                    parSet.clear();
                    if(tasks.size()>1){
                        for(Task t : tasks){
                            TaskImpl tempTask = (TaskImpl) t;
                            if(tempTask.getSuperTask()!=null){
                                parSet.add(tempTask.getAssignee());
                            }
                        }
                        List<LxtUser> subAssigner = userDAO.queryByIds(parSet);
                        parSet.clear();
                        for(LxtUser user : subAssigner){
                            parSet.add(user.getUsername());
                        }
                        currentMap.put("subAssigner", parSet);
                    }
                    currentList.add(currentMap);
                    continue;
                }
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("activity", temp.getActivityName());
                map.put("eid", temp.getExecutionId());
                map.put("transition", haii.getTransitionName());
                map.put("createTime", temp.getStartTime());
                map.put("endTime", temp.getEndTime());
                LxtUser user = userDAO.getPOJOById(haii.getHistoryTask().getAssignee());
                map.put("assigner", user.getUsername());
                map.put("userId", user.getUserId());
                mapList.add(map);
            }
            historyMap.put("history", mapList);
            historyMap.put("current", currentList);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }

        return historyMap;
    }
*/
}