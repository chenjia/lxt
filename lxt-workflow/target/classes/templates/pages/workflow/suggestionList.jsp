<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div class="easyui-panel" data-options="fit:true" style="padding:2px;">
		<div class="suggestionDatagrid">
			<div class="suggestionToolbar">
				<table width="100%">
					<tr>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-do',plain:true" onclick="suggestionHandler.dosuggestion()">处理</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-delegate',plain:true" onclick="suggestionHandler.delegate()">委托</a>
						</td>
						<td align="right">
							<input class="quick_suggestionName" class="easyui-searchbox" data-options="prompt:'请在此输入任务名称',searcher:quickSearchsuggestion,menu:'#suggestionMM'" style="width:300px"/>
							<div class="suggestionMM" style="width:120px">
								<div class="quickSearchsuggestion" data-options="name:'quick',iconCls:'icon-search'" onclick="$('#suggestionSP').slideUp();">快速查询</div>
								<div data-options="name:'advance',iconCls:'icon-advance-search'" onclick="$('#suggestionSP').slideDown();">高级查询</div>
							</div>
						</td>
					</tr>
				</table>
				<div class="suggestionSP" class="panel-body" style="position:absolute;left:-1px;z-index:99999;width:100%;display:none;">
					<form class="suggestionSearchForm" action="">
						<table width="100%" cellpadding="8">
							<tr style="height: 25px;line-height: 25px;" align="left">
								<td width="25%">
									<label for="s_suggestionName">任务名称：</label>
									<input class="s_suggestionName" class="searchbox" type="text" maxlength="20"/>
								</td>
								<td width="25%">
									<label for="s_suggestionKey">任务Key：</label>
									<input class="s_suggestionKey" class="searchbox" type="text" maxlength="20"/>
								</td>
								<td width="25%">
									<label for="s_suggestionVersion">版本号：</label>
									<input class="s_suggestionVersion" class="searchbox" type="text" maxlength="20"/>
								</td>
								<td align="right">
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="suggestionHandler.search(1)">搜索</a>
								</td>
							</tr>
							<tr height="25">
								<td>
									<label for="s_suggestionStartCreateTime">创建时间：</label>
									<input class="s_suggestionStartCreateTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_suggestionEndCreateTime" style="margin-left:30px;">至：</label>
									<input class="s_suggestionEndCreateTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_suggestionStatus">　状态：</label>
									<select class="s_suggestionStatus" class="easyui-combobox" data-options="panelHeight:'65',editable:false">
										<option value="">全部</option>
										<option value="1">已发布</option>
										<option value="0">未发布</option>
									</select>
								</td>
								<td align="right">
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="suggestionHandler.clear();">清空</a>
								</td>
							</tr>
							<tr height="25">
								<td>
									<label for="s_suggestionStartPublishTime">发布时间：</label>
									<input class="s_suggestionStartPublishTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_suggestionEndPublishTime" style="margin-left:30px;">至：</label>
									<input class="s_suggestionEndPublishTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_suggestionMemo">　备注：</label>
									<input class="s_suggestionMemo" class="searchbox" type="text" maxlength="20"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		
		<div class="suggestionColumnMenu"></div>
		<div class="suggestionRowMenu" class="easyui-menu">
			<div data-options="iconCls:'icon-do'" onclick="suggestionHandler.dosuggestion()">处理</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-delegate'" onclick="suggestionHandler.delegate()">委托</div>
		</div>
	</div>
	<script type="text/javascript">
		var selectedTab = centerTabs.tabs('getSelected');
		
		selectedTab.find('.suggestionDatagrid').datagrid($.extend({},datagridOptions,{
			url:'suggestion!queryByPage.action',
			idField:'suggestionId',
			title:'任务列表',
			toolbar:'#suggestionToolbar',
			iconCls:'icon-suggestion',
			fitColumns:true,
			columns:[[
			    $.extend({},columnOptions,{field:'suggestionId',checkbox:true}),
			    $.extend({},columnOptions,{field:'workflowId',hidden:true}),
			    $.extend({},columnOptions,{field:'workflowName',title:'流程名称'}),
			    $.extend({},columnOptions,{field:'workflowVersion',title:'流程版本号'}),
			    $.extend({},columnOptions,{field:'suggestionName',title:'任务名称'}),
			    $.extend({},columnOptions,{field:'prevUser',title:'上一处理人'}),
			    $.extend({},columnOptions,{field:'createTime',title:'下达时间'}),
			    $.extend({},columnOptions,{field:'executionId',title:'操作',formatter:function(value,row){
			    	return '<a class="operation" href="javascript:void(0)" onclick="suggestionHandler.dosuggestion()">处理</a>';
			    }})
			]]
		}));
	</script>
</body>
</html>
