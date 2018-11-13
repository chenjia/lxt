<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>lxt管理系统</title>
<link id="skin1" rel="stylesheet" type="text/css" href="../../js/easyui/themes/default/easyui.css">
<link id="skin2" rel="stylesheet" type="text/css" href="../../js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/globalConfig.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/lxtUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/validateRules.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/layout.js"></script>
<script type="text/javascript">
function onEnter(keyCode){
	if(keyCode == 13){
		if($('#validCode').val()){
			login();
		}else{
			$('#validCode').focus();
		}
	}
}

function login(){
	var username = $('#username').val();
	var password = $('#password').val();
	var validCode = $('#validCode').val();
	var dialog = $('#dialog');
	dialog.window({
		title:'系统登录',
		iconCls:'icon-lock',
		width:250,
		height:120,
		collapsible:false,
		minimizable:false,
		maximizable:false,
		content:'<div><img src="images/progressbar.gif"/></div><br/><div align="center">正在登录中，请稍候...</div>',
		modal:true
	});
	$.ajax({
		url:'system!login.action',
		data:{'param.username':username,'param.password':password,'param.validCode':validCode},
		success:function(json){
			if(json.data){
				var screenname = json.data.screenname;
				$('#screenname').html(screenname);
				$('#noticeMarquee').html(globalOptions.greeting(screenname));
				$('#loginWindow').window('close');
				globalOptions.checkLogin = true;
			}else{
				$.messager.alert('登录失败','<div style="height:30px;line-height:30px;font-size:14px;">'+json.msg+'</div>','error');
				$('#validCodeImg').attr('src','../../captcha.png?r='+Math.random());
			}
		}
	});
}
</script>
</head>
<body style="margin:0px;padding:0px;overflow: hidden;">
	<div id="layout" style="margin:4px;">
		<div data-options="region:'north',split:true" style="overflow: hidden;">
			<table width="100%">
				<tr height="24">
					<td rowspan="2">
						<img src="../../images/logo.png">
					</td>
					<td>
						<a id="ddd" href="javascript:void(0)">top center</a>
					</td>
					<td align="right" style="padding:0px 6px;">
						<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-home'">首页</a>
						<span class="short-separator">|</span>
						<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-desktop'">桌面</a>
						<span class="short-separator">|</span>
						<a id="message" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-message'" onclick="openTab('消息中心')"><span>(1)消息</span></a>
						<span class="short-separator">|</span>
						<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-config'">设置</a>
						<span class="short-separator">|</span>
						<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-help'">帮助</a>
						<span class="short-separator">|</span>
						<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-logout'" onclick="logout()">退出</a>
					</td>
				</tr>
				<tr height="24">
					<td>
						bottom center
					</td>
					<td align="right" style="padding:0px 10px;">
						欢迎您，亲爱的用户[<span id="screenname"></span>]，系统时间：<span id="systime"></span>
					</td>
				</tr>
			</table>
		</div>
		<div data-options="region:'south',split:true" style="height:40px;overflow: hidden;">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr height="35">
					<td align="left" style="padding:0px 6px;width:20%;">
						<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-favorite'" onclick="addFavorite();">收藏</a>
						<span class="short-separator">|</span>
						<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-box'" onclick="suggest();">意见箱</a>
					</td>
					<td style="padding:0px 6px;width:60%;">
						<marquee id="noticeMarquee" direction="right" scrollamount="2" scrolldelay="50" onmouseover="this.stop()" onmouseout="this.start()">土豪，我们做朋友吧~~</marquee>
					</td>
					<td align="right" style="padding:0px 6px;width:20%;">
						<label for="changeSkinSel">更换皮肤：</label>
						<select id="changeSkinSel" class="easyui-combobox" style="width:100px;" data-options="panelWidth:100,panelHeight:300,editable:false,onChange:changeSkin">
							<option value="default" selected="selected">默认经典</option>
							<option value="black">黑色幽默</option>
							<option value="bootstrap">解靴带</option>
							<option value="gray">灰色空间</option>
							<option value="cupertino">蓝色忧郁</option>
							<option value="dark-hive">黑暗蜂房</option>
							<option value="pepper-grinder">纸张研磨</option>
							<option value="sunny">夏日阳光</option>
							<option value="metro">都市风情</option>
							<option value="metro-blue">蓝色都市</option>
							<option value="metro-gray">灰色都市</option>
							<option value="metro-green">绿色都市</option>
							<option value="metro-orange">橘色都市</option>
							<option value="metro-red">红色都市</option>
						</select>
					</td>
				</tr>
			</table>
		</div>
		<div data-options="region:'west',split:true,iconCls:'icon-navigation'" id='west' title="导航栏" style="width:207px;">
			<div class="easyui-accordion" data-options="fit:true" >
				<div title="流程管理" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
					<ul id="menuTree"></ul>
				</div>
				<div title="Help" data-options="iconCls:'icon-help'" style="padding:10px;">
					
				</div>
				<div title="TreeMenu" data-options="iconCls:'icon-search'" style="padding:10px;">
					
				</div>
			</div>
		</div>
		<div data-options="region:'center'">
			<div id="tabs" class="easyui-tabs" data-options="border:false,fit:true,headerCls:'background-color:white;'">
				<div data-options="iconCls:'icon-form',closable:true,href:'../form/formList.jsp'" title="表单列表">
					
				</div>
				<div data-options="iconCls:'icon-task',closable:true,href:'../workflow/taskList.jsp'" title="任务列表">
					
				</div>
				<div data-options="iconCls:'icon-task',closable:true,href:'../workflow/taskCompleteList.jsp'" title="已办任务">
					
				</div>
				<div data-options="iconCls:'icon-workflow',closable:true,href:'../workflow/workflowList.jsp'" title="流程列表">
					
				</div>
				<div data-options="iconCls:'icon-user',closable:true,href:'../system/userList.jsp'" title="用户列表">
					
				</div>
				<div data-options="iconCls:'icon-home',closable:true" title="系统首页" style="padding:10px">
					
				</div>
				<div data-options="iconCls:'icon-desktop',closable:true" title="我的桌面" style="padding:10px">
					
				</div>
			</div>
		</div>
	</div>
	<div id="loginWindow" class="easyui-window" data-options="modal:true,title:'快速登录',iconCls:'icon-lock',collapsible:false,minimizable:false,maximizable:false,resizable:false,closed:true" style="width:400px;height:250px;padding:20px 35px;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<table border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td width="50" height="50">
								<img src="../../images/user.png">
							</td>
							<td height="50" style="font-size:14px;">用户名：</td>
							<td width="200" height="50">
								<input type="text" id="username" name="username" value="admin" style="width: 164px; height: 32px; line-height: 34px; background: url(../../images/inputbg.gif) repeat-x; border: solid 1px #d1d1d1; font-size: 18px; font-family: Verdana, Geneva, sans-serif;padding-left:8px;">
							</td>
						</tr>
						<tr>
							<td height="50">
								<img src="../../images/password.png">
							</td>
							<td height="50" style="font-size:14px;">密　码：</td>
							<td height="50">
								<input type="password" name="password" value="admin" id="password" onkeypress="onEnter(event.keyCode)" style="width: 164px; height: 32px; line-height: 34px; background: url(../../images/inputbg.gif) repeat-x; border: solid 1px #d1d1d1; font-size: 16px;padding-left:8px;">
							</td>
						</tr>
						<tr>
							<td height="50">
								<img src="../../images/shield.png">
							</td>
							<td height="50" style="font-size:14px;">验证码：</td>
							<td height="50">
								<input type="text" id="validCode" name="validCode" onkeypress="javascript:if(event.keyCode==13)login()" maxlength="4" style="width: 64px; height: 32px; line-height: 34px; background: url(../../images/inputbg.gif) repeat-x; border: solid 1px #d1d1d1; font-size: 18px; font-family: Verdana, Geneva, sans-serif;padding-left:8px;">
								<img id="validCodeImg" src="../../captcha.png" title="看不清？换一张" style="vertical-align: middle;cursor:pointer;" onclick="this.src='../../captcha.png?r='+Math.random()">
							</td>
						</tr>
						<tr>
							<td height="40">&nbsp;</td>
							<td height="40">&nbsp;</td>
							<td height="40">
								<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-yes'" onclick="login()">登录</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	
	<div id="lxtWin"></div>
	
	<table class="loading" style="background:white;position:absolute;top:0px;left:0px;width:100%;height:100%;z-index:10000">
		<tr><td align="center"><img src="../../images/loading.gif"><br/><br/><span>页面加载中...</span></td></tr>
	</table>
</body>
</html>