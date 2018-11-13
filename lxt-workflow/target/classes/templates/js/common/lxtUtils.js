$.extend({
	getUrlVars:function(){
		var vars = [], hash;
		var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
		for(var i = 0; i < hashes.length; i++){
			hash = hashes[i].split('=');
			vars.push(hash[0]);
			vars[hash[0]] = hash[1];
		}
		return vars;
	},
	getUrlVar:function(name){
		return $.getUrlVars()[name];
	}
});

var Lxt = {};

String.prototype.getParam=function(name){
	var reg =new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r =this.substr(this.indexOf("\?")+1).match(reg);
	if(r!=null)return unescape(r[2]);return null;
};

var DateUtils = {
	shortDateFormatter:function(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
	},
	fullDateFormatter:function(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		var h = date.getHours();
		var mi = date.getMinutes();
		var s = date.getSeconds();
		return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d)+' '+(h<10?('0'+h):h)+':'+(mi<10?('0'+mi):mi)+':'+(s<10?('0'+s):s);
	}
};

var ParamUtils = {
	formatParam:function(param){
		var newParam = {};
		for(var i in param){
			if(i!='attrs'){
				newParam['param.'+i] = param[i];
			}
		}
		return newParam;
	}
};

var ArrayUtils = {
	indexOf:function(array,value){
		for(var i=0;i<array.length;i++){
			if(array[i]==value){
				return i;
			}
		}
		return -1;
	},
	removeValue:function(array,value){
		var i = ArrayUtils.indexOf(array, value);
		if(i>=0){
			 array.splice(i,1);
		}
		return i;
	},
	remove:function(array,index){
		array.splice(index,1);
	}
};

var MessageUtils = {
	alert:function(title,content,icon,fn){
		$.messager.alert(title,'<div style="height:20px;line-height:20px;font-size:13px;">'+content+'</div>',icon,fn);
	},
	progress:function(title,text){
		$.messager.progress({
			title:title,
			interval:500,
			text:text
		});
	},
	fade:function(title,msg,iconCls){
		var icon = '';
		if(iconCls=='ok'){
			icon = '<img style="vertical-align:middle;padding-right:10px;" src="../../images/icons/yes_24.png"/>';
		}
		$.messager.show({
			title:title,
			msg:icon+'<span style="font-size:14px;">'+msg+'</span>',
			timeout:3000,
			showType:'fade'
		});
	},
	slide:function(title,msg,iconCls){
		var icon = '';
		if(iconCls=='ok'){
			icon = '<img style="vertical-align:middle;padding-right:10px;" src="../../images/icons/yes_24.png"/>';
		}
		$.messager.show({
			title:title,
			msg:icon+'<span style="font-size:14px;">'+msg+'</span>',
			timeout:3000,
			showType:'slide'
		});
	},
	success:function(title,content,fn){
		MessageUtils.alert(title,content,'info',fn);
	},
	warning:function(title,content,fn){
		MessageUtils.alert(title,content,'warning',fn);
	},
	fail:function(title,content){
		MessageUtils.alert(title,content,'error');
	},
	confirm:function(title,content,fn){
		$.messager.confirm(title,'<div style="height:20px;line-height:20px;font-size:13px;">'+content+'</div>',fn);
	},
	prompt:function(title,content,fn){
		$.messager.prompt(title,'<div style="height:20px;line-height:20px;font-size:13px;">'+content+'</div>',fn);
	}
};

Lxt.editors = {
	richEditorOptions:{
		title:'富文本编辑器',
		iconCls:'icon-view'
	},
	basicEditor:function(options){
		var basicEditorOptions = {
			title:'文本编辑器',
			iconCls:'icon-text-edit'
		};
		options = $.extend({},basicEditorOptions,options);
		var id = options.id;
		var obj = $('#'+id);
		var container = $('<span class="combo" style="width: 150px; height: 20px;"></span>');
		var textInput = $('<input class="combo-text easyui-validatebox" id="'+id+'" style="width: 128px; height: 20px; line-height: 20px;" type="text">');
		var iconSpan = $('<span></span>').append(
			$('<span class="combo-arrow '+options.iconCls+'" style="height: 20px;"></span>').click(function(){
				var editor = $('<div></div>').appendTo('body');
				editor.dialog({
					title:options.title,
					iconCls:options.iconCls,
					modal:true,
					resizable:true,
					width:700,
					height:400,
					content:'<textarea style="width:100%;height:100%;border:none;">'+textInput.val()+'</textarea>',
					buttons:[{
						text:'确定',
						iconCls:'icon-yes',
						handler:function(){
							var text = editor.find('textarea').val();
							textInput.val(text);
							editor.dialog('destroy');
						}
					},{
						text:'取消',
						iconCls:'icon-no',
						handler:function(){
							editor.dialog('destroy');
						}
					}],
					onClose:function(){
						editor.dialog('destroy');
					}
				});
			}).mouseover(function(){
				$(this).css({opacity:1});
			}).mouseout(function(){
				$(this).css({opacity:0.6});
			})
		);
		container.append(textInput).append(iconSpan);
		obj.after(container).remove();
	},
	richEditor:function(options){
		
	}
};

