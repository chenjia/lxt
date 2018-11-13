<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>按钮</title>
</head>
<body>
<form id="buttonConfigForm">
	<div class="easyui-accordion" data-options="fit:true,border:false">
	    <div title="表单属性" data-options="iconCls:'icon-tool',selected:true" style="padding:4px;overflow:auto;">
			<table class="form-config-table" width="100%">
				<tr>
					<td align="right">
						<span class="required">*</span>
						<label for="bc_id">Id：</label>
					</td>
					<td>
						<input id="bc_id" name="id" onblur="buttonPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label for="bc_class">Class：</label>
					</td>
					<td>
						<input id="bc_class" name="class" onblur="buttonPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span class="required">*</span>
						<label for="bc_text">内容：</label>
					</td>
					<td>
						<input id="bc_text" name="text" onblur="buttonPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label for="bc_icon">图标：</label>
					</td>
					<td>
						<input id="bc_icon" name="icon" onblur="buttonPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label for="bc_style">Style：</label>
					</td>
					<td>
						<textarea id="bc_style" name="Style" onblur="buttonPropertyUpdate();" data-options="alidType:'username[4,32]'" class="easyui-validatebox searchbox" style="width:150px;height:60px;"></textarea>
					</td>
				</tr>
			</table>
		</div>
	    <div title="组件设置" data-options="iconCls:'icon-node'" style="padding:4px;overflow:auto;">
	    	<table class="form-config-table" width="100%">
				<tr>
					<td width="35%" align="right">
						<label for="bc_type">组件类型：</label>
					</td>
					<td width="65%">
						<input id="bc_type" name="type" disabled="disabled" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
					</td>
				</tr>
			</table>
	    </div>
	    <div title="绑定事件" data-options="iconCls:'icon-connect'" style="padding:0px 4px;overflow:auto;">
			<tr>
				<td width="35%" align="right">
					<label for="fc_onclick">onclick：</label>
				</td>
				<td width="65%">
					<input id="fc_onclick" name="onclick" onblur="buttonPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
				</td>
			</tr>
	    </div>
	    <div title="数据库映射" data-options="iconCls:'icon-connect'" style="padding:0px 4px;overflow:auto;">
			
	    </div>
   	</div>
</form>
	<script type="text/javascript">
		function buttonPropertyInit(){
			var data = $('.selection').data('component');
			$('#buttonConfigForm').form('load',data);
			$('#buttonConfigForm').css({height:$(document).height()-35,'overflow-y':'auto'});
		}
		
		function buttonPropertyUpdate(){
			var data = $('.selection').data('component');
			var c = $('#bc_class').val();
			var text = $('#bc_text').val();
			var icon = $('#bc_icon').val();
			var style = $('#bc_style').val();
			var onclick = $('#bc_onclick').val();
			data.class = c;
			data.text = text;
			data.icon = icon;
			data.style = style;
			data.onclick = onclick;
			
			var label = $('.selection .field-button');
			label.linkbutton({
				text:text,
				iconCls: icon  
			});
			label.attr({style:style});
		}
		
		buttonPropertyInit();
	</script>
</body>
</html>