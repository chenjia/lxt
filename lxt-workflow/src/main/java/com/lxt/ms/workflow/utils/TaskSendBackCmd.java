//package com.lxt.ms.workflow.utils;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import com.lxt.ms.common.utils.SpringUtils;
//import com.lxt.ms.workflow.mapper.NodeMapper;
//import com.netflix.discovery.converters.Auto;
//import org.activiti.engine.HistoryService;
//import org.activiti.engine.RepositoryService;
//import org.activiti.engine.impl.interceptor.Command;
//import org.activiti.engine.repository.ProcessDefinitionQuery;
//import org.activiti.engine.runtime.Execution;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class TaskSendBackCmd implements Command<Boolean> {
//	@Autowired
//	private NodeMapper nodeMapper;
//
//	private String taskId;
//	private String nodeId;
//	private String target;
//	private LxtNode node;
//	private TaskService ts;
//	private HistoryService hs;
//	private ExecutionService es;
//	private RepositoryService rs;
//
//	public TaskSendBackCmd(String taskId, String nodeId, String target) {
//		this.taskId = taskId;
//		this.nodeId = nodeId;
//		this.target = target;
//		this.nodeDAO = (NodeDAOImpl) SpringUtils.getBean("nodeDAO");
//	}
//
//	public Boolean execute(Environment environment) throws Exception {
//		ts = environment.get(TaskService.class);
//		hs = environment.get(HistoryService.class);
//		es = environment.get(ExecutionService.class);
//		rs = environment.get(RepositoryService.class);
//
//		node = nodeDAO.getPOJOById(nodeId);
////		Map<String,String> map = NodeUtils.getLxtNodeAttrsMap(node.getLxtNodeAttrs());
//		Map<String,String> map = new HashMap<String, String>();
//		TaskImpl task = (TaskImpl) ts.getTask(taskId);
//		ExecutionImpl execution = task.getExecution();
//		ActivityImpl sourceActivity = execution.getActivity();
//		String isSendback = map.get(Variable.IS_SENDBACK);
//		if(isSendback.equals(WorkflowStatus.SENDBACK_FORBIDDEN)){
//			throw new Exception("该节点不允许退回！");
//		}
//		String sendbackWay = map.get(Variable.SENDBACK_WAY);
//		ActivityImpl targetActivity = getTargetActivity(sourceActivity,execution,sendbackWay);
//
//		if(sourceActivity.getName().equals(targetActivity.getName())){
//			throw new Exception("不能退回到自身节点！");
//		}
//
//		TransitionImpl sendbackTransition = createTransition(sourceActivity,targetActivity);
//
//		Map<String,Object> variables = new HashMap<String, Object>();
//		variables.put(Variable.WORKFLOW_PRE_USER_ID, map.get(Variable.TASK_ASSIGNER));
//		variables.put(Variable.WORKFLOW_PRE_TRUE_NAME, map.get(Variable.TASK_HANDLER));
//		variables.put(Variable.WORKFLOW_PRE_STEP_NAME, sourceActivity.getName());
//		es.setVariables(execution.getId(), variables);
//		ts.completeTask(taskId, sendbackTransition.getName());
//		sourceActivity.removeOutgoingTransition(sendbackTransition);
//
//		return true;
//	}
//
//	private TransitionImpl createTransition(ActivityImpl sourceActivity, ActivityImpl targetActivity) throws Exception{
//		TransitionImpl newTransition = null;
//		newTransition = sourceActivity.createOutgoingTransition();
//		newTransition.setName(sourceActivity.getName()+" sendback to "+targetActivity.getName());
//		newTransition.setDestination(targetActivity);
//		return newTransition;
//	}
//
//	private ActivityImpl getTargetActivity(ActivityImpl sourceActivity, Execution execution,
//			String sendbackWay) throws Exception {
//		ActivityImpl targetActivity = null;
//		List<TransitionImpl> incomingTransitions = (List<TransitionImpl>) sourceActivity.getIncomingTransitions();
//
//		if(sendbackWay.equals(WorkflowStatus.SENDBACK_SOURCE)){
//			targetActivity = getSourceTarget(sourceActivity,execution);
//		}else if(sendbackWay.equals(WorkflowStatus.SENDBACK_PREVIOUS)){
//			targetActivity = getPreviousTarget(sourceActivity,execution);
//		}else if(sendbackWay.equals(WorkflowStatus.SENDBACK_HISTORY)){
//			targetActivity = getHistoryTarget(sourceActivity,execution);
//		}else if(sendbackWay.equals(WorkflowStatus.SENDBACK_ASSIGNED)){
//			targetActivity = getAssignedTarget(sourceActivity,execution);
//		}
//
//		return targetActivity;
//	}
//
//	/**
//	 * 获得来源节点
//	 * @return
//	 */
//	private ActivityImpl getSourceTarget(ActivityImpl sourceActivity,Execution execution) throws Exception{
//		ActivityImpl targetActivity = null;
//
//		HistoryActivityInstanceQuery haiq = hs.createHistoryActivityInstanceQuery();
//		haiq.executionId(execution.getId());
//		haiq.orderDesc("startTime");
//		List<HistoryActivityInstance> historyActivityInstances = haiq.list();
//		if(historyActivityInstances.size()<2){
//			throw new Exception("无可退回节点！");
//		}else{
//			HistoryActivityInstanceImpl haiImpl = (HistoryActivityInstanceImpl) historyActivityInstances.get(1);
//			targetActivity = haiImpl.getActivity();
//		}
//
//		return targetActivity;
//	}
//
//	/**
//	 * 获得上一步节点
//	 * @return
//	 */
//	private ActivityImpl getPreviousTarget(ActivityImpl sourceActivity,Execution execution) throws Exception{
//		ActivityImpl targetActivity = null;
//
//		List<TransitionImpl> incomingTransitions = (List<TransitionImpl>) sourceActivity.getIncomingTransitions();
//		if(incomingTransitions.size()==1){
//			targetActivity = incomingTransitions.get(0).getSource();
//		}else{
//			HistoryActivityInstanceQuery haiq = hs.createHistoryActivityInstanceQuery();
//			haiq.executionId(execution.getId());
//			haiq.orderDesc("startTime");
//			List<HistoryActivityInstance> historyActivityInstances = haiq.list();
//			Set<String> sourceActivityNames = new HashSet<String>();
//			for(TransitionImpl transition : incomingTransitions){
//				ActivityImpl sourceActivityImpl = transition.getSource();
//				sourceActivityNames.add(sourceActivityImpl.getName());
//			}
//			for(HistoryActivityInstance hai : historyActivityInstances){
//				if(sourceActivityNames.contains(hai.getActivityName())){
//					HistoryActivityInstanceImpl haiImpl = (HistoryActivityInstanceImpl)hai;
//					targetActivity = haiImpl.getActivity();
//				}
//			}
//		}
//
//		return targetActivity;
//	}
//
//	/**
//	 * 获得历史节点
//	 * @return
//	 */
//	private ActivityImpl getHistoryTarget(ActivityImpl sourceActivity,Execution execution) throws Exception{
//		ActivityImpl targetActivity = null;
//
//		String deployeId = node.getLxtWorkflow().getDeployeId();
//		ProcessDefinitionQuery processDefinitionQuery = rs.createProcessDefinitionQuery();
//		processDefinitionQuery.deploymentId(deployeId);
//		ProcessDefinitionImpl pd = (ProcessDefinitionImpl)processDefinitionQuery.uniqueResult();
//		targetActivity = pd.getActivity(target);
//
//		return targetActivity;
//	}
//
//	/**
//	 * 获得指定节点
//	 * @return
//	 */
//	private ActivityImpl getAssignedTarget(ActivityImpl sourceActivity,Execution execution) throws Exception{
//		ActivityImpl targetActivity = null;
//
//		String deployeId = node.getLxtWorkflow().getDeployeId();
////		Map<String,String> nodeAttrs = NodeUtils.getLxtNodeAttrsMap(node.getLxtNodeAttrs());
//		Map<String,String> nodeAttrs = new HashMap<String, String>();
//		String target = nodeAttrs.get("sendbackTargetText");
//		ProcessDefinitionQuery processDefinitionQuery = rs.createProcessDefinitionQuery();
//		processDefinitionQuery.deploymentId(deployeId);
//		ProcessDefinitionImpl pd = (ProcessDefinitionImpl)processDefinitionQuery.uniqueResult();
//		targetActivity = pd.getActivity(target);
//
//		return targetActivity;
//	}
//}
