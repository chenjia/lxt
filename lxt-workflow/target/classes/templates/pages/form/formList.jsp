<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div class="easyui-panel" data-options="fit:true" style="padding:2px;">
		<div id="formDatagrid">
			<div id="formToolbar">
				<table width="100%">
					<tr>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="formHandler.createForm()">新建</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="formHandler.remove()">删除</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="formHandler.modify()">修改</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-view',plain:true" onclick="formHandler.review()">预览</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-yes',plain:true" onclick="formHandler.open()">启用</a>
						</td>
						<td width="10">
							<span class="long-separator">|</span>
						</td>
						<td width="60">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true" onclick="formHandler.close()">禁用</a>
						</td>
						<td align="right">
							<input id="quick_formName" class="easyui-searchbox" data-options="prompt:'请在此输入任务名称',searcher:quickSearchform,menu:'#formMM'" style="width:300px"/>
							<div id="formMM" style="width:120px">
								<div id="quickSearchform" data-options="name:'quick',iconCls:'icon-search'" onclick="$('#formSP').slideUp();">快速查询</div>
								<div data-options="name:'advance',iconCls:'icon-advance-search'" onclick="$('#formSP').slideDown();">高级查询</div>
							</div>
						</td>
					</tr>
				</table>
				<div id="formSP" class="panel-body" style="position:absolute;left:-1px;z-index:99999;width:100%;display:none;">
					<form id="formSearchForm" action="">
						<table width="100%" cellpadding="8">
							<tr style="height: 25px;line-height: 25px;" align="left">
								<td width="25%">
									<label for="s_formName">任务名称：</label>
									<input id="s_formName" class="searchbox" type="text" maxlength="20"/>
								</td>
								<td width="25%">
									<label for="s_formKey">任务Key：</label>
									<input id="s_formKey" class="searchbox" type="text" maxlength="20"/>
								</td>
								<td width="25%">
									<label for="s_formVersion">版本号：</label>
									<input id="s_formVersion" class="searchbox" type="text" maxlength="20"/>
								</td>
								<td align="right">
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="formHandler.search(1)">搜索</a>
								</td>
							</tr>
							<tr height="25">
								<td>
									<label for="s_formStartCreateTime">创建时间：</label>
									<input id="s_formStartCreateTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_formEndCreateTime" style="margin-left:30px;">至：</label>
									<input id="s_formEndCreateTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_formStatus">　状态：</label>
									<select id="s_formStatus" class="easyui-combobox" data-options="panelHeight:'65',editable:false">
										<option value="">全部</option>
										<option value="1">已发布</option>
										<option value="0">未发布</option>
									</select>
								</td>
								<td align="right">
									<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="formHandler.clear();">清空</a>
								</td>
							</tr>
							<tr height="25">
								<td>
									<label for="s_formStartPublishTime">发布时间：</label>
									<input id="s_formStartPublishTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_formEndPublishTime" style="margin-left:30px;">至：</label>
									<input id="s_formEndPublishTime" class="searchbox easyui-datebox" data-options="editable:false,formatter:DateUtils.shortDateFormatter"/>
								</td>
								<td>
									<label for="s_formMemo">　备注：</label>
									<input id="s_formMemo" class="searchbox" type="text" maxlength="20"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		
		<div id="formColumnMenu"></div>
		<div id="formCategoryColumnMenu"></div>
		<div id="formRowMenu" class="easyui-menu">
			<div id="rowMenu_formAdd" data-options="iconCls:'icon-add'" onclick="formHandler.createForm()">新建</div>
			<div class="menu-sep"></div>
			<div id="rowMenu_formRemove" data-options="iconCls:'icon-remove'" onclick="formHandler.remove()">删除</div>
			<div class="menu-sep"></div>
			<div id="rowMenu_formModify" data-options="iconCls:'icon-edit'" onclick="formHandler.modify()">修改</div>
			<div class="menu-sep"></div>
			<div id="rowMenu_formReview" data-options="iconCls:'icon-view'" onclick="formHandler.review()">预览</div>
			<div class="menu-sep"></div>
			<div id="rowMenu_formStatusOpen" data-options="iconCls:'icon-yes'" onclick="formHandler.open()">启用</div>
			<div class="menu-sep"></div>
			<div id="rowMenu_formStatusClosed" data-options="iconCls:'icon-no'" onclick="formHandler.close()">禁用</div>
		</div>
		<div id="formCategoryRowMenu" class="easyui-menu">
			<div id="rowMenu_formCategoryAdd" data-options="iconCls:'icon-add'" onclick="formHandler.createCategory()">新建分类</div>
			<div class="menu-sep"></div>
			<div id="rowMenu_formRemove" data-options="iconCls:'icon-remove'" onclick="formHandler.remove()">删除</div>
			<div class="menu-sep"></div>
			<div id="rowMenu_formCategoryRename" data-options="iconCls:'icon-edit'" onclick="formHandler.rename()">重命名</div>
		</div>
	</div>
	<script type="text/javascript">
		var formDatagrid = $('#formDatagrid').treegrid($.extend({},datagridOptions,{
			url:'form!queryByParent.action',
			idField:'nodeId',
			treeField:'formCategoryName',
			title:'表单列表',
			animate:true,
			toolbar:'#formToolbar',
			iconCls:'icon-form',
			pagination:false,
			striped:false,
			rownumbers:false,
			onBeforeLoad:function(row,param){
				if(row){
					$(this).treegrid('options').url = 'form!queryByParent.action?param.parentId='+row.formCategoryId;
				}else {
					$(this).treegrid('options').url = 'form!queryByParent.action?param.parentId=';
				}
			},
			loadFilter:function(data,parentId){
				if(data.data){
					return data.data;
				}else{
					return [];
				}
			},
			onAfterEdit:function(row,changes){
				$.ajax({
					url:'formCategory!rename.action',
					data:{'param.formCategoryId':row.nodeId,'param.formCategoryName':row.formCategoryName},
					error:function(){
						MessageUtils.fail('重命名分类','对不起，功能暂时不可用，重命名失败！');
					}
				});
			},
			frozenColumns:[[
				{field:'formId',checkbox:true},
				{field:'formCategoryName',title:'所属分类',width:200,editor:'text'}
		    ]],
			columns:[[
			    $.extend({},columnOptions,{field:'formName',title:'表单名称'}),
			    $.extend({},columnOptions,{field:'taskName',title:'任务名称'}),
			    $.extend({},columnOptions,{field:'createUserName',title:'创建者',hidden:true}),
			    $.extend({},columnOptions,{field:'createTime',title:'创建时间',hidden:true}),
			    $.extend({},columnOptions,{field:'modifyUserName',title:'最后操作人'}),
			    $.extend({},columnOptions,{field:'modifyTime',title:'最后更新时间'}),
			    $.extend({},columnOptions,{field:'status',title:'状态',width:50,formatter:function(value,row){
			    	var newValue;
			    	if(value==0){
						newValue = '<img tooltip="禁用" src="../../images/icons/no.png"/>';
					}else if(value==1){
						newValue = '<img tooltip="启用" src="../../images/icons/yes.png"/>';
					}else if(row.formId){
						newValue = '未知';
					}
					return newValue;
				}}),
				$.extend({},columnOptions,{field:'formCategoryId',title:'操作',formatter:function(value,row){
					if(row.formId){
						return '<a class="operation" href="javascript:void(0)" onclick="">查看</a>';
			    	}
			    }})
			]],
			onHeaderContextMenu:function(e, field){
				e.preventDefault();
				if (!formColumnMenu){
					createformColumnMenu();
				}
				formColumnMenu.menu('show', {
					left:e.pageX,
					top:e.pageY
				});
			},
			onContextMenu:function(e, row){
				$('#formDatagrid').treegrid('select',row.nodeId);
				e.preventDefault();
				if(row.formId){
					if(row.status==1){
						$('#formRowMenu').menu('disableItem',$('#rowMenu_formStatusOpen')[0]);
						$('#formRowMenu').menu('enableItem',$('#rowMenu_formStatusClosed')[0]);
					}else{
						$('#formRowMenu').menu('enableItem',$('#rowMenu_formStatusOpen')[0]);
						$('#formRowMenu').menu('disableItem',$('#rowMenu_formStatusClosed')[0]);
					}
					$('#formRowMenu').menu('show', {
						left:e.pageX,
						top:e.pageY
					});
				}else{
					$('#formCategoryRowMenu').menu('show', {
						left:e.pageX,
						top:e.pageY
					});
				}
			}
		}));
		
		var formColumnMenu,formRowMenu;
		function createformColumnMenu(){
			formColumnMenu = $('#formColumnMenu');
			formColumnMenu.menu({
				onClick: function(item){
					if (item.iconCls == 'icon-eye'){
						$('#formDatagrid').treegrid('hideColumn', item.name);
						formColumnMenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-hidden'
						});
					} else {
						$('#formDatagrid').treegrid('showColumn', item.name);
						formColumnMenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-eye'
						});
					}
				}
			});
			var fields = $('#formDatagrid').treegrid('getColumnFields');
			for(var i=0; i<fields.length; i++){
				var field = fields[i];
				var col = $('#formDatagrid').treegrid('getColumnOption', field);
				if(col.field=='formCategoyName'){
					continue;
				}
				if(col.hidden){
					formColumnMenu.menu('appendItem', {
						text: col.title,
						name: field,
						iconCls: 'icon-hidden'
					});
				}else{
					formColumnMenu.menu('appendItem', {
						text: col.title,
						name: field,
						iconCls: 'icon-eye'
					});
				}
			}
		}
		
		function quickSearchform(value,name){
			formHandler.search(0);
		}
		
		var formHandler = {
			createCategory:function(){
				MessageUtils.prompt('新建分类', '您希望新建的分类名称是？', function(r){
					if(r){
						var parent = $('#formDatagrid').treegrid('getSelected');
						$.ajax({
							url:'formCategory!save.action',
							data:{'param.parentId':parent.nodeId,'param.formCategoryName':r},
							success:function(data){
								if(data.status==0){
									$('#formDatagrid').treegrid('append',{
										parent:parent.nodeId,
										data:{data:[{
											nodeId:data.data,
											formCategoryName:r,
											state:'closed'
										}]}
									});
								}else{
									MessageUtils.fail('新建分类','此功能暂时不可用！');
								}
							}
						});
					}
				});
			},
			createForm:function(){
				window.open('../form/formConfig.jsp');
			},
			remove:function(){
				var row = $('#formDatagrid').treegrid('getSelected');
				if(!row){
					MessageUtils.alert('删除表单或分类','请选择要删除的表单或分类！','info');
				}else{
					if(row.formId){
						MessageUtils.confirm('删除表单','确定要删除表单['+row.formName+']吗？',function(r){
							if(r){
								$.ajax({
									url:'form!remove.action',
									async:false,
									data:{'param.formId':row.formId},
									success:function(data){
										if(data.status==0){
											$('#formDatagrid').treegrid('unselectAll');
											$('#formDatagrid').treegrid('reload',row.formCategoryId);
											MessageUtils.success('删除表单','表单['+row.formName+']删除成功！');
										}
									}
								});
							}
						});
					}else{
						MessageUtils.confirm('删除表单分类','确定要删除表单分类['+row.formCategoryName+']及子分类和表单吗？',function(r){
							if(r){
								$.ajax({
									url:'formCategory!remove.action',
									async:false,
									data:{'param.formCategoryId':row.formCategoryId},
									success:function(data){
										if(data.status==0){
// 											$('#formDatagrid').treegrid('unselectAll');
											$('#formDatagrid').treegrid('reload',$('#formDatagrid').treegrid('getParent',row.formCategoryId).formCategoryId);
											MessageUtils.success('删除表单分类','表单分类['+row.formCategoryName+']删除成功！');
										}
									}
								});
							}
						});
					}
				}
			},
			rename:function(){
				var node = $('#formDatagrid').treegrid('getSelected');
				$('#formDatagrid').treegrid('beginEdit',node.formCategoryId);
				var editor = $('#formDatagrid').treegrid('getEditor',{index:node.nodeId,field:'formCategoryName'});
				editor.target.blur(function(){
					$('#formDatagrid').treegrid('endEdit',node.nodeId);
					$('#formDatagrid').treegrid('update',{id:node.nodeId,row:{state:'closed'}});
				}).keypress(function(event){
					if(event.keyCode==27){
						$('#formDatagrid').treegrid('cancelEdit',node.nodeId);
					}
				}).focus();
			},
			open:function(){
				var node = $('#formDatagrid').treegrid('getSelected');
				$.ajax({
					url:'form!open.action',
					data:{'param.formId':node.formId},
					success:function(data){
						if(data.status==0){
							MessageUtils.success('启用表单', '表单['+node.formName+']启用成功！', function(){
								$('#formDatagrid').treegrid('update',{id:node.nodeId,row:{
									status:1
								}});
							});
						}
					}
				});
			},
			close:function(){
				var node = $('#formDatagrid').treegrid('getSelected');
				$.ajax({
					url:'form!close.action',
					data:{'param.formId':node.formId},
					success:function(data){
						if(data.status==0){
							MessageUtils.success('禁用表单', '表单['+node.formName+']禁用成功！', function(){
								$('#formDatagrid').treegrid('update',{id:node.nodeId,row:{
									status:0
								}});
							});
						}
					}
				});
			},
			modify:function(){
				var node = $('#formDatagrid').treegrid('getSelected');
				if(node.formId){
					window.open('../form/formConfig.jsp?formId='+node.formId);
				}else{
					MessageUtils.warning('修改表单','仅表单可以修改，请勿选择分类！');
				}
			},
			clear:function(){
				$('#formSearchForm').form('clear');
				$('#s_formStatus').combobox('setValue','');
			},
			search:function(flag){
				var params = {random:new Date()};
				if(flag==0){
					params.name = $('#quick_formName').searchbox('getValue');
				}else{
					params.name = $('#s_formName').val();
					params.key = $('#s_formKey').val();
					params.version = $('#s_formVersion').val();
					params.startCreateTime = $('#s_formStartCreateTime').datebox('getValue');
					params.endCreateTime = $('#s_formEndCreateTime').datebox('getValue');
					params.state = $('#s_formStatus').combobox('getValue');
					params.startPublishTime = $('#s_formStartPublishTime').combobox('getValue');
					params.endPublishTime = $('#s_formEndPublishTime').combobox('getValue');
					params.memo = $('#s_formMemo').val();
					$('#quickSearchform').click();
				}
				$('#formDatagrid').datagrid('load',params);
			}
		};
	</script>
</body>
</html>
