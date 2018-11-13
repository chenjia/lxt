<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div class="easyui-panel" data-options="fit:true" style="padding:2px;">
		<div id="workflowDatagrid">
			<div id="workflowToolbar">
				<table width="100%">
					<tr>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="workflowHandler.create()">新建</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="workflowHandler.remove()">删除</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="workflowHandler.edit()">修改</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-publish',plain:true" onclick="workflowHandler.publish()">发布</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-yes',plain:true" onclick="workflowHandler.unlock()">启用</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true" onclick="workflowHandler.lock()">禁用</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="100">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-start',plain:true" onclick="workflowHandler.start()">发起流程</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="100">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="workflowHandler.deleteAll()">删除所有</a>
						</td>
						<td align="right">
							<input id="quick_workflowName" class="easyui-searchbox" data-options="prompt:'请在此输入流程名称',searcher:quickSearchWorkflow,menu:'#workflowMM'" style="width:300px"/>
							<div id="workflowMM" style="width:120px">
								<div id="quickSearchWorkflow" data-options="name:'quick',iconCls:'icon-search'" onclick="$('#workflowSP').slideUp();">快速查询</div>
								<div data-options="name:'advance',iconCls:'icon-advance-search'" onclick="$('#workflowSP').slideDown();">高级查询</div>
							</div>
						</td>
					</tr>
				</table>
				<div id="workflowSP" class="panel-body" style="position:absolute;left:-1px;z-index:99999;width:100%;display:none;">
					<form id="workflowSearchForm" action="">
						<table width="100%" cellpadding="8">
							<tr style="height: 25px;line-height: 25px;" align="left">
								<td width="25%">
									<label for="s_workflowName">流程名称：</label>
									<input id="s_workflowName" class="searchbox" type="text" maxlength="20"/>
								</td>
								<td width="25%">
									<label for="s_workflowKey">流程Key：</label>
									<input id="s_workflowKey" class="searchbox" type="text" maxlength="20"/>
								</td>
								<td width="25%">
									<label for="s_workflowVersion">版本号：</label>
									<input id="s_workflowVersion" class="searchbox" type="text" maxlength="20"/>
								</td>
								<td align="right">
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="workflowHandler.search(1)">搜索</a>
								</td>
							</tr>
							<tr height="25">
								<td>
									<label for="s_workflowStartCreateTime">创建时间：</label>
									<input id="s_workflowStartCreateTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_workflowEndCreateTime" style="margin-left:30px;">至：</label>
									<input id="s_workflowEndCreateTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_workflowStatus">　状态：</label>
									<select id="s_workflowStatus" class="easyui-combobox" data-options="panelHeight:'65',editable:false">
										<option value="">全部</option>
										<option value="1">已发布</option>
										<option value="0">未发布</option>
									</select>
								</td>
								<td align="right">
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="workflowHandler.clear();">清空</a>
								</td>
							</tr>
							<tr height="25">
								<td>
									<label for="s_workflowStartPublishTime">发布时间：</label>
									<input id="s_workflowStartPublishTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_workflowEndPublishTime" style="margin-left:30px;">至：</label>
									<input id="s_workflowEndPublishTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_workflowMemo">　备注：</label>
									<input id="s_workflowMemo" class="searchbox" type="text" maxlength="20"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		
		<div id="cmenu"></div>
		<div id="rmenu" class="easyui-menu">
			<div data-options="iconCls:'icon-add'" onclick="workflowHandler.create()">新建</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-remove'" onclick="workflowHandler.remove()">删除</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-edit'" onclick="workflowHandler.edit()">修改</div>
			<div class="menu-sep"></div>
			<div id="publishMenuItem" data-options="iconCls:'icon-publish'" onclick="workflowHandler.publish()">发布</div>
		</div>
	</div>
	<script type="text/javascript">
		var workflowDatagrid = $('#workflowDatagrid').datagrid($.extend({},datagridOptions,{
			url:'workflow!queryByPage.action',
			idField:'workflowId',
			title:'流程列表',
			toolbar:'#workflowToolbar',
			iconCls:'icon-workflow',
			columns:[[
			    $.extend({},columnOptions,{field:'workflowId',checkbox:true}),
			    $.extend({},columnOptions,{field:'name',title:'流程名称'}),
			    $.extend({},columnOptions,{field:'workflowKey',title:'流程Key'}),
			    $.extend({},columnOptions,{field:'version',title:'版本号'}),
			    $.extend({},columnOptions,{field:'createTime',title:'创建时间'}),
			    $.extend({},columnOptions,{field:'publishStatus',title:'发布状态',formatter:function(value){
					var newValue = '<span style="color:red">未发布</span>';
					if(value==1){newValue = '<span style="color:green">已发布</span>';}
					return newValue;
				}}),
				$.extend({},columnOptions,{field:'status',title:'开启状态',formatter:function(value){
					var newValue = '<span style="color:red">关闭</span>';
					if(value==1){newValue = '<span style="color:green">开启</span>';}
					return newValue;
				}}),
				$.extend({},columnOptions,{field:'memo',title:'备注',width:300})
			]],
			onHeaderContextMenu:function(e, field){
				e.preventDefault();
				if (!cmenu){
					createColumnMenu();
				}
				cmenu.menu('show', {
					left:e.pageX,
					top:e.pageY
				});
			},
			onRowContextMenu:function(e, index, data){
				$('#workflowDatagrid').datagrid('selectRow',index);
				e.preventDefault();
				if(data.state==1){
					$('#rmenu').menu('disableItem',$('#publishMenuItem')[0]);
				}else{
					$('#rmenu').menu('enableItem',$('#publishMenuItem')[0]);
				}
				$('#rmenu').menu('show', {
					left:e.pageX,
					top:e.pageY
				});
			}
		}));
		
		var cmenu,rmenu;
		function createColumnMenu(){
			cmenu = $('#cmenu');
			cmenu.menu({
				onClick: function(item){
					if (item.iconCls == 'icon-eye'){
						$('#workflowDatagrid').datagrid('hideColumn', item.name);
						cmenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-hidden'
						});
					} else {
						$('#workflowDatagrid').datagrid('showColumn', item.name);
						cmenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-eye'
						});
					}
				}
			});
			var fields = $('#workflowDatagrid').datagrid('getColumnFields');
			for(var i=0; i<fields.length; i++){
				var field = fields[i];
				var col = $('#workflowDatagrid').datagrid('getColumnOption', field);
				if(col.field=='id'){
					continue;
				}
				cmenu.menu('appendItem', {
					text: col.title,
					name: field,
					iconCls: 'icon-eye'
				});
			}
		}
		
		function quickSearchWorkflow(value,name){
			workflowHandler.search(0);
		}
		
		var workflowHandler = {
			create:function(){
				window.open('../workflow/workflowConfig.jsp');
			},
			remove:function(){
				var checked = $('#workflowDatagrid').datagrid('getChecked');
				if(checked.length==1){
					MessageUtils.confirm('删除流程','确定要删除流程['+checked[0].name+']吗？',function(r){
						if(r){
							$.ajax({
								url:'workflow!delete.action',
								data:{'param.workflowId':checked[0].workflowId},
								success:function(data){
									if(data.status==0){
										$('#workflowDatagrid').datagrid('clearSelections');
										$('#workflowDatagrid').datagrid('reload');
										MessageUtils.success('删除流程','流程['+checked[0].name+']删除成功！');
									}
								}
							});
						}
					});
				}else{
					MessageUtils.alert('删除流程','请选择要删除的流程！','info');
				}
			},
			deleteAll:function(){
				$.ajax({
					url:'workflow!deleteAll.action',
					success:function(data){
						if(data.status==0){
							$('#workflowDatagrid').datagrid('reload');
						}
					}
				});
			},
			edit:function(){
				var checked = $('#workflowDatagrid').datagrid('getChecked');
				if(checked.length==1){
					window.open('../workflow/workflowConfig.jsp?workflowId='+checked[0].workflowId);
				}
			},
			publish:function(){
				var checked = $('#workflowDatagrid').datagrid('getChecked');
				if(checked.length==1){
					if(checked[0].publishStatus==1){
						MessageUtils.fail('发布流程','对不起，该流程已经发布过了！');
					}else{
						$.ajax({
							url:'workflow!publish.action',
							data:{'param.workflowId':checked[0].workflowId},
							success:function(json){
								if(json.status==0){
									$('#workflowDatagrid').datagrid('reload');
									MessageUtils.success('发布流程','流程['+checked[0].name+']发布成功！');
								}
							}
						});
					}
				}else{
					MessageUtils.alert('发布流程','请选择一个要发布的流程！');
				}
			},
			start:function(){
				var checked = $('#workflowDatagrid').datagrid('getChecked');
				if(checked.length==1){
					if(checked[0].publishStatus==1){
						$.ajax({
							url:'workflow!start.action',
							data:{'param.workflowId':checked[0].workflowId},
							success:function(json){
								if(json.status==0){
									MessageUtils.success('发起流程','流程['+checked[0].name+']发起成功！'+',pid:'+json.data);
									$('#taskDatagrid').datagrid('reload');
								}else{
									MessageUtils.fail('发布流程',json.msg);
								}
							}
						});
					}else{
						MessageUtils.fail('发布流程','对不起，该流程尚未发布！');
					}
				}else{
					MessageUtils.alert('发起流程','请选择一个要发起的流程！');
				}
			},
			clear:function(){
				$('#workflowSearchForm').form('clear');
				$('#s_workflowStatus').combobox('setValue','');
			},
			search:function(flag){
				var params = {random:new Date()};
				if(flag==0){
					params.name = $('#quick_workflowName').searchbox('getValue');
				}else{
					params.name = $('#s_workflowName').val();
					params.key = $('#s_workflowKey').val();
					params.version = $('#s_workflowVersion').val();
					params.startCreateTime = $('#s_workflowStartCreateTime').datebox('getValue');
					params.endCreateTime = $('#s_workflowEndCreateTime').datebox('getValue');
					params.state = $('#s_workflowStatus').combobox('getValue');
					params.startPublishTime = $('#s_workflowStartPublishTime').combobox('getValue');
					params.endPublishTime = $('#s_workflowEndPublishTime').combobox('getValue');
					params.memo = $('#s_workflowMemo').val();
					$('#quickSearchWorkflow').click();
				}
				$('#workflowDatagrid').datagrid('load',params);
			}
		};
	</script>
</body>
</html>