var Selector = {
	userSelector:function(options){
		var container = $('<span class="combo userbox" style="width: 150px; height: 20px;"></span>');
		var textInput = $('<input class="combo-text easyui-validatebox" '+(required?'data-options="required:true"':'')+' id="'+obj.attr('id')+'_text" readonly="readonly" style="width: 128px; height: 20px; line-height: 20px;" type="text">');
		var valueInput = $('<input id="'+obj.attr('id')+'_value" name="param.userId" type="hidden">');
		var iconSpan = $('<span></span>').append(
			$('<span class="combo-arrow icon-user" style="height: 20px;"></span>').click(function(){
				Selector.showUserSelector(valueInput,textInput,options.multiple);
			}).mouseover(function(){
				$(this).css({opacity:1});
			}).mouseout(function(){
				$(this).css({opacity:0.6});
			})
		);
		var value = obj.val();
		if(value){
			valueInput.val(value);
			$.ajax({
				url:'user!queryByIds.action',
				data:{'param.ids':value},
				success:function(json){
					var text = '';
					$.each(json.data,function(){
						text += this.deptName;
						if(multiple){text += ',';}
					});
					textInput.val(text);
				}
			});
		}
		container.append(textInput).append(iconSpan).append(valueInput);
		obj.after(container).remove();
	},
	showUserSelector:function(valueInput,textInput,multiple){
		var valueId = valueInput.attr('id');
		var value = valueInput.val();
		var text = textInput.val();
		var selector = $('<div></div>').appendTo('body');
		var datagrid = $('<table></table>').appendTo('body');
		selector.dialog({
			title:'选择用户',
			iconCls:'icon-user',
			resizable:true,
			width:700,
			height:400,
			closed:false,
			cache:false,
			content:datagrid,
			buttons:[{
				text:'确定',
				iconCls:'icon-yes',
				handler:function(){
					valueInput.val($('#'+valueId+'_selected_value').val());
					textInput.val($('#'+valueId+'_selected_text').val());
					textInput.validatebox('validate');
					selector.dialog('destroy');
				}
			},{
				text:'取消',
				iconCls:'icon-no',
				handler:function(){
					selector.dialog('destroy');
				}
			}],
			onClose:function(){
				selector.dialog('destroy');
			},
			modal:true
		});
		var toolbar = '<div><table><tr><td><label for="'+valueId+'_selected_text">已选用户：</label><input id="'+valueId+'_selected_text" value="'+text+'" class="searchbox"  style="width:300px" type="text" maxlength="100" readonly="readonly"/><input type="hidden" id="'+valueId+'_selected_value" value="'+value+'"/></td></tr></table></div>';
		datagrid.datagrid($.extend({},datagridOptions,{
			url:'user!queryByPage.action',
			idField:'userId',
			toolbar:toolbar,
			iconCls:'icon-user',
			border:false,
			singleSelect:!multiple,
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
			onLoadSuccess:function(data){
				datagrid.parent().find('.datagrid-cell').tooltip({
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
				var valueArray = value.split(',');
				if(valueArray.length>0){
					$.each(data.rows,function(index){
						var row = this;
						$.each(valueArray,function(){
							if(this==row.userId){
								datagrid.datagrid('selectRecord',row.userId);
							}
						});
					});
				}
			},
			onUnselect:function(rowIndex, rowData){
				var selectedValue = $('#'+valueId+'_selected_value').val();
				var selectedText = $('#'+valueId+'_selected_text').val();
				var selectedValueArray = selectedValue.split(',');
				var selectedTextArray = selectedText.split(',');
				var index = ArrayUtils.removeValue(selectedValueArray,rowData.userId);
				ArrayUtils.remove(selectedTextArray,index);
				$('#'+valueId+'_selected_value').val(selectedValueArray.toString());
				$('#'+valueId+'_selected_text').val(selectedTextArray.toString());
			},
			onSelect:function(rowIndex, rowData){
				if(multiple){
					if($('#'+valueId+'_selected_value').val().indexOf(rowData.userId+',')<0){
						$('#'+valueId+'_selected_value').val($('#'+valueId+'_selected_value').val()+rowData.userId+',');
						$('#'+valueId+'_selected_text').val($('#'+valueId+'_selected_text').val()+rowData.truename+',');
					}
				}else{
					$('#'+valueId+'_selected_value').val(rowData.userId);
					$('#'+valueId+'_selected_text').val(rowData.truename);
				}
			}
		}));
	},
	defaultDatagridOptions:{
		tipPosition:'right',
		required:true,
		iconCls:'',
		title:'请选择',
		resizable:true,
		width:700,
		height:400,
		closed:false,
		cache:false,
		url:'',
		valUrl:'',
		idField:'',
		nameField:''
	},
	remindRuleDatagridOptions:{
		id:'fc_formCategory',
		url:'formCategory!queryByParent.action',
		valUrl:'remindRule!queryByIds.action',
		idField:'remindRuleId',
		nameField:'name',
		iconCls:'icon-remindrule',
		columns:[[
			$.extend({},columnOptions,{field:'remindRuleId',checkbox:true}),
			$.extend({},columnOptions,{field:'name',title:'规则名称'}),
		    $.extend({},columnOptions,{field:'createTime',title:'创建日期',width:150}),
			$.extend({},columnOptions,{field:'memo',title:'备注',width:300})
		]]
	},
	initDatagridSelector:function(options){
		var options = $.extend({},Selector.defaultDatagridOptions,options);
		var obj = $('#'+options.id);
		var multiple = options.multiple;
		var required = options.required;
		var valueId = options.valueId||options.id;
		var textId = options.textId||options.id+'_text';
		var tipPosition = options.tipPosition;
		var container = $('<span class="combo" style="width: 150px; height: 20px;"></span>');
		var textInput = $('<input id="'+textId+'" class="combo-text easyui-validatebox" data-options="tipPosition:\''+tipPosition+'\''+(required?',required:true':'')+'" readonly="readonly" style="width: 128px; height: 20px; line-height: 20px;" type="text">');
		var valueInput = $('<input id="'+valueId+'" name="'+obj.attr('name')+'" type="hidden">');
		var iconSpan = $('<span></span>').append(
			$('<span class="combo-arrow '+options.iconCls+'" style="height: 20px;"></span>').click(function(){
				Selector.showDatagridSelector(options);
			}).mouseover(function(){
				$(this).css({opacity:1});
			}).mouseout(function(){
				$(this).css({opacity:0.6});
			})
		);
		var value = obj.val();
		if(value){
			valueInput.val(value);
			$.ajax({
				url:'remindRule!queryByIds.action',
				data:{'param.ids':value},
				success:function(json){
					var text = '';
					$.each(json.data,function(){
						text += this.name;
						if(multiple){text += ',';}
					});
					textInput.val(text);
				}
			});
		}
		container.append(textInput).append(iconSpan).append(valueInput);
		obj.after(container).remove();
	},
	showDatagridSelector:function(options){
		var valueId = options.valueId||options.id;
		var textId = options.textId||options.id+'_text';
		var valueInput = $('#'+valueId);
		var textInput = $('#'+textId);
		var multiple = options.multiple;
		var value = valueInput.val();
		var text = textInput.val();
		var selector = $('<div></div>').appendTo('body');
		var datagrid = $('<table></table>').appendTo('body');
		selector.dialog({
			title:'选择催办规则',
			iconCls:options.iconCls,
			resizable:true,
			width:700,
			height:400,
			closed:false,
			cache:false,
			content:datagrid,
			buttons:[{
				text:'确定',
				iconCls:'icon-yes',
				handler:function(){
					valueInput.val($('#'+valueId+'_selected_value').val());
					textInput.val($('#'+valueId+'_selected_text').val());
					textInput.validatebox('validate');
					selector.dialog('destroy');
				}
			},{
				text:'取消',
				iconCls:'icon-no',
				handler:function(){
					selector.dialog('destroy');
				}
			}],
			onClose:function(){
				selector.dialog('destroy');
			},
			modal:true
		});
		var toolbar = '<div><table><tr><td><label for="'+valueId+'_selected_text">已选：</label><input id="'+valueId+'_selected_text" value="'+text+'" class="searchbox"  style="width:300px" type="text" maxlength="100" readonly="readonly"/><input type="hidden" id="'+valueId+'_selected_value" value="'+value+'"/></td></tr></table></div>';
		datagrid.datagrid($.extend({},datagridOptions,{
			url:options.url,
			idField:options.idField,
			toolbar:toolbar,
			iconCls:options.idField,
			border:false,
			singleSelect:!multiple,
			columns:options.columns,
			onLoadSuccess:function(data){
				datagrid.parent().find('.datagrid-cell').tooltip({
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
				var valueArray = value.split(',');
				if(valueArray.length>0){
					$.each(data.rows,function(index){
						var row = this;
						$.each(valueArray,function(){
							if(this==row[options.idField]){
								datagrid.datagrid('selectRecord',row[options.idField]);
							}
						});
					});
				}
			},
			onUnselect:function(rowIndex, rowData){
				var selectedValue = $('#'+valueId+'_selected_value').val();
				var selectedText = $('#'+valueId+'_selected_text').val();
				var selectedValueArray = selectedValue.split(',');
				var selectedTextArray = selectedText.split(',');
				var index = ArrayUtils.removeValue(selectedValueArray,rowData[options.idField]);
				ArrayUtils.remove(selectedTextArray,index);
				$('#'+valueId+'_selected_value').val(selectedValueArray.toString());
				$('#'+valueId+'_selected_text').val(selectedTextArray.toString());
			},
			onSelect:function(rowIndex, rowData){
				if(multiple){
					if($('#'+valueId+'_selected_value').val().indexOf(rowData[options.idField]+',')<0){
						$('#'+valueId+'_selected_value').val($('#'+valueId+'_selected_value').val()+rowData[options.idField]+',');
						$('#'+valueId+'_selected_text').val($('#'+valueId+'_selected_text').val()+rowData[options.nameField]+',');
					}
				}else{
					$('#'+valueId+'_selected_value').val(rowData[options.idField]);
					$('#'+valueId+'_selected_text').val(rowData[options.nameField]);
				}
			}
		}));
	},
	
	
	
	
	
	defaultTreeOptions:$.extend({},treeOptions,{
		required:true,
		iconCls:'icon-view',
		title:'请选择',
		resizable:true,
		animate:true,
		lines:true,
		width:700,
		height:400,
		closed:false,
		cache:false,
		tipPosition:'right',
		url:'',
		valUrl:'',
		idField:'id',
		nameField:'name'
	}),
	initTreeSelector:function(options){
		var options = $.extend({},Selector.defaultTreeOptions,options);
		var obj = $('#'+options.id);
		var multiple = options.multiple;
		var required = options.required;
		var valueId = options.valueId||options.id;
		var textId = options.textId||options.id+'_text';
		var tipPosition = options.tipPosition;
		var container = $('<span class="combo" style="width: 150px; height: 20px;"></span>');
		var textInput = $('<input id="'+textId+'" class="combo-text easyui-validatebox" data-options="tipPosition:\''+tipPosition+'\''+(required?',required:true':'')+'" readonly="readonly" style="width: 128px; height: 20px; line-height: 20px;" type="text">');
		var valueInput = $('<input id="'+valueId+'" name="'+obj.attr('name')+'" type="hidden">');
		var iconSpan = $('<span></span>').append(
			$('<span class="combo-arrow '+options.iconCls+'" style="height: 20px;"></span>').click(function(){
				Selector.showTreeSelector(options);
			}).mouseover(function(){
				$(this).css({opacity:1});
			}).mouseout(function(){
				$(this).css({opacity:0.6});
			})
		);
		var value = obj.val();
		if(value){
			valueInput.val(value);
			$.ajax({
				url:options.valUrl,
				data:{'param.ids':value},
				success:function(json){
					var text = '';
					$.each(json.data,function(){
						text += this.name;
						if(multiple){text += ',';}
					});
					textInput.val(text);
				}
			});
		}
		container.append(textInput).append(iconSpan).append(valueInput);
		obj.after(container).remove();
	},
	showTreeSelector:function(options){
		var valueId = options.valueId||options.id;
		var textId = options.textId||options.id+'_text';
		var valueInput = $('#'+valueId);
		var textInput = $('#'+textId);
		var multiple = options.multiple;
		var value = valueInput.val();
		var text = textInput.val();
		var selector = $('<div></div>').appendTo('body');
		var tree = $('<ul></ul>').appendTo('body');
		var toolbar = '<div><table><tr><td><label for="'+valueId+'_selected_text">已选：</label><input id="'+valueId+'_selected_text" value="'+text+'" class="searchbox"  style="width:300px" type="text" maxlength="100" readonly="readonly"/><input type="hidden" id="'+valueId+'_selected_value" value="'+value+'"/></td></tr></table></div>';
		selector.dialog({
			title:options.title,
			iconCls:options.iconCls,
			resizable:true,
			width:700,
			height:400,
			closed:false,
			cache:false,
			toolbar:toolbar,
			content:tree,
			buttons:[{
				text:'确定',
				iconCls:'icon-yes',
				handler:function(){
					valueInput.val($('#'+valueId+'_selected_value').val());
					textInput.val($('#'+valueId+'_selected_text').val());
					textInput.validatebox('validate');
					selector.dialog('destroy');
				}
			},{
				text:'取消',
				iconCls:'icon-no',
				handler:function(){
					selector.dialog('destroy');
				}
			}],
			onClose:function(){
				selector.dialog('destroy');
			},
			modal:true
		});
		
		tree.tree($.extend({},Selector.defaultTreeOptions,{
			url:options.url,
			toolbar:toolbar,
			loadFilter:function(data,parent){
				var array = data.data;
				if(!array){return [];}
				var nodes = [];
				for(var i=0;i<array.length;i++){
					var obj = array[i];
					var node = {};
					if(obj.id){
						node.id = obj.id;
						node.text = obj.name;
						node.iconCle = 'tree-folder';
						node.state = 'closed';
					}
					nodes.push(node);
				}
				return nodes;
			},
			onCheck:function(node, checked){
				if(checked){
					if(!multiple){
						$.each(tree.tree('getChecked'),function(){
							if(node.id!=this.id){
								tree.tree('uncheck',this.target);
							}
						});
						$('#'+valueId+'_selected_value').val('');
						$('#'+valueId+'_selected_text').val('');
					}
					if($('#'+valueId+'_selected_value').val().indexOf(node.id)<0){
						var selectedValue = $('#'+valueId+'_selected_value').val()+node.id;
						var selectedText = $('#'+valueId+'_selected_text').val()+node.text;
						if(multiple){
							selectedValue += ',';
							selectedText += ',';
						}
						$('#'+valueId+'_selected_value').val(selectedValue);
						$('#'+valueId+'_selected_text').val(selectedText);
					}
				}else{
					var selectedValue = $('#'+valueId+'_selected_value').val();
					var selectedText = $('#'+valueId+'_selected_text').val();
					var selectedValueArray = selectedValue.split(',');
					var selectedTextArray = selectedText.split(',');
					var index = ArrayUtils.removeValue(selectedValueArray,node.id);
					ArrayUtils.remove(selectedTextArray,index);
					$('#'+valueId+'_selected_value').val(selectedValueArray.toString());
					$('#'+valueId+'_selected_text').val(selectedTextArray.toString());
				}
			},
			onLoadSuccess:function(node, data){
				var valueArray = value.split(',');
				if(valueArray.length>0){
					$.each(data,function(index){
						var curNode = this;
						$.each(valueArray,function(){
							if(this==curNode.id){
								curNode = tree.tree('find', curNode.id);
								tree.tree('check',curNode.target);
							}
						});
					});
				}
			},
			onSelect:function(node){
				if(!multiple){
					$.each(tree.tree('getChecked'),function(){
						if(node.id!=this.id){
							tree.tree('uncheck',this.target);
						}
					});
					$('#'+valueId+'_selected_value').val('');
					$('#'+valueId+'_selected_text').val('');
				}
				if($('#'+valueId+'_selected_value').val().indexOf(node.id)<0){
					var selectedValue = $('#'+valueId+'_selected_value').val()+node.id;
					var selectedText = $('#'+valueId+'_selected_text').val()+node.text;
					if(multiple){
						selectedValue += ',';
						selectedText += ',';
					}
					tree.tree('check',node.target);
					$('#'+valueId+'_selected_value').val(selectedValue);
					$('#'+valueId+'_selected_text').val(selectedText);
				}
			}
		}));
	},
	
	
	
	
	
	
	
	
	remindRuleSelector:function(options){
		var obj = $('#'+options.id);
		var multiple = options.multiple;
		var required = options.required;
		var valueId = options.valueId||options.id;
		var textId = options.textId||options.id+'_text';
		var container = $('<span class="combo remindrulebox" style="width: 150px; height: 20px;"></span>');
		var textInput = $('<input class="combo-text easyui-validatebox" data-options="tipPosition:\'left\''+(required?',required:true':'')+'" id="'+textId+'" readonly="readonly" style="width: 128px; height: 20px; line-height: 20px;" type="text">');
		var valueInput = $('<input id="'+valueId+'" name="'+obj.attr('name')+'" type="hidden">');
		var iconSpan = $('<span></span>').append(
			$('<span class="combo-arrow icon-remindrule" style="height: 20px;"></span>').click(function(){
				Selector.showRemindRuleSelector(valueInput,textInput,multiple);
			}).mouseover(function(){
				$(this).css({opacity:1});
			}).mouseout(function(){
				$(this).css({opacity:0.6});
			})
		);
		var value = obj.val();
		if(value){
			valueInput.val(value);
			$.ajax({
				url:'remindRule!queryByIds.action',
				data:{'param.ids':value},
				success:function(json){
					var text = '';
					$.each(json.data,function(){
						text += this.name;
						if(multiple){text += ',';}
					});
					textInput.val(text);
				}
			});
		}
		container.append(textInput).append(iconSpan).append(valueInput);
		obj.after(container).remove();
	},
	showRemindRuleSelector:function(valueInput,textInput,multiple){
		var valueId = valueInput.attr('id');
		var value = valueInput.val();
		var text = textInput.val();
		var selector = $('<div></div>').appendTo('body');
		var datagrid = $('<table></table>').appendTo('body');
		selector.dialog({
			title:'选择催办规则',
			iconCls:'icon-remindrule',
			resizable:true,
			width:700,
			height:400,
			closed:false,
			cache:false,
			content:datagrid,
			buttons:[{
				text:'确定',
				iconCls:'icon-yes',
				handler:function(){
					valueInput.val($('#'+valueId+'_selected_value').val());
					textInput.val($('#'+valueId+'_selected_text').val());
					textInput.validatebox('validate');
					selector.dialog('destroy');
				}
			},{
				text:'取消',
				iconCls:'icon-no',
				handler:function(){
					selector.dialog('destroy');
				}
			}],
			onClose:function(){
				selector.dialog('destroy');
			},
			modal:true
		});
		var toolbar = '<div><table><tr><td><label for="'+valueId+'_selected_text">已选规则：</label><input id="'+valueId+'_selected_text" value="'+text+'" class="searchbox"  style="width:300px" type="text" maxlength="100" readonly="readonly"/><input type="hidden" id="'+valueId+'_selected_value" value="'+value+'"/></td></tr></table></div>';
		datagrid.datagrid($.extend({},datagridOptions,{
			url:'remindRule!queryByPage.action',
			idField:'remindRuleId',
			toolbar:toolbar,
			iconCls:'icon-remindrule',
			border:false,
			singleSelect:!multiple,
			columns:[[
				$.extend({},columnOptions,{field:'remindRuleId',checkbox:true}),
				$.extend({},columnOptions,{field:'name',title:'规则名称'}),
			    $.extend({},columnOptions,{field:'createTime',title:'创建日期',width:150}),
				$.extend({},columnOptions,{field:'memo',title:'备注',width:300})
			]],
			onLoadSuccess:function(data){
				datagrid.parent().find('.datagrid-cell').tooltip({
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
				var valueArray = value.split(',');
				if(valueArray.length>0){
					$.each(data.rows,function(index){
						var row = this;
						$.each(valueArray,function(){
							if(this==row.remindRuleId){
								datagrid.datagrid('selectRecord',row.remindRuleId);
							}
						});
					});
				}
			},
			onUnselect:function(rowIndex, rowData){
				var selectedValue = $('#'+valueId+'_selected_value').val();
				var selectedText = $('#'+valueId+'_selected_text').val();
				var selectedValueArray = selectedValue.split(',');
				var selectedTextArray = selectedText.split(',');
				var index = ArrayUtils.removeValue(selectedValueArray,rowData.remindRuleId);
				ArrayUtils.remove(selectedTextArray,index);
				$('#'+valueId+'_selected_value').val(selectedValueArray.toString());
				$('#'+valueId+'_selected_text').val(selectedTextArray.toString());
			},
			onSelect:function(rowIndex, rowData){
				if(multiple){
					if($('#'+valueId+'_selected_value').val().indexOf(rowData.remindRuleId+',')<0){
						$('#'+valueId+'_selected_value').val($('#'+valueId+'_selected_value').val()+rowData.remindRuleId+',');
						$('#'+valueId+'_selected_text').val($('#'+valueId+'_selected_text').val()+rowData.name+',');
					}
				}else{
					$('#'+valueId+'_selected_value').val(rowData.remindRuleId);
					$('#'+valueId+'_selected_text').val(rowData.name);
				}
			}
		}));
	},
	
	departmentSelector:function(obj,multiple,required){
		var container = $('<span class="combo departmentbox" style="width: 150px; height: 20px;"></span>');
		var textInput = $('<input class="combo-text easyui-validatebox" '+(required?'data-options="required:true"':'')+' id="'+obj.attr('id')+'_text" readonly="readonly" style="width: 128px; height: 20px; line-height: 20px;" type="text">');
		var valueInput = $('<input id="'+obj.attr('id')+'_value" name="param.deptId" type="hidden">');
		var iconSpan = $('<span></span>').append(
			$('<span class="combo-arrow icon-department" style="height: 20px;"></span>').click(function(){
				var valueId = valueInput.attr('id');
				var value = valueInput.val();
				var text = textInput.val();
				var selector = $('<div></div>').appendTo('body');
				var datagrid = $('<table></table>').appendTo('body');
				selector.dialog({
					title:'选择部门',
					iconCls:'icon-department',
					resizable:true,
					width:700,
					height:400,
					closed:false,
					cache:false,
					content:datagrid,
					buttons:[{
						text:'确定',
						iconCls:'icon-yes',
						handler:function(){
							valueInput.val($('#'+valueId+'_selected_value').val());
							textInput.val($('#'+valueId+'_selected_text').val());
							textInput.validatebox('validate');
							selector.dialog('destroy');
						}
					},{
						text:'取消',
						iconCls:'icon-no',
						handler:function(){
							selector.dialog('destroy');
						}
					}],
					onClose:function(){
						selector.dialog('destroy');
					},
					modal:true
				});
				var toolbar = '<div><table><tr><td><label for="'+valueId+'_selected_text">已选部门：</label><input id="'+valueId+'_selected_text" value="'+text+'" class="searchbox"  style="width:300px" type="text" maxlength="100" readonly="readonly"/><input type="hidden" id="'+valueId+'_selected_value" value="'+value+'"/></td></tr></table></div>';
				datagrid.datagrid($.extend({},datagridOptions,{
					url:'department!queryByPage.action',
					idField:'deptId',
					toolbar:toolbar,
					iconCls:'icon-department',
					border:false,
					singleSelect:!multiple,
					columns:[[
					    $.extend({},columnOptions,{field:'deptId',checkbox:true}),
					    $.extend({},columnOptions,{field:'deptName',title:'部门名称'}),
					    $.extend({},columnOptions,{field:'deptCode',title:'部门代码'}),
					    $.extend({},columnOptions,{field:'orgManager',title:'负责人'}),
					    $.extend({},columnOptions,{field:'orgId',title:'所属机构'}),
					    $.extend({},columnOptions,{field:'phone',title:'固定电话'}),
					    $.extend({},columnOptions,{field:'mobile',title:'移动电话'}),
					    $.extend({},columnOptions,{field:'fax',title:'传真'}),
					    $.extend({},columnOptions,{field:'qq',title:'QQ'}),
					    $.extend({},columnOptions,{field:'email',title:'邮箱'}),
					    $.extend({},columnOptions,{field:'lastUpdateTime',title:'最后修改时间'}),
					    $.extend({},columnOptions,{field:'lastUpdateUser',title:'最后修改人'}),
						$.extend({},columnOptions,{field:'deptMemo',title:'备注',width:300})
					]],
					onLoadSuccess:function(data){
						datagrid.parent().find('.datagrid-cell').tooltip({
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
						var valueArray = value.split(',');
						if(valueArray.length>0){
							$.each(data.rows,function(index){
								var row = this;
								$.each(valueArray,function(){
									if(this==row.deptId){
										datagrid.datagrid('selectRecord',row.deptId);
									}
								});
							});
						}
					},
					onUnselect:function(rowIndex, rowData){
						var selectedValue = $('#'+valueId+'_selected_value').val();
						var selectedText = $('#'+valueId+'_selected_text').val();
						var selectedValueArray = selectedValue.split(',');
						var selectedTextArray = selectedText.split(',');
						var index = ArrayUtils.removeValue(selectedValueArray,rowData.deptId);
						ArrayUtils.remove(selectedTextArray,index);
						$('#'+valueId+'_selected_value').val(selectedValueArray.toString());
						$('#'+valueId+'_selected_text').val(selectedTextArray.toString());
					},
					onSelect:function(rowIndex, rowData){
						if(multiple){
							if($('#'+valueId+'_selected_value').val().indexOf(rowData.deptId+',')<0){
								$('#'+valueId+'_selected_value').val($('#'+valueId+'_selected_value').val()+rowData.deptId+',');
								$('#'+valueId+'_selected_text').val($('#'+valueId+'_selected_text').val()+rowData.deptName+',');
							}
						}else{
							$('#'+valueId+'_selected_value').val(rowData.deptId);
							$('#'+valueId+'_selected_text').val(rowData.deptName);
						}
					}
				}));
			}).mouseover(function(){
				$(this).css({opacity:1});
			}).mouseout(function(){
				$(this).css({opacity:0.6});
			})
		);
		var value = obj.val();
		if(value){
			valueInput.val(value);
			$.ajax({
				url:'department!queryByIds.action',
				data:{'param.ids':value},
				success:function(json){
					var text = '';
					$.each(json.data,function(){
						text += this.deptName;
						if(multiple){text += ',';}
					});
					textInput.val(text);
				}
			});
		}
		container.append(textInput).append(iconSpan).append(valueInput);
		obj.after(container).remove();
	},
	
	organizationSelector:function(obj,multiple,required){
		var container = $('<span class="combo organizationbox" style="width: 150px; height: 20px;"></span>');
		var textInput = $('<input class="combo-text easyui-validatebox" '+(required?'data-options="required:true"':'')+' id="'+obj.attr('id')+'_text" readonly="readonly" style="width: 128px; height: 20px; line-height: 20px;" type="text">');
		var valueInput = $('<input id="'+obj.attr('id')+'_value" name="param.orgId" type="hidden">');
		var iconSpan = $('<span></span>').append(
			$('<span class="combo-arrow icon-organization" style="height: 20px;"></span>').click(function(){
				var valueId = valueInput.attr('id');
				var value = valueInput.val();
				var text = textInput.val();
				var selector = $('<div></div>').appendTo('body');
				var toolbar = '<div class="datagrid-toolbar"><table><tr><td><label for="'+valueId+'_selected_text">已选机构：</label><input id="'+valueId+'_selected_text" value="'+text+'" class="searchbox" style="width:300px" type="text" maxlength="100" readonly="readonly"/><input type="hidden" id="'+valueId+'_selected_value" value="'+value+'"/></td></tr></table></div>';
				var tree = $('<ul></ul>').appendTo('body');
				selector.dialog({
					title:'选择机构',
					iconCls:'icon-organization',
					resizable:true,
					width:700,
					height:400,
					closed:false,
					cache:false,
					content:$('<div class="easyui-panel" data-options="fit:true,border:false"></div>').append(toolbar).append(tree),
					buttons:[{
						text:'确定',
						iconCls:'icon-yes',
						handler:function(){
							valueInput.val($('#'+valueId+'_selected_value').val());
							textInput.val($('#'+valueId+'_selected_text').val());
							textInput.validatebox('validate');
							selector.dialog('destroy');
						}
					},{
						text:'取消',
						iconCls:'icon-no',
						handler:function(){
							selector.dialog('destroy');
						}
					}],
					onClose:function(){
						selector.dialog('destroy');
					},
					modal:true
				});
				tree.tree($.extend({},treeOptions,{
					url:'organization!queryByParentId.action',
					toolbar:toolbar,
					iconCls:'icon-organization',
					border:false,
					loadFilter:function(data,parent){
						var array = data.data;
						if(!array){return [];}
						var nodes = [];
						for(var i=0;i<array.length;i++){
							var obj = array[i];
							var node = {};
							if(obj.parentOrg){
								node.id = obj.orgId;
								node.text = obj.orgName;
								node.iconCle = 'tree-folder';
								node.state = 'closed';
							}
							nodes.push(node);
						}
						return nodes;
					},
					onLoadSuccess:function(node, data){
						var valueArray = value.split(',');
						if(valueArray.length>0){
							$.each(data,function(index){
								var curNode = this;
								$.each(valueArray,function(){
									if(this==curNode.id){
										curNode = tree.tree('find', curNode.id);
										tree.tree('check',curNode.target);
									}
								});
							});
						}
					},
					onCheck:function(node, checked){
						if(checked){
							if(!multiple){
								$.each(tree.tree('getChecked'),function(){
									if(node.id!=this.id){
										tree.tree('uncheck',this.target);
									}
								});
								$('#'+valueId+'_selected_value').val('');
								$('#'+valueId+'_selected_text').val('');
							}
							if($('#'+valueId+'_selected_value').val().indexOf(node.id)<0){
								var orgValue = $('#'+valueId+'_selected_value').val()+node.id;
								var orgText = $('#'+valueId+'_selected_text').val()+node.text;
								if(multiple){
									orgValue += ',';
									orgText += ',';
								}
								$('#'+valueId+'_selected_value').val(orgValue);
								$('#'+valueId+'_selected_text').val(orgText);
							}
						}else{
							var selectedValue = $('#'+valueId+'_selected_value').val();
							var selectedText = $('#'+valueId+'_selected_text').val();
							var selectedValueArray = selectedValue.split(',');
							var selectedTextArray = selectedText.split(',');
							var index = ArrayUtils.removeValue(selectedValueArray,node.id);
							ArrayUtils.remove(selectedTextArray,index);
							$('#'+valueId+'_selected_value').val(selectedValueArray.toString());
							$('#'+valueId+'_selected_text').val(selectedTextArray.toString());
						}
					}
				}));
			}).mouseover(function(){
				$(this).css({opacity:1});
			}).mouseout(function(){
				$(this).css({opacity:0.6});
			})
		);
		var value = obj.val();
		if(value){
			valueInput.val(value);
			$.ajax({
				url:'organization!queryByIds.action',
				data:{'param.ids':value},
				success:function(json){
					var text = '';
					$.each(json.data,function(){
						text += this.orgName;
						if(multiple){text += ',';}
					});
					textInput.val(text);
				}
			});
		}
		container.append(textInput).append(iconSpan).append(valueInput);
		obj.after(container).remove();
	}
};