<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文本框</title>
</head>
<body>
	<form id="textConfigForm">
		<div class="easyui-accordion" data-options="fit:true" >
			<div title="基本信息" data-options="iconCls:'icon-basicinfo'" class="property-form">
				<table class="form-config-table" width="100%">
					<tr>
						<td align="right">
							<span class="required">*</span>
							<label for="tc_id">Id：</label>
						</td>
						<td>
							<input id="tc_id" name="id" onblur="textPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<span class="required">*</span>
							<label for="tc_name">Name：</label>
						</td>
						<td>
							<input id="tc_name" name="name" onblur="textPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label for="tc_className">Class：</label>
						</td>
						<td>
							<input id="tc_className" name="className" onblur="textPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label for="tc_label">Label：</label>
						</td>
						<td>
							<input id="tc_label" name="label" onblur="textPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label for="tc_style">Style：</label>
						</td>
						<td>
							<textarea id="tc_style" name="Style" onblur="textPropertyUpdate();" data-options="alidType:'username[4,32]'" class="easyui-validatebox searchbox" style="width:150px;height:60px;"></textarea>
						</td>
					</tr>
				</table>
			</div>
			<div title="组件设置" data-options="iconCls:'icon-node'" class="property-form">
		    	<table class="form-config-table" width="100%">
					<tr>
						<td width="35%" align="right">
							<label for="tc_type">组件类型：</label>
						</td>
						<td width="65%">
							<input id="tc_type" name="type" disabled="disabled" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<span class="required">*</span>
							<label for="tc_fieldName">名称：</label>
						</td>
						<td>
							<input id="tc_fieldName" name="fieldName" onblur="textPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label for="tc_validateRule">校验规则：</label>
						</td>
						<td>
							<input id="tc_validateRule" name="validateRule" disabled="disabled" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label for="tc_memo">备注：</label>
						</td>
						<td>
							<textarea id="tc_memo" name="memo" onblur="textPropertyUpdate();" data-options="validType:'username[4,32]'" class="easyui-validatebox searchbox" style="width:150px;height:60px;"></textarea>
						</td>
					</tr>
				</table>
		    </div>
		    <div title="数据库映射" data-options="iconCls:'icon-connect'" class="property-form">
				<table class="form-config-table" width="100%">
					<tr>
						<td align="right">
							<span class="required">*</span>
							<label for="tc_tableName">表名：</label>
						</td>
						<td>
							<input id="tc_tableName" name="tableName" onblur="textPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<span class="required">*</span>
							<label for="tc_fieldCode">字段名：</label>
						</td>
						<td>
							<input id="tc_fieldCode" name="fieldCode" onblur="textPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<span class="required">*</span>
							<label for="tc_fieldType">字段类型：</label>
						</td>
						<td>
							<input id="tc_fieldType" name="fieldType" onblur="textPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<span class="required">*</span>
							<label for="tc_fieldLength">字段长度：</label>
						</td>
						<td>
							<input id="tc_fieldLength" name="fieldLength" onblur="textPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label for="tc_defaultValue">默认值：</label>
						</td>
						<td>
							<input id="tc_defaultValue" name="defaultValue" onblur="textPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label for="tc_notEmpty">非空约束：</label>
						</td>
						<td>
							<input type="checkbox" id="tc_notEmpty" name="notEmpty" value="1" style="width:20px;" onchange="textPropertyUpdate();"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label for="tc_isUnique">唯一约束：</label>
						</td>
						<td>
							<input type="checkbox" id="tc_isUnique" name="isUnique" value="1" style="width:20px;" onchange="textPropertyUpdate();"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label for="tc_createIndex">建立索引：</label>
						</td>
						<td>
							<input type="checkbox" id="tc_createIndex" name="createIndex" value="1" style="width:20px;" onchange="textPropertyUpdate();"/>
						</td>
					</tr>
				</table>
		    </div>
		</div>
	</form>
	<script type="text/javascript">
		function textPropertyInit(){
			var data = $('.selection').data('component');
			$('#textConfigForm').form('load',data);
			$('#textConfigForm').css({height:($(document).height()-35)+'px','overflow-y':'auto'});
			Lxt.editors.basicEditor({
				id:'tc_memo',
				title:'备注'
			});
		}
		
		function textPropertyUpdate(){
			var data = $('.selection').data('component');
			var id = $('#tc_id').val();
			var className = $('#tc_className').val();
			var name = $('#tc_name').val();
			var label = $('#tc_label').val();
			var style = $('#tc_style').val();
			var fieldName = $('#tc_fieldName').val();
			var validateRule = $('#tc_validateRule').val();
			var memo = $('#tc_memo').val();
			var tableName = $('#tc_tableName').val();
			var fieldCode = $('#tc_fieldCode').val();
			var fieldType = $('#tc_fieldType').val();
			var fieldLength = $('#tc_fieldLength').val();
			var defaultValue = $('#tc_defaultValue').val();
			var notEmpty = $('input[name=notEmpty]:checked').val()=='1'?'1':'0';
			var isUnique = $('input[name=isUnique]:checked').val()=='1'?'1':'0';
			var createIndex = $('input[name=createIndex]:checked').val()=='1'?'1':'0';
			
			data.id = id;
			data.className = className;
			data.name = name;
			data.label = label;
			data.style = style;
			data.fieldName = fieldName;
			data.validateRule = validateRule;
			data.memo = memo;
			data.tableName = tableName;
			data.fieldCode = fieldCode;
			data.fieldType = fieldType;
			data.fieldLength = fieldLength;
			data.defaultValue = defaultValue;
			data.notEmpty = notEmpty;
			data.isUnique = isUnique;
			data.createIndex = createIndex;
			
			$('.selection label').html(label);
		}
		
		textPropertyInit();
	</script>
</body>
</html>