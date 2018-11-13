<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div class="easyui-panel" data-options="fit:true" style="padding:2px;">
		<div class="easyui-panel" data-options="fit:true,title:'新建用户',iconCls:'icon-user-add'" style="padding:2px;">
			<form id="addUserForm">
				<table>
					<tr height="50">
						<td width="10%" align="right">
							<label for="ua_username"><span class="required">*</span>用户名：</label>
						</td>
						<td width="20%">
							<input id="ua_username" name="param.username" data-options="required:true,validType:'username[4,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
						<td width="10%" align="right">
							<label for="ua_truename"><span class="required">*</span>真实姓名：</label>
						</td>
						<td width="20%">
							<input id="ua_truename" name="param.truename" data-options="required:true,validType:'length[2,32]'" class="easyui-validatebox searchbox" type="text" maxlength="100"/>
						</td>
						<td width="10%" align="right">
							<label for="ua_screenname">昵称：</label>
						</td>
						<td width="20%">
							<input id="ua_screenname" name="param.screenname" class="searchbox" type="text" maxlength="100"/>
						</td>
					</tr>
					<tr height="50">
						<td width="10%" align="right">
							<label for="ua_birthday"><span class="required">*</span>出生日期：</label>
						</td>
						<td>
							<input id="ua_birthday" name="param.birthday" data-options="required:true" class="easyui-datebox" style="width:152px;"/>
						</td>
						<td width="10%" align="right">
							<label for="ua_orgId"><span class="required">*</span>所在机构：</label>
						</td>
						<td width="20%">
							<input id="ua_orgId" name="param.orgId" class="searchbox" type="text" maxlength="20"/>
						</td>
						<td width="10%" align="right">
							<label for="ua_deptId"><span class="required">*</span>所在部门：</label>
						</td>
						<td width="20%">
							<input id="ua_deptId" name="param.deptId" class="searchbox" type="text" maxlength="20"/>
						</td>
					</tr>
					<tr height="50">
						<td width="10%" align="right">
							<label for="ua_mobile"><span class="required">*</span>移动电话：</label>
						</td>
						<td>
							<input id="ua_mobile" name="param.mobile" data-options="required:true,validType:'mobile'" class="easyui-validatebox searchbox" type="text" maxlength="20"/>
						</td>
						<td width="10%" align="right">
							<label for="ua_email"><span class="required">*</span>Email：</label>
						</td>
						<td width="20%">
							<input id="ua_email" name="param.email" data-options="required:true,validType:'email[4,64]'" class="easyui-validatebox searchbox" type="text" maxlength="20"/>
						</td>
						<td width="10%" align="right">
							<label for="ua_qq">QQ号码：</label>
						</td>
						<td width="20%">
							<input id="ua_qq" name="param.qq" data-options="validType:'number[5,15]'" class="easyui-validatebox searchbox" type="text" maxlength="20"/>
						</td>
					</tr>
					<tr height="50">
						<td width="10%" align="right">
							<label><span class="required">*</span>性别：</label>
						</td>
						<td>
							<label for="ua_gender_male" style="margin:0 10px;">男</label><input style="height:20px;line-height:20px;vertical-align: middle;" id="ua_gender_male" name="param.gender" value="1" class="searchbox" checked="checked" type="radio" maxlength="20"/>
							<label for="ua_gender_female" style="margin:0 10px 0 25px;">女</label><input style="height:20px;line-height:20px;vertical-align: middle;" id="ua_gender_female" name="param.gender" value="0" class="searchbox" type="radio" maxlength="20"/>
						</td>
						<td width="10%" align="right">
							<label for="ua_identity_card"><span class="required">*</span>身份证：</label>
						</td>
						<td>
							<input id="ua_identity_card" name="param.identityCard" data-options="required:true,validType:'identityNo'" class="easyui-validatebox searchbox" type="text" maxlength="20"/>
						</td>
					</tr>
					<tr height="50">
						<td colspan="6" width="100%" align="right">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="addUserHandler.save();">保存</a>
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="addUserHandler.clear();">清空</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		Selector.departmentSelector($('#ua_deptId'),false,true);
		Selector.organizationSelector($('#ua_orgId'),false,true);
		setTimeout(function(){
			$('#addUserForm').find('.validatebox-invalid').removeClass('validatebox-invalid');
		},100);
		var addUserHandler = {
			save:function(){
				$('#addUserForm').form('submit',{
					url:'../../user!addUser.action',
					onSubmit:function(){
						var isValid = $('#addUserForm').form('validate');
						if(isValid){
							MessageUtils.progress('新建用户','正在新建用户，请稍候...');
						}
						return isValid;
					},
					success:function(){
						$.messager.progress('close');
						MessageUtils.success('新建用户','成功新建用户：'+$('#ua_username').val());
						addUserHandler.clear();
						setTimeout(function(){
							$('#addUserForm').find('.validatebox-invalid').removeClass('validatebox-invalid');
						},100);
						$('#userDatagrid').datagrid('clearSelections');
						$('#userDatagrid').datagrid('reload');
					}
				});
			},
			clear:function(){
				$('#addUserForm').form('clear');
				$('#ua_gender_male').attr('checked','checked');
			}
		};
	</script>
</body>
</html>
