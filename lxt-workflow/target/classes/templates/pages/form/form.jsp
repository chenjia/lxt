<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表单</title>
</head>
<body>
	<form id="formConfigForm">
		<div class="easyui-accordion" data-options="fit:true" >
			<div title="基本信息" data-options="iconCls:'icon-basicinfo'" class="property-form">
				<table class="form-config-table" width="100%">
					<tr>
						<td align="right">
							<label for="fc_id">Id：</label>
						</td>
						<td>
							<input id="fc_id" name="id" onblur="formPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label for="fc_name">Name：</label>
						</td>
						<td>
							<input id="fc_name" name="name" onblur="formPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label for="fc_className">Class：</label>
						</td>
						<td>
							<input id="fc_className" name="className" onblur="formPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<span class="required">*</span>
							<label for="fc_action">Action：</label>
						</td>
						<td>
							<input id="fc_action" name="action" onblur="formPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label for="fc_method">Method：</label>
						</td>
						<td>
							<select id="fc_method" name="method" onblur="formPropertyUpdate();" style="width:152px;">
								<option value="post">post</option>
								<option value="get">get</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label for="fc_enctype">Enctype：</label>
						</td>
						<td>
							<select id="fc_enctype" name="enctype" onblur="formPropertyUpdate();" style="width:152px;">
								<option value="application/x-www-form-urlencoded">default</option>
								<option value="multipart/form-data">multipart/form-data</option>
							</select>
						</td>
					</tr>
				</table>
			</div>
			<div title="组件设置" data-options="iconCls:'icon-basicinfo'" class="property-form">
				<table class="form-config-table" width="100%">
					<tr>
						<td align="right">
							<span class="required">*</span>
							<label for="fc_type">类型：</label>
						</td>
						<td>
							<input id="fc_type" name="type" disabled="disabled" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<span class="required">*</span>
							<label for="fc_formName">标题：</label>
						</td>
						<td>
							<input id="fc_formName" name="formName" onblur="formPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<span class="required">*</span>
							<label for="fc_taskName">任务名称：</label>
						</td>
						<td>
							<input id="fc_taskName" name="taskName" onblur="formPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<span class="required">*</span>
							<label for="fc_formCategory">表单分类：</label>
						</td>
						<td>
							<input id="fc_formCategory" name="formCategory" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label for="fc_icon">图标：</label>
						</td>
						<td>
							<input id="fc_icon" name="icon" onblur="formPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label for="fc_memo">备注：</label>
						</td>
						<td>
							<textarea id="fc_memo" name="memo" onblur="formPropertyUpdate();" data-options="validType:'username[4,32]'" class="easyui-validatebox searchbox" style="width:150px;height:60px;"></textarea>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label for="fc_cssText">样式：</label>
						</td>
						<td>
							<textarea id="fc_cssText" name="cssText" onblur="formPropertyUpdate();" data-options="validType:'username[4,32]'" class="easyui-validatebox searchbox" style="width:150px;height:60px;"></textarea>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label for="fc_scriptText">脚本：</label>
						</td>
						<td>
							<textarea id="fc_scriptText" name="scriptText" onblur="formPropertyUpdate();" data-options="validType:'username[4,32]'" class="easyui-validatebox searchbox" style="width:150px;height:60px;"></textarea>
						</td>
					</tr>
				</table>
			</div>
		    <div title="数据库映射" data-options="iconCls:'icon-connect'" class="property-form">
				<table class="form-config-table" width="100%">
					<tr>
						<td align="right">
							<span class="required">*</span>
							<label for="fc_tableName">表名：</label>
						</td>
						<td>
							<input id="fc_tableName" name="tableName" onblur="formPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<span class="required">*</span>
							<label for="fc_fieldPK">主键：</label>
						</td>
						<td>
							<input id="fc_fieldPK" name="fieldPK" onblur="formPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
				</table>
		    </div>
		</div>
	</form>
	<script type="text/javascript">
		var contextPath = '<%=request.getContextPath()%>/';
		function formPropertyInit(){
			var data = $('#drawPanel').data('component');
			$('#formConfigForm').form('load',$.extend({Style:data.style},data));
			$('#formConfigForm').css({height:($(document).height()-35)+'px','overflow-y':'auto'});
			Selector.initTreeSelector({
				id:'fc_formCategory',
				url:contextPath+'formCategory!queryByParent.action',
				valUrl:contextPath+'formCategory!queryByIds.action',
				title:'请选择表单分类',
				idField:'id',
				nameField:'name',
				toolbar:toolbar,
				iconCls:'icon-view'
			});
			
			Lxt.editors.basicEditor({
				id:'fc_memo',
				title:'备注'
			});
			
			Lxt.editors.basicEditor({
				id:'fc_cssText',
				title:'样式'
			});
			
			Lxt.editors.basicEditor({
				id:'fc_scriptText',
				title:'脚本'
			});
		}
		
		function formPropertyUpdate(){
			var data = $('#drawPanel').data('component');
			var id = $('#fc_id').val();
			var formCategoryId = $('#fc_formCategory').val();
			var name = $('#fc_name').val();
			var className = $('#fc_className').val();
			var action = $('#fc_action').val();
			var method = $('#fc_method').val();
			var enctype = $('#fc_enctype').val();
			var memo = $('#fc_memo').val();
			var cssText = $('#fc_cssText').val();
			var scriptText = $('#fc_scriptText').val();
			var formName = $('#fc_formName').val();
			var taskName = $('#fc_taskName').val();
			var icon = $('#fc_icon').val();
			var onsubmit = $('#fc_onsubmit').val();
			var onreset = $('#fc_onreset').val();
			var tableName = $('#fc_tableName').val();
			var fieldPK = $('#fc_fieldPK').val();
			
			data.id = id;
			data.formCategoryId = formCategoryId;
			data.name = name;
			data.className = className;
			data.action = action;
			data.method = method;
			data.enctype = enctype;
			data.cssText = cssText;
			data.scriptText = scriptText;
			data.memo = memo;
			data.formName = formName;
			data.taskName = taskName;
			data.icon = icon;
			data.tableName = tableName;
			data.fieldPK = fieldPK;
			
			if($.trim(formName)){
				$('#centerPanel').panel('setTitle',formName);
			}
			$('#drawPanel').panel('options').iconCls = icon;
		}
		
		formPropertyInit();
	</script>
</body>
</html>