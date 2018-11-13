$.ajaxSetup({
	global:false,
	type:'POST',
	dataType:'json',
	error:function(request,textStatus,error){
		if(globalOptions.checkLogin){
			$.ajax({
				url:'system!checkLogin.action',
				success:function(json){
					if(json.data=='unlogin'){
						MessageUtils.alert("系统错误", "对不起，您尚未登录或已超时，请重新登录！",'warning',function(){
							$('#validCodeImg').attr('src','../../captcha.png?r='+Math.random());
							$('#loginWindow').window('open');
							setTimeout(function(){
								$.ajax({
									url:'system!getValidCode.action',
									dataType:'html',
									success:function(data){
										if(data){
											$('#validCode').val(data);
											login();
										}
									}
								});
							},1000);
						});
					}else{
						MessageUtils.fail('系统错误','系统出现未知错误！');
					}
				}
			});
			globalOptions.checkLogin = false;
		}
	}
});

var globalOptions = {
	checkLogin:true,
	greeting:function(screenname){
		return '欢迎用户：['+screenname+']登录lxt办公系统';
	}
};

var columnOptions = {
	width:120,
	sorted:true,
	sortable:true,
	resizable:true
};

var treeOptions = {
	checkbox:true,
	animate:true,
	cascadeCheck:false,
	lines:true,
	onBeforeLoad:function(node,param){
		if(!node){
			param.id = '';
		}
		param['param.parentId'] = param.id;
		delete param.id;
	}
};

var datagridOptions = {
	pageNumber:1,
	pageSize:10,
	singleSelect:true,
	rownumbers:true,
	fit:true,
	nowrap:true,
	pagination:true,
	striped:true,
	fitColumns:false,
	loadMsg:'数据加载中，请稍候...',
	loadFilter:function(data){
		return data.data;
	},
	onBeforeLoad:function(param){
		param = $.extend(param,ParamUtils.formatParam(param));
	},
	onLoadSuccess:function(){
		$(this).parent().find('.datagrid-cell').tooltip({
	        position:'right',
	        deltaX:5,
	        deltaY:5,
	        trackMouse:true,
	        content:function(){
	        	var tooltip = $(this).children().attr('tooltip');
	        	if(tooltip){return tooltip;}
	        	else{return $(this).html();}
	        }
	    });
	},
	onLoadError:function(){
		if(globalOptions.checkLogin){
			$.ajax({
				url:'system!checkLogin.action',
				success:function(json){
					if(json.data=='unlogin'){
						MessageUtils.alert("系统错误", "对不起，您尚未登录或已超时，请重新登录！",'warning',function(){
							$('#validCodeImg').attr('src','../../captcha.png?r='+Math.random());
							$('#loginWindow').window('open');
						});
					}else{
						MessageUtils.fail('系统错误','系统出现未知错误！');
					}
				}
			});
			globalOptions.checkLogin = false;
		}
	}
};