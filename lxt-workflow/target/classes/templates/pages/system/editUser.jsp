<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div class="easyui-panel" data-options="fit:true" style="padding:2px;">
		<div class="easyui-panel" data-options="fit:true,title:'修改用户',iconCls:'icon-user-edit'" style="padding:2px;">
			<form id="editUserForm">
				<table>
					<tr height="50">
						<td width="10%" align="right">
							<label for="ue_username"><span class="required">*</span>用户名：</label>
						</td>
						<td width="20%">
							<input id="ue_username" name="param.username" readonly="readonly" class="searchbox" type="text" maxlength="100"/>
						</td>
						<td width="10%" align="right">
							<label for="ue_truename"><span class="required">*</span>真实姓名：</label>
						</td>
						<td width="20%">
							<input id="ue_truename" name="param.truename" data-options="required:true,validType:'length[2,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
						<td width="10%" align="right">
							<label for="ue_screenname">昵称：</label>
						</td>
						<td width="20%">
							<input id="ue_screenname" name="param.screenname" class="searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr height="50">
						<td width="10%" align="right">
							<label for="ue_birthday"><span class="required">*</span>出生日期：</label>
						</td>
						<td>
							<input id="ue_birthday" name="param.birthday" data-options="required:true" class="easyui-datebox" style="width:152px;"/>
						</td>
						<td width="10%" align="right">
							<label for="ue_orgId"><span class="required">*</span>所在机构：</label>
						</td>
						<td width="20%">
							<input id="ue_orgId" name="param.orgId" class="searchbox" type="text" maxlength="20"/>
						</td>
						<td width="10%" align="right">
							<label for="ue_deptId"><span class="required">*</span>所在部门：</label>
						</td>
						<td width="20%">
							<input id="ue_deptId" name="param.deptId" class="searchbox" type="text" maxlength="20"/>
						</td>
					</tr>
					<tr height="50">
						<td width="10%" align="right">
							<label for="ue_mobile"><span class="required">*</span>移动电话：</label>
						</td>
						<td>
							<input id="ue_mobile" name="param.mobile" data-options="required:true,validType:'mobile'" class="easyui-validatebox searchbox" type="text" maxlength="20"/>
						</td>
						<td width="10%" align="right">
							<label for="ue_email"><span class="required">*</span>Email：</label>
						</td>
						<td width="20%">
							<input id="ue_email" name="param.email" data-options="required:true,validType:'email[4,64]'" class="easyui-validatebox searchbox" type="text" maxlength="20"/>
						</td>
						<td width="10%" align="right">
							<label for="ue_qq">QQ号码：</label>
						</td>
						<td width="20%">
							<input id="ue_qq" name="param.qq" data-options="validType:'number[5,15]'" class="easyui-validatebox searchbox" type="text" maxlength="20"/>
						</td>
					</tr>
					<tr height="50">
						<td width="10%" align="right">
							<label><span class="required">*</span>性别：</label>
						</td>
						<td>
							<label for="ue_gender_male" style="margin:0 10px;">男</label><input style="height:20px;line-height:20px;vertical-align: middle;" id="ue_gender_male" name="param.gender" value="1" class="searchbox" checked="checked" type="radio" maxlength="20"/>
							<label for="ue_gender_female" style="margin:0 10px 0 25px;">女</label><input style="height:20px;line-height:20px;vertical-align: middle;" id="ue_gender_female" name="param.gender" value="0" class="searchbox" type="radio" maxlength="20"/>
						</td>
						<td width="10%" align="right">
							<label for="ue_identity_card"><span class="required">*</span>身份证：</label>
						</td>
						<td>
							<input id="ue_identity_card" name="param.identityCard" data-options="required:true,validType:'identityNo'" class="easyui-validatebox searchbox" type="text" maxlength="20"/>
						</td>
					</tr>
					<tr height="50">
						<td colspan="6" width="100%" align="right">
							<input type="hidden" id="ue_userId" name="param.userId" />
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="editUserHandler.save();">保存</a>
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="editUserHandler.clear();">清空</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		var editUserId = $('#userDatagrid').data('editUser').userId;
		$('#ue_userId').val(editUserId);
		$.ajax({
			url:'user!getUserById.action',
			async:false,
			data:{'param.userId':editUserId},
			success:function(json){
				var data = json.data;
				var formData = {};
				for(var i in data){
					formData['param.'+i] = data[i];
				}
				var birthday = formData['param.birthday'];
				if(birthday.length>10){
					formData['param.birthday'] = birthday.substr(0,10);
				}
				$('#editUserForm').form('load',formData);
			}
		});
	
		Selector.departmentSelector($('#ue_deptId'),false,true);
		Selector.organizationSelector($('#ue_orgId'),false,true);
		
		var editUserHandler = {
			save:function(){
				$('#editUserForm').form('submit',{
					url:'../../user!editUser.action',
					onSubmit:function(){
						var isValid = $('#editUserForm').form('validate');
						if(isValid){
							MessageUtils.progress('修改用户','正在修改用户，请稍候...');
						}
						return isValid;
					},
					success:function(){
						$.messager.progress('close');
						MessageUtils.success('修改用户','成功修改用户：'+$('#ue_username').val());
						editUserHandler.clear();
						setTimeout(function(){
							$('#editUserForm').find('.validatebox-invalid').removeClass('validatebox-invalid');
						},100);
						$('#userDatagrid').datagrid('clearSelections');
						$('#userDatagrid').datagrid('reload');
					}
				});
			},
			clear:function(){
				$('#editUserForm').form('clear');
				$('#ue_gender_male').attr('checked','checked');
			}
		};
	</script>
</body>
</html>
