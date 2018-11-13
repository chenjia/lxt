<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/form.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/globalConfig.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/lxtUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/form/formConfig.js"></script>
</head>
<body class="easyui-layout" style="margin:2px;">
	<div id="westPanel" data-options="region:'west',title:'工具箱',iconCls:'icon-briefcase',split:true" style="width:250px;">
		<div id="westAccordion" class="easyui-accordion" data-options="fit:true,border:false">
		    <div title="基本组件" data-options="iconCls:'icon-tool',selected:true" style="padding:4px;overflow:auto;">
		        <div id="component-label" class="form-component">
					<span class="field-label">请输入文本内容</span>
				</div>
				<div class="component-separate"></div>
				<div id="component-button" class="form-component">
					<a href="#" class="easyui-linkbutton" style="position:relative;top:3px;">按钮</a>
				</div>
				<div class="component-separate"></div>
				<div id="component-image" class="form-component">
					<img class="field-image" style="width:16px;height:16px;position:relative;top:7px;" src="../../images/logo.png" />
				</div>
				<div class="component-separate"></div>
				<div id="component-a" class="form-component">
					<a href="#" class="field-a">超链接</a>
				</div>
			</div>
		    <div title="表单组件" data-options="iconCls:'icon-node'" style="padding:4px;overflow:auto;">
				<div id="component-text" class="form-component">
					<label class="field-label">文本框：</label><input readonly="readonly" type="text" class="field-text"/>
				</div>
				<div class="component-separate"></div>
				<div id="component-textarea" class="form-component">
					<label class="field-label">文本域：</label><textarea readonly="readonly" class="field-textarea"></textarea>
				</div>
				<div class="component-separate"></div>
				<div id="component-select" class="form-component">
					<label class="field-label">下拉框：</label><select class="field-select"><option>--选择项--</option></select>
				</div>
				<div class="component-separate"></div>
				<div id="component-radio" class="form-component">
					<label class="field-label" style="position:relative;top:4px;">单选框：</label><input type="radio" checked="checked" class="field-radio"/><label class="field-label" style="position:relative;top:4px;">选项</label>
				</div>
				<div class="component-separate"></div>
				<div id="component-checkbox" class="form-component">
					<label class="field-label" style="position:relative;top:2px;">多选框：</label><input type="checkbox" checked="checked" class="field-checkbox"/><label class="field-label" style="position:relative;top:2px;">选项</label>
				</div>
				<div class="component-separate"></div>
				<div id="component-file" class="form-component">
					<input type="file" class="field-file" style="position:relative;top:5px;"/>
				</div>
				<div class="component-separate"></div>
				<div id="component-listSelector" class="form-component">
					列表选择器
				</div>
				<div class="component-separate"></div>
				<div id="component-treeSelector" class="form-component">
					树形选择器
				</div>
				<div class="component-separate"></div>
		    </div>
		    <div title="高级组件" data-options="iconCls:'icon-connect'" style="padding:4px;overflow:auto;">
				<div id="component-table" class="form-component">
					表格
				</div>
				<div id="component-datagrid" class="form-component">
					列表
				</div>
				<div id="component-tree" class="form-component">
					树
				</div>
				<div id="component-fieldset" class="form-component">
					组
				</div>
				<div id="component-tab" class="form-component">
					页签
				</div>
		    </div>
    	</div>
    </div>
    <div id="centerPanel" data-options="region:'center',title:'表单名称',iconCls:'icon-form'">
    	<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north',border:false" style="height:31px">
				<div id="toolbar" class="graph-toolbar">
		    		<table cellspacing="0" cellpadding="0">
			    		<tr>
				    		<td><a href="javascript:void(0);" title="保存" onclick="FormHandler.save();"><span class="btn"><span class="icon icon-save"></span></span></a></td>
				    		<td><a href="javascript:void(0);" title="打印" onclick=""><span class="btn"><span class="icon icon-print"></span></span></a></td>
				    		<td><div class="datagrid-btn-separator"></div></td>
				    		<td><a href="javascript:void(0);" title="复制" onclick=""><span class="btn"><span class="icon icon-copy"></span></span></a></td>
				    		<td><a href="javascript:void(0);" title="粘贴" onclick=""><span class="btn"><span class="icon icon-paste"></span></span></a></td>
				    		<td><a href="javascript:void(0);" title="删除" onclick="FormHandler.remove();"><span class="btn"><span class="icon icon-delete"></span></span></a></td>
				    		<td><div class="datagrid-btn-separator"></div></td>
				    		<td><a href="javascript:void(0);" title="上一步" onclick=""><span class="btn"><span class="icon icon-undo"></span></span></a></td>
				    		<td><a href="javascript:void(0);" title="下一步" onclick=""><span class="btn"><span class="icon icon-redo"></span></span></a></td>
				    		<td><div class="datagrid-btn-separator"></div></td>
				    		<td><a href="javascript:void(0);" title="网格" onclick="FormHandler.toggleGrid();"><span class="btn"><span class="icon icon-grid"></span></span></a></td>
				    		<td><div class="datagrid-btn-separator"></div></td>
				    		<td><a href="javascript:void(0);" title="帮助" onclick=""><span class="btn"><span class="icon icon-help"></span></span></a></td>
			    		</tr>
		    		</table>
		    	</div>
			</div>
			<div id="drawPanel" data-options="region:'center',border:false">
				
			</div>
		</div>
    </div>
    <div id="eastPanel" data-options="href:'form.jsp',region:'east',title:'表单配置',iconCls:'icon-property',split:true" style="width:280px;"></div>
</body>
</html>
