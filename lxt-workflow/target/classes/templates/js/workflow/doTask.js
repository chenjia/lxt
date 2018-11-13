$(function(){
	$('.doTaskTool').fadeIn();
	$('.doTaskView').show();
});

var doTaskHandler = {
	params:{},
	getParams:function(){
		if(!this.params.hasOwnProperty('taskId')){
			var selectedTab = centerTabs.tabs('getSelected');
			var title = selectedTab.panel('options').title;
			$.extend(this.params,centerTabs.data(title));
		}
		return this.params;
	},
	getTaskInfo:function(){
		var params = this.getParams();
		$.ajax({
			url:'task!getTaskInfo.action',
			data:{'param.taskId':params.taskId},
			success:function(json){
				$.extend(doTaskHandler.params,json.data);
			}
		});
	},
	validateTaskForm:function(){
		return true;
	},
	getTransitions:function(){
		var transitions;
		$.ajax({
			async:false,
			url:'task!getTransitions.action',
			data:{'param.taskId':this.getParams().taskId},
			success:function(json){
				transitions = json.data;
			}
		});
		return transitions;
	},
	submit:function(){
		if(!this.validateTaskForm()){
			return false;
		}
		var transitions = this.getTransitions();
		var params = this.getParams();
		if(transitions.length==1){
			var transition = transitions[0];
			var confirmText = '任务将提交至['+transition.activity+']，并由['+transition.assigner+']处理，是否确认？';
			if(transition.nodeType=='end'){
				confirmText = '任务将提交至['+transition.activity+']，并结束流程，是否确认？';
			}
			MessageUtils.confirm('提交任务',confirmText, function(r){
				if(r){
					params['transition'] = transition.transition;
					$.ajax({
						url:'task!submit.action',
						data:ParamUtils.formatParam(params),
						success:function(json){
							if(json.data==0){
								MessageUtils.success('提交任务','任务提交成功！',function(){closeTab();});
								$('#taskDatagrid').datagrid('clearSelections');
								$('#taskDatagrid').datagrid('reload');
							}
						}
					});
				}
			});
		}else{
			
		}
	},
	sendback:function(){
		var params = this.getParams();
		if(params.attrs['isSendback']==1){
			MessageUtils.confirm('退回任务','确定要退回任务['+params.taskName+']吗？',function(r){
				if(r){
					if(params.attrs['sendbackWay']==3){
						$.ajax({
							url:'task!getHistoryActivity.action',
							data:ParamUtils.formatParam(params),
							success:function(json){
								var historySelector = '<div id="sendbackBox" style="padding:10px;">';
								var activityArray = json.data;
								for(var i=0;i<activityArray.length;i++){
									if(activityArray[i].activityName==params.taskName){
										continue;
									}
									historySelector += '<div><input type="radio" name="sendbackActivity" style="vertical-align:middle;" value="'+activityArray[i].activityName+'"/><label>'+activityArray[i].activityName+'</label></div><br/>';
								}
								historySelector += '</div>';
								var sendbackDialog = $('#sendbackWin').dialog({
									modal:true,
									title:'选择退回目标',
									iconCls:'icon-undo',
									collapsible:false,
									minimizable:false,
									maximizable:false,
									resizable:false,
									width:250,
									height:200,
									content:historySelector,
									buttons:[{
										text:'退回',
										iconCls:'icon-undo',
										handler:function(){
											params.target = $('#sendbackBox input:checked').val();
											$.ajax({
												url:'task!sendBack.action',
												data:ParamUtils.formatParam(params),
												success:function(json){
													if(json.data==0){
														MessageUtils.success('退回任务','任务退回成功！',function(){closeTab();});
														$('#taskDatagrid').datagrid('clearSelections');
														$('#taskDatagrid').datagrid('reload');
													}else{
														MessageUtils.fail("退回任务", json.msg);
													}
												}
											});
											sendbackDialog.dialog('close');
										}
									},{
										text:'取消',
										iconCls:'icon-no',
										handler:function(){
											sendbackDialog.dialog('close');
										}
									}]
								});
							}
						});
					}else{
						$.ajax({
							url:'task!sendBack.action',
							data:ParamUtils.formatParam(params),
							success:function(json){
								if(json.data==0){
									MessageUtils.success('退回任务','任务退回成功！',function(){closeTab();});
									$('#taskDatagrid').datagrid('clearSelections');
									$('#taskDatagrid').datagrid('reload');
								}
							}
						});
					}
				}
			});
		}else{
			MessageUtils.fail('无权限的操作','此步骤无退回的权限，如有需要请联系管理员！');
		}
	},
	showForm:function(){
		var selectedTab = centerTabs.tabs('getSelected');
		var centerPanel = selectedTab.find('.easyui-layout').layout('panel','center');
		centerPanel.panel('setTitle','表单');
		var panels = centerPanel.find('.easyui-panel');
		panels.fadeOut();
		var formPanel = $(panels[0]);
		formPanel.fadeIn();
		
	},
	showGraph:function(){
		var selectedTab = centerTabs.tabs('getSelected');
		var title = selectedTab.panel('options').title;
		var params = $.extend({},centerTabs.data(title));
		var centerPanel = selectedTab.find('.easyui-layout').layout('panel','center');
		centerPanel.panel('setTitle','流程图');
		var panels = centerPanel.find('.easyui-panel');
		panels.fadeOut();
		var graphPanel = $(panels[1]);
		var iframe = graphPanel.find('iframe');
		if(!iframe.attr('src')){
			iframe.attr('src','../workflow/graph.jsp?workflowId='+escape(params.workflowId)+'&executionId='+escape(params.executionId)+'&taskId='+escape(params.taskId));
		}
		graphPanel.fadeIn();
	},
	showSuggestion:function(){
		var selectedTab = centerTabs.tabs('getSelected');
		var title = selectedTab.panel('options').title;
		var params = $.extend({},centerTabs.data(title));
		var centerPanel = selectedTab.find('.easyui-layout').layout('panel','center');
		centerPanel.panel('setTitle','意见列表');
		var panels = centerPanel.find('.easyui-panel');
		panels.fadeOut();
		var suggestionPanel = $(panels[2]);
		var iframe = suggestionPanel.find('iframe');
		if(!iframe.attr('src')){
			iframe.attr('src','../workflow/suggestionList.jsp?workflowId='+params.workflowId+'&executionId='+params.executionId+'&taskId='+params.taskId);
		}
		suggestionPanel.fadeIn();
	},
	showAttach:function(){
		var selectedTab = centerTabs.tabs('getSelected');
		var centerPanel = selectedTab.find('.easyui-layout').layout('panel','center');
		centerPanel.panel('setTitle','附件列表');
		var panels = centerPanel.find('.easyui-panel');
		panels.fadeOut();
		var attachPanel = $(panels[3]);
		var iframe = graphPanel.find('iframe');
		if(!iframe.attr('src')){
			iframe.attr('src','../workflow/attachList.jsp?workflowId='+params.workflowId+'&executionId='+params.executionId+'&taskId='+params.taskId);
		}
		attachPanel.fadeIn();
	},
	showLog:function(){
		var selectedTab = centerTabs.tabs('getSelected');
		var centerPanel = selectedTab.find('.easyui-layout').layout('panel','center');
		centerPanel.panel('setTitle','日志列表');
		var panels = centerPanel.find('.easyui-panel');
		panels.fadeOut();
		var logPanel = $(panels[4]);
		var iframe = graphPanel.find('iframe');
		if(!iframe.attr('src')){
			iframe.attr('src','../workflow/logList.jsp?workflowId='+params.workflowId+'&executionId='+params.executionId+'&taskId='+params.taskId);
		}
		logPanel.fadeIn();
	}
};

doTaskHandler.getTaskInfo();