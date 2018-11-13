<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程制作</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/workflow.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
mxBasePath = '../../mxgraph';
mxLoadResources = false;

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
				checkLogin = true;
			}else{
				$.messager.alert('登录失败','<div style="height:30px;line-height:30px;font-size:14px;">'+json.msg+'</div>','error');
				$('#validCodeImg').attr('src','../../captcha.png?r='+Math.random());
			}
		}
	});
}
</script>
<script type="text/javascript" src="<%=request.getContextPath() %>/mxgraph/js/mxClient.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/globalConfig.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/lxtUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/workflow/workflowConfig.js"></script>
</head>
<body class="easyui-layout" style="margin:2px;">
	<div id="westPanel" data-options="region:'west',title:'工具箱',iconCls:'icon-briefcase',split:true" style="width:150px;">
		<div id="westAccordion" class="easyui-accordion" data-options="fit:true,border:false">
		    <div id="nodePanel" title="流程节点" data-options="iconCls:'icon-node',selected:true" style="padding:10px;">
		        <div class="graph-node">
					<div><img class="graphElement" src="../../mxgraph/images/start.png"/></div>
					<div>[开始节点]</div>
				</div>
				<div class="graph-node">
					<div><img class="graphElement" src="../../mxgraph/images/task_48.png"/></div>
					<div>[任务节点]</div>
				</div>
				<div class="graph-node">
					<div><img class="graphElement" src="../../mxgraph/images/end.png"/></div>
					<div>[结束节点]</div>
				</div>
				<div class="graph-node">
					<div><img class="graphElement" src="../../mxgraph/images/error.png"/></div>
					<div>[异常节点]</div>
				</div>
		    </div>
		    <div title="流转导向" data-options="iconCls:'icon-connect'" style="padding:10px;">
				connect
		    </div>
		    <div title="其它工具" data-options="iconCls:'icon-tool'" style="overflow:auto;">
				other
			</div>
    	</div>
    </div>
    <div id="centerPanel" data-options="region:'center',title:'绘图面板',iconCls:'icon-workflow-draw'">
    	<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north',border:false" style="height:31px">
				<div id="toolbar" class="graph-toolbar">
		    		<table cellspacing="0" cellpadding="0">
			    		<tr>
				    		<td><a href="javascript:void(0);" title="保存" onclick="saveWorkflow();"><span class="btn"><span class="icon icon-save"></span></span></a></td>
				    		<td><a href="javascript:void(0);" title="打印" onclick="editor.execute('exportImage');"><span class="btn"><span class="icon icon-print"></span></span></a></td>
				    		<td><div class="datagrid-btn-separator"></div></td>
				    		<td><a href="javascript:void(0);" title="复制" onclick="editor.execute('copy');"><span class="btn"><span class="icon icon-copy"></span></span></a></td>
				    		<td><a href="javascript:void(0);" title="粘贴" onclick="editor.execute('paste');"><span class="btn"><span class="icon icon-paste"></span></span></a></td>
				    		<td><a href="javascript:void(0);" title="剪切" onclick="editor.execute('cut');"><span class="btn"><span class="icon icon-cut"></span></span></a></td>
				    		<td><a href="javascript:void(0);" title="删除" onclick="editor.execute('delete');"><span class="btn"><span class="icon icon-delete"></span></span></a></td>
				    		<td><div class="datagrid-btn-separator"></div></td>
				    		<td><a href="javascript:void(0);" title="上一步" onclick="editor.execute('undo');"><span class="btn"><span class="icon icon-undo"></span></span></a></td>
				    		<td><a href="javascript:void(0);" title="下一步" onclick="editor.execute('redo');"><span class="btn"><span class="icon icon-redo"></span></span></a></td>
				    		<td><a href="javascript:void(0);" title="重置" onclick="editor.execute('save');"><span class="btn"><span class="icon icon-reset"></span></span></a></td>
				    		<td><div class="datagrid-btn-separator"></div></td>
				    		<td><a href="javascript:void(0);" title="放大" onclick="editor.execute('zoomIn');"><span class="btn"><span class="icon icon-zoom-in"></span></span></a></td>
				    		<td><a href="javascript:void(0);" title="缩小" onclick="editor.execute('zoomOut');"><span class="btn"><span class="icon icon-zoom-out"></span></span></a></td>
				    		<td><a href="javascript:void(0);" title="还原至初始大小" onclick="editor.execute('actualSize');"><span class="btn"><span class="icon icon-zoom-original"></span></span></a></td>
				    		<td><div class="datagrid-btn-separator"></div></td>
				    		<td><a href="javascript:void(0);" title="直线" onclick="setEdgeStype('');"><span class="btn"><span class="icon icon-straight"></span></span></a></td>
				    		<td><a href="javascript:void(0);" title="折线" onclick="setEdgeStype('elbowEdge');"><span class="btn"><span class="icon icon-curve"></span></span></a></td>
				    		<td><a href="javascript:void(0);" title="网格" onclick="toggleGrid();"><span class="btn"><span class="icon icon-grid"></span></span></a></td>
				    		<td><div class="datagrid-btn-separator"></div></td>
				    		<td><a href="javascript:void(0);" title="帮助" onclick="editor.execute('toggleHelp');"><span class="btn"><span class="icon icon-help"></span></span></a></td>
				    		<td><a href="javascript:void(0);" title="帮助" onclick="test();"><span class="btn"><span class="icon icon-help"></span></span></a></td>
			    		</tr>
		    		</table>
		    	</div>
			</div>
			<div data-options="region:'center',border:false">
				<div id="graph" style="width:100%;height:100%;"></div>
				<div id="outline" style="z-index:1;position:absolute;right:0px;bottom:0px;overflow:hidden;width:180px;height:135px;background:white;border:1px solid #91A1AE;"></div>
		    	<div id="splash" style="width:100%;height:100%;position:absolute;left:0px;top:0px;">
		    	<table width="100%" height="100%">
					<tr>
						<td align="center">
							<img src="../../images/loading.gif" style="vertical-align:middle;">
							<span style="font-size:14px;font-weight:bold;vertical-align:middle;">页面正在加载中，请稍候...</span>
						</td>
					</tr>
				</table>
				</div>
			</div>
		</div>
    </div>
    <div id="eastPanel" data-options="href:'../../mxgraph/pages/workflow.jsp',region:'east',title:'流程配置',iconCls:'icon-property',split:true" style="width:280px;"></div>
    
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
</body>
</html>