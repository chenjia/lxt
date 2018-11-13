<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div class="easyui-panel" data-options="fit:true" style="padding:2px;">
		<div id="taskDatagrid">
			<div id="taskToolbar">
				<table width="100%">
					<tr>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-do',plain:true" onclick="taskHandler.doTask()">处理</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-delegate',plain:true" onclick="taskHandler.delegate()">委托</a>
						</td>
						<td align="right">
							<input id="quick_taskName" class="easyui-searchbox" data-options="prompt:'请在此输入任务名称',searcher:quickSearchtask,menu:'#taskMM'" style="width:300px"/>
							<div id="taskMM" style="width:120px">
								<div id="quickSearchtask" data-options="name:'quick',iconCls:'icon-search'" onclick="$('#taskSP').slideUp();">快速查询</div>
								<div data-options="name:'advance',iconCls:'icon-advance-search'" onclick="$('#taskSP').slideDown();">高级查询</div>
							</div>
						</td>
					</tr>
				</table>
				<div id="taskSP" class="panel-body" style="position:absolute;left:-1px;z-index:99999;width:100%;display:none;">
					<form id="taskSearchForm" action="">
						<table width="100%" cellpadding="8">
							<tr style="height: 25px;line-height: 25px;" align="left">
								<td width="25%">
									<label for="s_taskName">任务名称：</label>
									<input id="s_taskName" class="searchbox" type="text" maxlength="20"/>
								</td>
								<td width="25%">
									<label for="s_taskKey">任务Key：</label>
									<input id="s_taskKey" class="searchbox" type="text" maxlength="20"/>
								</td>
								<td width="25%">
									<label for="s_taskVersion">版本号：</label>
									<input id="s_taskVersion" class="searchbox" type="text" maxlength="20"/>
								</td>
								<td align="right">
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="taskHandler.search(1)">搜索</a>
								</td>
							</tr>
							<tr height="25">
								<td>
									<label for="s_taskStartCreateTime">创建时间：</label>
									<input id="s_taskStartCreateTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_taskEndCreateTime" style="margin-left:30px;">至：</label>
									<input id="s_taskEndCreateTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_taskStatus">　状态：</label>
									<select id="s_taskStatus" class="easyui-combobox" data-options="panelHeight:'65',editable:false">
										<option value="">全部</option>
										<option value="1">已发布</option>
										<option value="0">未发布</option>
									</select>
								</td>
								<td align="right">
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="taskHandler.clear();">清空</a>
								</td>
							</tr>
							<tr height="25">
								<td>
									<label for="s_taskStartPublishTime">发布时间：</label>
									<input id="s_taskStartPublishTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_taskEndPublishTime" style="margin-left:30px;">至：</label>
									<input id="s_taskEndPublishTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_taskMemo">　备注：</label>
									<input id="s_taskMemo" class="searchbox" type="text" maxlength="20"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		
		<div id="taskColumnMenu"></div>
		<div id="taskRowMenu" class="easyui-menu">
			<div data-options="iconCls:'icon-do'" onclick="taskHandler.doTask()">处理</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-delegate'" onclick="taskHandler.delegate()">委托</div>
		</div>
	</div>
	<script type="text/javascript">
		var taskDatagrid = $('#taskDatagrid').datagrid($.extend({},datagridOptions,{
			url:'task!queryByPage.action',
			idField:'taskId',
			title:'任务列表',
			toolbar:'#taskToolbar',
			iconCls:'icon-task',
			fitColumns:true,
			columns:[[
			    $.extend({},columnOptions,{field:'taskId',checkbox:true}),
			    $.extend({},columnOptions,{field:'workflowId',hidden:true}),
			    $.extend({},columnOptions,{field:'workflowName',title:'流程名称'}),
			    $.extend({},columnOptions,{field:'workflowVersion',title:'流程版本号'}),
			    $.extend({},columnOptions,{field:'taskName',title:'任务名称'}),
			    $.extend({},columnOptions,{field:'prevUser',title:'上一处理人'}),
			    $.extend({},columnOptions,{field:'createTime',title:'下达时间'}),
			    $.extend({},columnOptions,{field:'executionId',title:'操作',formatter:function(value,row){
			    	return '<a class="operation" href="javascript:void(0)" onclick="taskHandler.doTask()">处理</a>';
			    }})
			    /*
			    $.extend({},columnOptions,{field:'state',title:'状态',formatter:function(value){
					var newValue = '<span style="color:red">未发布</span>';
					if(value==1){newValue = '<span style="color:green">已发布</span>';}
					return newValue;
				}}),
				*/
			]],
			onHeaderContextMenu:function(e, field){
				e.preventDefault();
				if (!taskColumnMenu){
					createTaskColumnMenu();
				}
				taskColumnMenu.menu('show', {
					left:e.pageX,
					top:e.pageY
				});
			},
			onRowContextMenu:function(e, index, data){
				$('#taskDatagrid').datagrid('selectRow',index);
				e.preventDefault();
				$('#taskRowMenu').menu('show', {
					left:e.pageX,
					top:e.pageY
				});
			}
		}));
		
		var taskColumnMenu,taskRowMenu;
		function createTaskColumnMenu(){
			taskColumnMenu = $('#taskColumnMenu');
			taskColumnMenu.menu({
				onClick: function(item){
					if (item.iconCls == 'icon-eye'){
						$('#taskDatagrid').datagrid('hideColumn', item.name);
						taskColumnMenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-hidden'
						});
					} else {
						$('#taskDatagrid').datagrid('showColumn', item.name);
						taskColumnMenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-eye'
						});
					}
				}
			});
			var fields = $('#taskDatagrid').datagrid('getColumnFields');
			for(var i=0; i<fields.length; i++){
				var field = fields[i];
				var col = $('#taskDatagrid').datagrid('getColumnOption', field);
				if(col.field=='taskId'){
					continue;
				}
				taskColumnMenu.menu('appendItem', {
					text: col.title,
					name: field,
					iconCls: 'icon-eye'
				});
			}
		}
		
		function quickSearchtask(value,name){
			taskHandler.search(0);
		}
		
		var taskHandler = {
			doTask:function(){
				setTimeout(function(){
					var checked = $('#taskDatagrid').datagrid('getChecked');
					if(checked.length==1){
						addTab('任务('+checked[0].taskName+')','../workflow/doTask.jsp','icon-task',null,{workflowId:checked[0].workflowId,executionId:checked[0].executionId,taskId:checked[0].taskId});
					}
				},1);
			},
			clear:function(){
				$('#taskSearchForm').form('clear');
				$('#s_taskStatus').combobox('setValue','');
			},
			search:function(flag){
				var params = {random:new Date()};
				if(flag==0){
					params.name = $('#quick_taskName').searchbox('getValue');
				}else{
					params.name = $('#s_taskName').val();
					params.key = $('#s_taskKey').val();
					params.version = $('#s_taskVersion').val();
					params.startCreateTime = $('#s_taskStartCreateTime').datebox('getValue');
					params.endCreateTime = $('#s_taskEndCreateTime').datebox('getValue');
					params.state = $('#s_taskStatus').combobox('getValue');
					params.startPublishTime = $('#s_taskStartPublishTime').combobox('getValue');
					params.endPublishTime = $('#s_taskEndPublishTime').combobox('getValue');
					params.memo = $('#s_taskMemo').val();
					$('#quickSearchtask').click();
				}
				$('#taskDatagrid').datagrid('load',params);
			}
		};
	</script>
</body>
</html>
