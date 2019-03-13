//package com.lxt.ms.workflow.utils;
//
//import java.util.HashMap;
//import java.util.Map;
//import org.jbpm.api.ExecutionService;
//import org.jbpm.api.TaskService;
//import org.jbpm.api.cmd.Command;
//import org.jbpm.api.cmd.Environment;
//import org.jbpm.pvm.internal.task.TaskImpl;
//
//import com.lxt.common.utils.CheckUtils;
//import com.lxt.workflow.constant.NodeType;
//import com.lxt.workflow.constant.Variable;
//import com.lxt.workflow.dao.impl.NodeDAOImpl;
//import com.lxt.workflow.entity.pojo.LxtNode;
//import com.lxt.workflow.entity.vo.TaskSubmitVO;
//import com.lxt.workflow.utils.SpringUtils;
//
//public class TaskSubmitCmd implements Command<Boolean>{
//	private NodeDAOImpl nodeDAO = null;
//	private TaskSubmitVO taskSubmitVo = null;
//
//	public TaskSubmitCmd(TaskSubmitVO taskSubmitVo) {
//		this.taskSubmitVo = taskSubmitVo;
//		this.nodeDAO = (NodeDAOImpl) SpringUtils.getBean("nodeDAO");
//	}
//
//	public Boolean execute(Environment environment) throws Exception {
//		TaskService ts = environment.get(TaskService.class);
//		ExecutionService es = environment.get(ExecutionService.class);
//		String taskId = taskSubmitVo.getTaskId();
//		String transition = taskSubmitVo.getTransition();
//
//		TaskImpl task = (TaskImpl) ts.getTask(taskId);
//		LxtNode node = nodeDAO.getPOJOById(task.getDescription());
//		Map<String,Object> variables = new HashMap<String, Object>();
//		variables.put(Variable.WORKFLOW_PRE_USER_ID, taskSubmitVo.getTASK_ASSIGNER());
//		variables.put(Variable.WORKFLOW_PRE_TRUE_NAME, taskSubmitVo.getTASK_HANDLER());
//		variables.put(Variable.WORKFLOW_PRE_STEP_NAME, taskSubmitVo.getTaskName());
//		es.createVariables(task.getExecutionId(), variables, false);
//
//		if(CheckUtils.isNotEmpty(transition)){
//			ts.completeTask(taskId,transition);
//		}else{
//			if(NodeType.JOINTLY.equals(node.getNodeType())){
////				boolean result = map.get("jointlyResult").equals("1");
////				JSONObject config = JSONObject.fromObject(node.getNodeJointlysignConfig());
////				Jointly jointly = JointlyFactory.getJointly(config.getInt("jointlysignWay"));
////				jointly.sign(task,result,node.getNodeJointlysignConfig());
//			}else{
//				ts.completeTask(taskId);
//			}
//		}
//		return true;
//	}
//}
