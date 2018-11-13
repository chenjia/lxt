$(function(){
	$.ajax({
		url:'../../system!getInitData.action',
		success:function(json){
			var systime = json.data.systime;
			var screenname = json.data.screenname;
			globalOptions.systime = systime;
			$('#screenname').html(screenname);
			$('#noticeMarquee').html(globalOptions.greeting(screenname));
		}
	});
	
	
	var isIE = $.browser.msie;
	var extWidth = 8,extHeight = 8;
	if(isIE){
		extWidth += 2;
		extHeight += 2;
	}
	var width = $(document).width()-extWidth;
	var height = $(document).height()-extHeight;
	$('#layout').css({width:width,height:height}).layout();
	setTimeout(function(){
		$('.loading').fadeOut();
	},500);
	initLayout();
	
});

function initLayout(){
	initWestPanel();
	initCenterPanel();
}
var centerTabs;
function initWestPanel(){
	var menuData = [{
		id:'myWorkspace',
		text:'我的工作区',
		children: [{
			id:'taskList',
			text:'任务列表',
			iconCls:'icon-task',
			attributes:{href:'../workflow/taskList.jsp'}
		},{
			id:'taskCompleteList',
			text:'已办任务',
			iconCls:'icon-task',
			attributes:{href:'../workflow/taskCompleteList.jsp'}
		}]
	},{
		id:'workflowConfig',
		text:'流程配置',
		attributes:{href:'../workflow/workflowConfig.jsp',target:'blank'},
		state: 'closed',
		children: [{
			text: 'Item11'
		},{
			text: 'Item12'
		}]
	},{
		text: '流程监控'
	},{
		id:'workflowList',
		text:'流程列表',
		iconCls:'icon-workflow',
		attributes:{href:'../workflow/workflowList.jsp'}
	}];
	centerTabs = $('#tabs');
	$('#menuTree').tree({
		animate:true,
		onClick:function(node){
			var attr = node.attributes;
			addTab(node.text,attr.href,node.iconCls,attr.target);
		},
		data:menuData
	});
	$('#westAccordion').accordion({fit:true,border:false});
}

function changeSkin(newValue,oldValue){
	$('#lxtWin').window({
		modal:true,
		title:'处理中',
		iconCls:'icon-coffee',
		collapsible:false,
		minimizable:false,
		maximizable:false,
		resizable:false,
		width:250,
		height:120,
		content:'<div id="progressbar" class="easyui-progressbar" data-options="text:\'正在切换皮肤，请稍候...\'" style="margin:28px 20px 0px;width:200px;"></div>'
	});
	var skinLoaded = false;
	var progress = $('#progressbar');
	var progressValue = progress.progressbar('getValue');
	var progressInterval = setInterval(function(){
		progress.progressbar('setValue',progressValue+=5);
		if(progressValue==100){
			if(!skinLoaded){
				progressValue = 0;
			}
		}
	},5);
	
	var orgBgColor = $('.panel-body').css('background-color');
	var orgColor = $('.panel-title').css('color');
	var orgWindow = $('.window').css('background-color');
	$('#skin2').attr({href:'../../js/easyui/themes/'+newValue+'/easyui.css'});
	var skinProgress = setInterval(function(){
		var curBgColor = $('.panel-body').css('background-color');
		var curColor = $('.panel-title').css('color');
		var curWindow = $('.window').css('background-color');
		if(orgBgColor!=curBgColor || orgColor!=curColor || orgWindow!=curWindow){
			skinLoaded = true;
			$('#skin1').attr({href:'../../js/easyui/themes/'+newValue+'/easyui.css'});
			if(progress.progressbar('getValue')==100){
				clearInterval(progressInterval);
				clearInterval(skinProgress);
				$('#lxtWin').window('close');
			}
		}
	},200);
}

function addFavorite(){
	var title=document.title;
	var url=document.location.href;
	if (window.sidebar) window.sidebar.addPanel(title, url,"");
	else if(window.opera && window.print){
	var mbm = document.createElement('a');
	mbm.setAttribute('rel','sidebar');
	mbm.setAttribute('href',url);
	mbm.setAttribute('title',title);
	mbm.click();}
	else if( document.all ) window.external.AddFavorite( url, title);
}

setInterval(function(){
	refreshTime();
	alertMessage();
},1000);

function refreshTime(){
	var systime = globalOptions.systime;
	if(systime != undefined){
		var time = new Date(systime);
		time.setSeconds(time.getSeconds()+1);
		globalOptions.systime = time.getTime();
		var month = time.getMonth()+1;
		var date = time.getDate();
		var hours = time.getHours();
		var minutes = time.getMinutes();
		var seconds = time.getSeconds();
		$('#systime').html(time.getFullYear()+'-'+(month<10?'0'+month:month)+'-'+(date<10?'0'+date:date)+' '+(hours<10?'0'+hours:hours)+':'+(minutes<10?'0'+minutes:minutes)+':'+(seconds<10?'0'+seconds:seconds));
	}
}

function alertMessage(){
	$('#message').fadeOut().fadeIn();
}

function initCenterPanel(){
	
}

function addTab(text,href,iconCls,target,params){
	if(target=='blank'){
		window.open(href);
	}else{
		var exists = centerTabs.tabs('exists',text);
		if(exists){
			centerTabs.tabs('select',text);
		}else{
			centerTabs.data(text,params).tabs('add',{
				closable:true,
				title:text,
				iconCls:iconCls,
				href:href
			});
		}
	}
}

function closeTab(text){
	if(!text){
		var selectedTab = centerTabs.tabs('getSelected');
		text = selectedTab.panel('options').title;
	}
	centerTabs.tabs('close',text);
}

function logout(){
	MessageUtils.confirm("退出系统", "您确定要退出系统吗?", function(r){
		if(r){
			$.ajax({
				url:'system!logout.action',
				success:function(data){
					if(data){
						location.href="../../login.jsp";
					}
				}
			});
		}
	});
}

function suggest(){
    $('#ddd').tooltip({  
        position: 'right',  
        content: '<span style="color:#fff">This is the tooltip message.</span>',  
        onShow: function(){  
            $(this).tooltip('tip').css({  
                backgroundColor: '#666',  
                borderColor: '#666'  
            });  
        }  
    });  
}