<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程</title>
<style type="text/css">
input,label{
	font:12px/24px verdana;
	vertical-align: middle;
}
</style>
</head>
<body>
	<form id="workflowConfigForm" style="padding:10px;">
		<table class="workflow-config-table" width="100%">
			<tr height="50">
				<td width="35%" align="right">
					<label for="wc_workflowKey"><span class="required">*</span>流程Key：</label>
				</td>
				<td width="65%">
					<input id="wc_workflowKey" name="workflowKey" data-options="required:true,tipPosition:'left',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label for="wc_name"><span class="required">*</span>流程名称：</label>
				</td>
				<td>
					<input id="wc_name" name="label" data-options="required:true,tipPosition:'left',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label for="wc_version"><span class="required">*</span>流程版本：</label>
				</td>
				<td>
					<input id="wc_version" name="version" data-options="required:true,tipPosition:'left',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label for="wc_status"><span class="required">*</span>流程状态：</label>
				</td>
				<td>
					<input type="radio" id="wc_status_open" name="status" value="1"/>
					<label for="wc_status_open" style="font-family: Verdana">开启</label>
					<input type="radio" id="wc_status_close" name="status" value="0"/>
					<label for="wc_status_close">关闭</label>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label for="wc_manual">手动触发：</label>
				</td>
				<td>
					<input id="wc_manual" name="manual" data-options="validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label for="wc_startRule">发起规则：</label>
				</td>
				<td>
					<input id="wc_startRule" name="startRule" data-options="alidType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label for="wc_beforeInEvent">前置事件：</label>
				</td>
				<td>
					<input id="wc_beforeInEvent" name="beforeInEvent" data-options="validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label for="wc_afterInEvent">发起事件：</label>
				</td>
				<td>
					<input id="wc_afterInEvent" name="afterInEvent" data-options="validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label for="wc_beforeOutEvent">结束事件：</label>
				</td>
				<td>
					<input id="wc_beforeOutEvent" name="beforeOutEvent" data-options="validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label for="wc_afterOutEvent">后置事件：</label>
				</td>
				<td>
					<input id="wc_afterOutEvent" name="afterOutEvent" data-options="validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label for="wc_memo">流程描述：</label>
				</td>
				<td>
					<textarea id="wc_memo" name="memo" data-options="validType:'username[4,32]'" class="easyui-validatebox searchbox" style="width:150px;height:60px;"></textarea>
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		function workflowPropertyInit(cell){
			var xmlObj = cell.getValue().attributes;
			var obj = {};
			for (var j = 0; j < xmlObj.length; j++) {
				var attr = xmlObj.item(j);
				obj[attr.nodeName] = attr.nodeValue;
			}
			$('#workflowConfigForm').form('load',obj);
		}
		
		function workflowPropertyUpdate(cell){
			cell.setAttribute('workflowKey',$('#wc_workflowKey').val());
			cell.setAttribute('label',$('#wc_name').val());
			cell.setAttribute('version',$('#wc_version').val());
			cell.setAttribute('status',$('input[name=status]:checked').val());
			cell.setAttribute('manual',$('#wc_manual').val());
			cell.setAttribute('startRule',$('#wc_startRule').val());
			cell.setAttribute('beforeInEvent',$('#wc_beforeInEvent').val());
			cell.setAttribute('afterInEvent',$('#wc_afterInEvent').val());
			cell.setAttribute('beforeOutEvent',$('#wc_beforeOutEvent').val());
			cell.setAttribute('afterOutEvent',$('#wc_afterOutEvent').val());
			cell.setAttribute('memo',$('#wc_memo').val());
		}
	</script>
</body>
</html>