<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div class="easyui-panel" data-options="fit:true" style="padding:2px;">
		<div id="taskCompleteDatagrid">
			<div id="taskCompleteToolbar">
				<table width="100%">
					<tr>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-do',plain:true" onclick="taskCompleteHandler.viewTask()">查看</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-getback',plain:true" onclick="taskCompleteHandler.takeBack()">取回</a>
						</td>
						<td align="right">
							<input id="quick_taskCompleteName" class="easyui-searchbox" data-options="prompt:'请在此输入任务名称',searcher:quickSearchtask,menu:'#taskCompleteMM'" style="width:300px"/>
							<div id="taskCompleteMM" style="width:120px">
								<div id="quickSearchtaskComplete" data-options="name:'quick',iconCls:'icon-search'" onclick="$('#taskCompleteSP').slideUp();">快速查询</div>
								<div data-options="name:'advance',iconCls:'icon-advance-search'" onclick="$('#taskCompleteSP').slideDown();">高级查询</div>
							</div>
						</td>
					</tr>
				</table>
				<div id="taskCompleteSP" class="panel-body" style="position:absolute;left:-1px;z-index:99999;width:100%;display:none;">
					<form id="taskCompleteSearchForm" action="">
						<table width="100%" cellpadding="8">
							<tr style="height: 25px;line-height: 25px;" align="left">
								<td width="25%">
									<label for="s_taskCompleteName">任务名称：</label>
									<input id="s_taskCompleteName" class="searchbox" type="text" maxlength="20"/>
								</td>
								<td width="25%">
									<label for="s_taskCompleteKey">任务Key：</label>
									<input id="s_taskCompleteKey" class="searchbox" type="text" maxlength="20"/>
								</td>
								<td width="25%">
									<label for="s_taskCompleteVersion">版本号：</label>
									<input id="s_taskCompleteVersion" class="searchbox" type="text" maxlength="20"/>
								</td>
								<td align="right">
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="taskCompleteHandler.search(1)">搜索</a>
								</td>
							</tr>
							<tr height="25">
								<td>
									<label for="s_taskCompleteStartCreateTime">创建时间：</label>
									<input id="s_taskCompleteStartCreateTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_taskCompleteEndCreateTime" style="margin-left:30px;">至：</label>
									<input id="s_taskCompleteEndCreateTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_taskCompleteStatus">　状态：</label>
									<select id="s_taskCompleteStatus" class="easyui-combobox" data-options="panelHeight:'65',editable:false">
										<option value="">全部</option>
										<option value="1">已发布</option>
										<option value="0">未发布</option>
									</select>
								</td>
								<td align="right">
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="taskCompleteHandler.clear();">清空</a>
								</td>
							</tr>
							<tr height="25">
								<td>
									<label for="s_taskCompleteStartPublishTime">发布时间：</label>
									<input id="s_taskCompleteStartPublishTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_taskCompleteEndPublishTime" style="margin-left:30px;">至：</label>
									<input id="s_taskCompleteEndPublishTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_taskCompleteMemo">　备注：</label>
									<input id="s_taskCompleteMemo" class="searchbox" type="text" maxlength="20"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		
		<div id="taskCompleteColumnMenu"></div>
		<div id="taskCompleteRowMenu" class="easyui-menu">
			<div data-options="iconCls:'icon-do'" onclick="taskCompleteHandler.doTask()">处理</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-delegate'" onclick="taskCompleteHandler.delegate()">委托</div>
		</div>
	</div>
	<script type="text/javascript">
		var dayMillisecond = 1000*60*60*24;
		var hourMillisecond = 1000*60*60;
		var minuteMillisecond = 1000*60;
		var secondMillisecond = 1000;
		
		var taskCompleteDatagrid = $('#taskCompleteDatagrid').datagrid($.extend({},datagridOptions,{
			url:'task!queryCompleteByPage.action',
			idField:'taskId',
			title:'已办任务列表',
			toolbar:'#taskCompleteToolbar',
			iconCls:'icon-task',
			fitColumns:true,
			columns:[[
			    $.extend({},columnOptions,{field:'taskId',checkbox:true}),
			    $.extend({},columnOptions,{field:'workflowId',hidden:true}),
			    $.extend({},columnOptions,{field:'workflowName',title:'流程名称'}),
			    $.extend({},columnOptions,{field:'workflowVersion',title:'流程版本号'}),
			    $.extend({},columnOptions,{field:'taskName',title:'任务名称'}),
			    $.extend({},columnOptions,{field:'createTime',title:'创建时间'}),
			    $.extend({},columnOptions,{field:'completeTime',title:'完成时间'}),
			    $.extend({},columnOptions,{field:'duration',title:'耗时',formatter:function(value,row){
			    	var str = '';
			    	var day = parseInt(value/dayMillisecond,10);
			    	if(day>0){
			    		str += day+'天';
			    		value = value%dayMillisecond;
			    	}
			    	var hour = parseInt(value/hourMillisecond,10);
			    	if(hour>0){
			    		str += hour+'时';
			    		value = value%hourMillisecond;
			    	}
			    	var minute = parseInt(value/minuteMillisecond,10);
			    	if(minute>0){
			    		str += minute+'分';
			    		value = value%minuteMillisecond;
			    	}
			    	var second = parseInt(value/secondMillisecond,10);
			    	if(second>0){
			    		str += second+'秒';
			    		value = value%secondMillisecond;
			    	}
			    	return str;
			    }}),
			    $.extend({},columnOptions,{field:'executionId',title:'操作',formatter:function(value,row){
			    	var str = '<a class="operation" href="javascript:void(0)" onclick="taskCompleteHandler.viewTask()">查看</a>';
			    	str += '　<a class="operation" href="javascript:void(0)" onclick="taskCompleteHandler.takeBack()">取回</a>';
			    	return str;
			    }})
			]],
			onHeaderContextMenu:function(e, field){
				e.preventDefault();
				if (!taskCompleteColumnMenu){
					createtaskCompleteColumnMenu();
				}
				taskCompleteColumnMenu.menu('show', {
					left:e.pageX,
					top:e.pageY
				});
			},
			onRowContextMenu:function(e, index, data){
				$('#taskCompleteDatagrid').datagrid('selectRow',index);
				e.preventDefault();
				$('#taskCompleteRowMenu').menu('show', {
					left:e.pageX,
					top:e.pageY
				});
			}
		}));
		
		var taskCompleteColumnMenu,taskCompleteRowMenu;
		function createtaskCompleteColumnMenu(){
			taskCompleteColumnMenu = $('#taskCompleteColumnMenu');
			taskCompleteColumnMenu.menu({
				onClick: function(item){
					if (item.iconCls == 'icon-eye'){
						$('#taskCompleteDatagrid').datagrid('hideColumn', item.name);
						taskCompleteColumnMenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-hidden'
						});
					} else {
						$('#taskCompleteDatagrid').datagrid('showColumn', item.name);
						taskCompleteColumnMenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-eye'
						});
					}
				}
			});
			var fields = $('#taskCompleteDatagrid').datagrid('getColumnFields');
			for(var i=0; i<fields.length; i++){
				var field = fields[i];
				var col = $('#taskCompleteDatagrid').datagrid('getColumnOption', field);
				if(col.field=='taskId'){
					continue;
				}
				taskCompleteColumnMenu.menu('appendItem', {
					text: col.title,
					name: field,
					iconCls: 'icon-eye'
				});
			}
		}
		
		function quickSearchtaskComplete(value,name){
			taskCompleteHandler.search(0);
		}
		
		var taskCompleteHandler = {
			viewTask:function(){
				setTimeout(function(){
					var checked = $('#taskCompleteDatagrid').datagrid('getChecked');
					if(checked.length==1){
						addTab('查看任务('+checked[0].taskName+')','../workflow/viewTask.jsp','icon-task',null,{workflowId:checked[0].workflowId,executionId:checked[0].executionId,taskId:checked[0].taskId});
						$('#taskCompleteDatagrid').datagrid('clearSelections');
					}
				},1);
			},
			clear:function(){
				$('#taskCompleteSearchForm').form('clear');
				$('#s_taskCompleteStatus').combobox('setValue','');
			},
			search:function(flag){
				var params = {random:new Date()};
				if(flag==0){
					params.name = $('#quick_taskCompleteName').searchbox('getValue');
				}else{
					params.name = $('#s_taskCompleteName').val();
					params.key = $('#s_taskCompleteKey').val();
					params.version = $('#s_taskCompleteVersion').val();
					params.startCreateTime = $('#s_taskCompleteStartCreateTime').datebox('getValue');
					params.endCreateTime = $('#s_taskCompleteEndCreateTime').datebox('getValue');
					params.state = $('#s_taskCompleteStatus').combobox('getValue');
					params.startPublishTime = $('#s_taskCompleteStartPublishTime').combobox('getValue');
					params.endPublishTime = $('#s_taskCompleteEndPublishTime').combobox('getValue');
					params.memo = $('#s_taskCompleteMemo').val();
					$('#quickSearchtaskComplete').click();
				}
				$('#taskCompleteDatagrid').datagrid('load',params);
			}
		};
	</script>
</body>
</html>
