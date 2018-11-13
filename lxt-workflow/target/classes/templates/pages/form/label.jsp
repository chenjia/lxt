<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文本内容</title>
</head>
<body>
	<form id="labelConfigForm">
		<div class="easyui-accordion" data-options="fit:true" >
			<div title="基本信息" data-options="iconCls:'icon-basicinfo'" style="padding:10px;">
				<table class="form-config-table" width="100%">
					<tr>
						<td align="right">
							<label for="lc_class">Class：</label>
						</td>
						<td>
							<input id="Lc_class" name="class" onblur="labelPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<span class="required">*</span>
							<label for="lc_text">文本内容：</label>
						</td>
						<td>
							<input id="lc_text" name="text" onblur="labelPropertyUpdate();" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label for="lc_style">Style：</label>
						</td>
						<td>
							<textarea id="lc_style" name="Style" onblur="labelPropertyUpdate();" data-options="alidType:'username[4,32]'" class="easyui-validatebox searchbox" style="width:150px;height:60px;"></textarea>
						</td>
					</tr>
				</table>
			</div>
			<div title="组件设置" data-options="iconCls:'icon-node'" style="padding:4px;overflow:auto;">
		    	<table class="form-config-table" width="100%">
					<tr>
						<td width="35%" align="right">
							<label for="lc_type">组件类型：</label>
						</td>
						<td width="65%">
							<input id="lc_type" name="type" disabled="disabled" data-options="required:true,tipPosition:'bottom',validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
				</table>
		    </div>
		</div>
	</form>
	<script type="text/javascript">
		function labelPropertyInit(){
			var data = $('.selection').data('component');
			$('#labelConfigForm').form('load',data);
			$('#labelConfigForm').css({height:($(document).height()-35)+'px','overflow-y':'auto'});
		}
		
		function labelPropertyUpdate(){
			var data = $('.selection').data('component');
			var c = $('#lc_class').val();
			var text = $('#lc_text').val();
			var style = $('#lc_style').val();
			data.class = c;
			data.text = text;
			data.style = style;
			
			var label = $('.selection .field-label');
			label.html(text);
			label.attr({style:style});
		}
		
		labelPropertyInit();
	</script>
</body>
</html>