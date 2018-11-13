<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<script type="text/javascript" src="../../js/workflow/doTask.js"></script>
	<div class="easyui-layout" data-options="fit:true,border:true">
		 
		<div data-options="region:'center',title:'表单'">
			<div class="easyui-panel" data-options="fit:true" style="padding:2px;">
				<div class="doTaskTool" style="position:absolute;right:5px;bottom:5px;height:30px;display:none;">
					<table width="100%">
						<tr>
							<td>
								<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="doTaskHandler.sendback();">退回</a>
								<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-submit'" onclick="doTaskHandler.submit();">提交</a>
							</td>
						</tr>
					</table>
				</div>
				<iframe width="100%" height="100%" frameborder="0" src="../form/customForm.jsp"></iframe>
			</div>
			
			<div class="easyui-panel" data-options="fit:true,collapsed:true" style="display:none;">
				<iframe width="100%" height="100%" frameborder="0" src=""></iframe>
			</div>
			<div class="easyui-panel" data-options="fit:true,collapsed:true" style="padding:2px;display:none;">
				<iframe width="100%" height="100%" frameborder="0" src=""></iframe>
			</div>
			<div class="easyui-panel" data-options="fit:true,collapsed:true" style="padding:2px;display:none;">
				<iframe width="100%" height="100%" frameborder="0" src=""></iframe>
			</div>
			<div class="easyui-panel" data-options="fit:true,collapsed:true" style="padding:2px;display:none;">
				<iframe width="100%" height="100%" frameborder="0" src=""></iframe>
			</div>
		</div>
		<div data-options="region:'east',split:true,collapsed:true,title:'视图'" style="width:100px;">
			<table class="doTaskView" width="90" style="display:none;">
				<tr height="80" align="center">
					<td>
						<div style="width:50px;height:50px;border:1px solid red;" onclick="doTaskHandler.showForm()">
							<a href="javascript:void(0)">
								<img src="../../images/icons/form_32.png"/>
								<br/>表单
							</a>
						</div>
					</td>
				</tr>
				<tr height="80" align="center">
					<td>
						<div onclick="doTaskHandler.showGraph()">
							<img src="../../images/icons/graph_32.png"/>
							<br/>流程图
						</div>
					</td>
				</tr>
				<tr height="80" align="center">
					<td>
						<div onclick="doTaskHandler.showSuggestion()">
							<img src="../../images/icons/suggestion_32.png"/>
							<br/>意见列表
						</div>
					</td>
				</tr>
				<tr height="80" align="center">
					<td>
						<div onclick="doTaskHandler.showAttach()">
							<img src="../../images/icons/attach_32.png"/>
							<br/>附件
						</div>
					</td>
				</tr>
				<tr height="80" align="center">
					<td>
						<div onclick="doTaskHandler.showLog()">
							<img src="../../images/icons/log_32.png"/>
							<br/>日志
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div id="sendbackWin"></div>
</body>
</html>
