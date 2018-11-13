<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>1</title>
</head>
<body>
	<script type="text/javascript">
		$('<table></table>').appendTo('body').datagrid($.extend({},datagridOptions,{
			url:'workflow!queryByPage.action',
			idField:'id',
			title:'流程列表',
			toolbar:'#workflowToolbar',
			iconCls:'icon-workflow',
			columns:[[
			    $.extend({},columnOptions,{field:'id',checkbox:true}),
			    $.extend({},columnOptions,{field:'name',title:'流程名称'}),
			    $.extend({},columnOptions,{field:'workflowKey',title:'流程Key'}),
			    $.extend({},columnOptions,{field:'version',title:'版本号'}),
			    $.extend({},columnOptions,{field:'createTime',title:'创建时间'}),
			    $.extend({},columnOptions,{field:'state',title:'状态',formatter:function(value){
					var newValue = '<span style="color:red">未发布</span>';
					if(value==1){newValue = '<span style="color:green">已发布</span>';}
					return newValue;
				}}),
				$.extend({},columnOptions,{field:'memo',title:'备注',width:300})
			]]
		}));
	</script>
</body>
</html>