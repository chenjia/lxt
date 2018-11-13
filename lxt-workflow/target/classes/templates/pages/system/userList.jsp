<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div class="easyui-panel" data-options="fit:true" style="padding:2px;">
		<div id="userDatagrid">
			<div id="userToolbar">
				<table width="100%">
					<tr>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-user-add',plain:true" onclick="userHandler.create()">新建</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-user-delete',plain:true" onclick="userHandler.remove()">删除</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-user-edit',plain:true" onclick="userHandler.edit()">修改</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reset',plain:true" onclick="userHandler.resetDefault()">重置</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-yes',plain:true" onclick="userHandler.active()">启用</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true" onclick="userHandler.forbidden()">禁用</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="80">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-role',plain:true" onclick="userHandler.setRole()">分配角色</a>
						</td>
						<td align="right">
							<input id="quick_userName" class="easyui-searchbox" data-options="prompt:'请在此输入用户名',searcher:quickSearchUser,menu:'#userMM'" style="width:300px"/>
							<div id="userMM" style="width:120px">
								<div id="quickSearchUser" data-options="name:'quick',iconCls:'icon-search'" onclick="$('#userSP').slideUp();">快速查询</div>
								<div data-options="name:'advance',iconCls:'icon-advance-search'" onclick="$('#userSP').slideDown();">高级查询</div>
							</div>
						</td>
					</tr>
				</table>
				<div id="userSP" class="panel-body" style="position:absolute;left:-1px;z-index:99999;width:100%;display:none;">
					<form id="userSearchForm" action="">
						<table width="100%" cellpadding="8">
							<tr style="height: 25px;line-height: 25px;" align="left">
								<td width="25%">
									<label for="s_userName">流程名称：</label>
									<input id="s_userName" class="searchbox" type="text" maxlength="20"/>
								</td>
								<td width="25%">
									<label for="s_userKey">流程Key：</label>
									<input id="s_userKey" class="searchbox" type="text" maxlength="20"/>
								</td>
								<td width="25%">
									<label for="s_userVersion">版本号：</label>
									<input id="s_userVersion" class="searchbox" type="text" maxlength="20"/>
								</td>
								<td align="right">
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="userHandler.search(1)">搜索</a>
								</td>
							</tr>
							<tr height="25">
								<td>
									<label for="s_userStartCreateTime">创建时间：</label>
									<input id="s_userStartCreateTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_userEndCreateTime" style="margin-left:30px;">至：</label>
									<input id="s_userEndCreateTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_userStatus">　状态：</label>
									<select id="s_userStatus" class="easyui-combobox" data-options="panelHeight:'65',editable:false">
										<option value="">全部</option>
										<option value="1">已发布</option>
										<option value="0">未发布</option>
									</select>
								</td>
								<td align="right">
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clean'" onclick="userHandler.reset();">清空</a>
								</td>
							</tr>
							<tr height="25">
								<td>
									<label for="s_userStartPublishTime">发布时间：</label>
									<input id="s_userStartPublishTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_userEndPublishTime" style="margin-left:30px;">至：</label>
									<input id="s_userEndPublishTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_userMemo">　备注：</label>
									<input id="s_userMemo" class="searchbox" type="text" maxlength="20"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		
		<div id="userColumnMenu"></div>
		<div id="userRowMenu" class="easyui-menu">
			<div data-options="iconCls:'icon-user-add'" onclick="userHandler.create()">新建</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-user-delete'" onclick="userHandler.remove()">删除</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-user-edit'" onclick="userHandler.edit()">修改</div>
			<div class="menu-sep"></div>
			<div id="userResetMenuItem" data-options="iconCls:'icon-reset'" onclick="userHandler.resetDefault()">重置</div>
			<div class="menu-sep"></div>
			<div id="userLockMenuItem" data-options="iconCls:'icon-lock'" onclick="userHandler.lock">禁用</div>
			<div class="menu-sep"></div>
			<div id="userUnlockMenuItem" data-options="iconCls:'icon-unlock'" onclick="userHandler.unlock()">解禁</div>
			<div class="menu-sep"></div>
			<div id="userSetRoleMenuItem" data-options="iconCls:'icon-role'" onclick="userHandler.setRole()">分配角色</div>
		</div>
	</div>
	<script type="text/javascript">
		var userDatagrid = $('#userDatagrid').datagrid($.extend({},datagridOptions,{
			url:'user!queryByPage.action',
			idField:'userId',
			title:'用户列表',
			toolbar:'#userToolbar',
			iconCls:'icon-user',
			frozenColumns:[[
				$.extend({},columnOptions,{field:'userId',checkbox:true}),
				$.extend({},columnOptions,{field:'username',title:'用户名'}),
				$.extend({},columnOptions,{field:'truename',title:'真实姓名'})
			]],
			columns:[[
			    $.extend({},columnOptions,{field:'screenname',title:'昵称'}),
			    $.extend({},columnOptions,{field:'status',title:'用户状态',width:80,formatter:function(value){
					var newValue;
			    	if(value==0){
						newValue = '<img tooltip="禁用" src="../../images/icons/no.png"/>';
					}else if(value==1){
						newValue = '<img tooltip="启用" src="../../images/icons/yes.png"/>';
					}else{
						newValue = '未知';
					}
					return newValue;
				}}),
			    $.extend({},columnOptions,{field:'identityCard',title:'身份证号'}),
			    $.extend({},columnOptions,{field:'gender',title:'性别',width:80,formatter:function(value){
			    	var newValue;
			    	if(value==0){
						newValue = '<img tooltip="女" src="../../images/icons/female.png"/>';
					}else if(value==1){
						newValue = '<img tooltip="男" src="../../images/icons/male.png"/>';
					}else{
						newValue = '未知';
					}
					return newValue;
				}}),
				$.extend({},columnOptions,{field:'picture',title:'头像',formatter:function(value){
					//return '<img style="width:16px;height:16px;" src="user!getPicture.action?userId='+value+'"/>';
					return '';
				}}),
				$.extend({},columnOptions,{field:'birthday',title:'生日',width:100,formatter:function(value){
					if(value)return value.substr(0,10);
				}}),
			    $.extend({},columnOptions,{field:'orgName',title:'所在机构'}),
			    $.extend({},columnOptions,{field:'deptName',title:'所在部门'}),
			    $.extend({},columnOptions,{field:'mobile',title:'移动电话'}),
			    $.extend({},columnOptions,{field:'email',title:'电子邮箱'}),
			    $.extend({},columnOptions,{field:'qq',title:'QQ'}),
				$.extend({},columnOptions,{field:'pwdStatus',title:'是否修改密码',width:80,formatter:function(value){
					var newValue;
			    	if(value==0){
						newValue = '<img tooltip="初始密码" src="../../images/icons/no.png"/>';
					}else if(value==1){
						newValue = '<img tooltip="已修改" src="../../images/icons/yes.png"/>';
					}else{
						newValue = '未知';
					}
					return newValue;
				}}),
				$.extend({},columnOptions,{field:'setQuestion',title:'是否设置密保',width:80,formatter:function(value){
					var newValue;
			    	if(value==0){
						newValue = '<img tooltip="未设置" src="../../images/icons/no.png"/>';
					}else if(value==1){
						newValue = '<img tooltip="已设置" src="../../images/icons/yes.png"/>';
					}else{
						newValue = '未知';
					}
					return newValue;
				}}),
			    $.extend({},columnOptions,{field:'creator',title:'创建者',hidden:true}),
			    $.extend({},columnOptions,{field:'createTime',title:'创建时间',hidden:true}),
			    $.extend({},columnOptions,{field:'lastLoginTime',title:'最后登录时间',hidden:true}),
			    $.extend({},columnOptions,{field:'lastUpdatePwdTime',title:'最后更新密码时间',hidden:true}),
			    $.extend({},columnOptions,{field:'lastUpdateQuestionTime',title:'最后设置密保时间',hidden:true})
			]],
			onHeaderContextMenu:function(e, field){
				e.preventDefault();
				if (!userColumnMenu){
					createTaskColumnMenu();
				}
				userColumnMenu.menu('show', {
					left:e.pageX,
					top:e.pageY
				});
			},
			onRowContextMenu:function(e, index, data){
				$('#userDatagrid').datagrid('selectRow',index);
				e.preventDefault();
				if(data.status==0){
					$('#userRowMenu').menu('enableItem',$('#userUnlockMenuItem')[0]);
					$('#userRowMenu').menu('disableItem',$('#userLockMenuItem')[0]);
				}else if(data.status==0){
					$('#userRowMenu').menu('enableItem',$('#userLockMenuItem')[0]);
					$('#userRowMenu').menu('disableItem',$('#userUnlockMenuItem')[0]);
				}else{
					$('#userRowMenu').menu('disableItem',$('#userLockMenuItem')[0]);
					$('#userRowMenu').menu('disableItem',$('#userUnlockMenuItem')[0]);
				}
				$('#userRowMenu').menu('show', {
					left:e.pageX,
					top:e.pageY
				});
			}
		}));
		
		var userColumnMenu,userRowMenu;
		function createTaskColumnMenu(){
			userColumnMenu = $('#userColumnMenu');
			userColumnMenu.menu({
				onClick: function(item){
					if (item.iconCls == 'icon-eye'){
						$('#userDatagrid').datagrid('hideColumn', item.name);
						userColumnMenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-hidden'
						});
					} else {
						$('#userDatagrid').datagrid('showColumn', item.name);
						userColumnMenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-eye'
						});
					}
				}
			});
			var fields = $('#userDatagrid').datagrid('getColumnFields');
			for(var i=0; i<fields.length; i++){
				var field = fields[i];
				var col = $('#userDatagrid').datagrid('getColumnOption', field);
				if(col.field=='userId'){
					continue;
				}
				if(col.hidden){
					userColumnMenu.menu('appendItem', {
						text: col.title,
						name: field,
						iconCls: 'icon-hidden'
					});
				}else{
					userColumnMenu.menu('appendItem', {
						text: col.title,
						name: field,
						iconCls: 'icon-eye'
					});
				}
			}
		}
		
		function quickSearchUser(value,name){
			userHandler.search(0);
		}
		
		var userHandler = {
			create:function(){
				addTab('新建用户','../system/addUser.jsp','icon-user-add');
			},
			remove:function(){
				var checked = $('#userDatagrid').datagrid('getChecked');
				if(checked.length==1){
					MessageUtils.confirm('删除用户','确定要删除用户：['+checked[0].username+']吗？',function(r){
						if(r){
							$.ajax({
								url:'user!delete.action',
								data:{'param.userId':checked[0].userId},
								success:function(){
									var username = checked[0].username;
									$('#userDatagrid').datagrid('clearSelections');
									$('#userDatagrid').datagrid('reload');
									MessageUtils.success('删除用户','用户['+username+']删除成功！');
								}
							});
						}
					});
				}else{
					MessageUtils.alert('删除用户','请选择要删除的用户！','info');
				}
			},
			edit:function(){
				var checked = $('#userDatagrid').datagrid('getChecked');
				if(checked.length==1){
					$('#userDatagrid').data('editUser',{userId:checked[0].userId});
					addTab('修改用户','../system/editUser.jsp','icon-user-edit');
				}else{
					MessageUtils.alert('修改用户','请选择要修改的用户！','info');
				}
			},
			active:function(){
				var checked = $('#userDatagrid').datagrid('getChecked');
				if(checked.length==1){
					$.ajax({
						url:'user!active.action',
						data:{'param.userId':checked[0].userId},
						success:function(json){
							if(json.data){
								$('#userDatagrid').datagrid('reload');
								MessageUtils.success('启用用户','用户['+checked[0].username+']启用成功！');
							}
						}
					});
				}else{
					MessageUtils.alert('启用用户','请选择要启用的用户！','info');
				}
			},
			forbidden:function(){
				var checked = $('#userDatagrid').datagrid('getChecked');
				if(checked.length==1){
					$.ajax({
						url:'user!forbidden.action',
						data:{'param.userId':checked[0].userId},
						success:function(json){
							if(json.data){
								$('#userDatagrid').datagrid('reload');
								MessageUtils.success('禁用用户','用户['+checked[0].username+']禁用成功！');
							}
						}
					});
				}else{
					MessageUtils.alert('禁用用户','请选择要禁用的用户！','info');
				}
			},
			setRole:function(){
				
			},
			reset:function(){
				$('#userSearchForm').form('clear');
				$('#s_userStatus').combobox('setValue','');
			},
			search:function(flag){
				var params = {random:new Date()};
				if(flag==0){
					params.name = $('#quick_userName').searchbox('getValue');
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
					$('#quickSearchUser').click();
				}
				$('#userDatagrid').datagrid('load',params);
			}
		};
	</script>
</body>
</html>
