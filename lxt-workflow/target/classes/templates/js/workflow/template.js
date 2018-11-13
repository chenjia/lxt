var textEditor = {
	type:'text',
	options:{required: true,length:[0,64]}
};
var textareaEditor = {
	type:'textarea',
	options:{required: true,length:[0,255]}
};
var booleanEditor = {
	type:'checkbox',
	options:{on:1,off:0}
};
var formTree,formCombo,formTreeData = [{id:'root_formcategory',text:'无表单'}],formSelector = {
	type:'combotree',
	options:{
		data:formTreeData,
//		url:'form!queryByParent.action',
		multiple:true,
		lines:true,
		cascadeCheck:true,
		onlyLeafCheck:true,
		idField:'id',
		textField:'text',
		animate:true,
		loadFilter:function(data,parent){
			var array = data.data;
			if(!array){return [];}
			var nodes = [];
			for(var i=0;i<array.length;i++){
				var obj = array[i];
				var node = {};
				if(obj.parentId){
					node.id = obj.id;
					node.text = obj.name;
					node.iconCle = 'tree-folder';
					node.state = 'closed';
				}else{
					node.id = obj.formId;
					node.text = obj.formName;
					node.iconCls = "tree-file";
				}
				nodes.push(node);
			}
			return nodes;
		},
		onBeforeLoad:function(node,param){
			formTree = $(this);
			if(!node){
				param.id = 'root_formcategory';
			}
			param['param.parentId'] = param.id;
			delete param.id;
		},
		onLoadSuccess:function(){
			formTreeData = $(this).tree('options').data;
		},
		onChange:function(newValue, oldValue){
			formCombo = $(this);
			if(newValue=='root_formcategory'){
				formTree.tree('options')['url'] = 'form!queryByParent.action';
				formTree.tree('reload');
			}
			if(formTreeData){
				formTree.tree('options')['url'] = 'form!queryByParent.action';
				formTree.tree('reload');
				formTree.tree('loadData',formTreeData);
			}
		},
		onSelect:function(node){
			var isLeaf = $(this).tree('isLeaf',node.target);
			if(isLeaf){
				 $(this).tree('check',node.target);
			}else{
				MessageUtils.fail('选择表单','请选择表单而非表单分类！');
			}
		},
		onCheck:function(node, checked){
			if(checked){
				formatters.formValues[node.id] = node.text;
				var nodes = $(this).tree('getChecked');
				for(var i=0;i<nodes.length;i++){
					var tempNode = nodes[i];
					if(tempNode.id!=node.id){
						$(this).tree('uncheck',tempNode.target);
					}
				}
			}
		}
	}
};

var formatters = {
	state:function(value,rowData){
		if(value==1){return '开启';}
		else if(value==0){return '关闭';}
		return value;
	},
	form:function(value,rowData){
		if(formatters.formValues[value]){
			value = formatters.formValues[value];
		}
		return value;
	},
	formValues:{'root_formcategory':'无表单'},
//	form:function(value,rowData){
//		if(typeof(value)=='string'){
//			if(value.charAt(0)=='[' && value.charAt(value.length-1)==']'){
//    			value = eval(value);
//    		}else{
//    			value = eval('['+value+']');
//    		}
//		}
//		var text = '';
//		$.each(value,function(){
//			var curValue = this;
//			$.each(forms,function(){
//				if(curValue==this.formId){
//					text += this.formName+',';
//					return true;
//				}
//			});
//		});
//		return text.substr(0,text.length-1);
//	},
	manual:function(value,rowData){
		if(value==1){return '是';}
		else if(value==0){return '否';}
		return value;
	},
	isTakeback:function(value,rowData){
		if(value==1){return '是';}
		else if(value==0){return '否';}
		return value;
	},
	isDelegate:function(value,rowData){
		if(value==1){return '是';}
		else if(value==0){return '否';}
		return value;
	},
	isSendback:function(value,rowData){
		if(value==1){return '是';}
		else if(value==0){return '否';}
		return value;
	},
	isCheck:function(value,rowData){
		if(value==1){return '是';}
		else if(value==0){return '否';}
		return value;
	},
	isUpload:function(value,rowData){
		if(value==1){return '是';}
		else if(value==0){return '否';}
		return value;
	},
	isAdvice:function(value,rowData){
		if(value==1){return '是';}
		else if(value==0){return '否';}
		return value;
	},
	Country:function(value,rowData){
		if(value){
			var text = '';
			$.each(value,function(){
				var curValue = this;
				$.each(countryData,function(){
					if(curValue==this.id){
						text += this.text+',';
					}
				});
			});
			return text.substr(0,text.length-1);
		}
		return value;
	}
};

function showProperties(cell){
	var dataBuilder = new DataBuilder();
	var propertyGrid = $('#propertygrid');
	var panel = $(document.body).layout('panel','east');
	propertyGrid.data('cell',cell);
	var nodeType = cell.getAttribute('nodeType');
	
	var workflowKey = cell.getAttribute('workflowKey');
	var label = cell.getAttribute('label');
	var version = cell.getAttribute('version');
	var state = cell.getAttribute('state');
	var description = cell.getAttribute('description');
	var manual = cell.getAttribute('manual');
	var startRule = cell.getAttribute('startRule');
	var beforeInEvent = cell.getAttribute('beforeInEvent');
	var afterInEvent = cell.getAttribute('afterInEvent');
	var beforeOutEvent = cell.getAttribute('beforeOutEvent');
	var afterOutEvent = cell.getAttribute('afterOutEvent');
	var form = cell.getAttribute('form');
	var remindRule = cell.getAttribute('remindRule');
	var handlerType = cell.getAttribute('handlerType');
	var handler = cell.getAttribute('handler');
	var isTakeback = cell.getAttribute('isTakeback');
	var isDelegate = cell.getAttribute('isDelegate');
	var isUpload = cell.getAttribute('isUpload');
	var isAdvice = cell.getAttribute('isAdvice');
	var isSendback = cell.getAttribute('isSendback');
	var sendbackTarget = cell.getAttribute('sendbackTarget');
	var isCheck = cell.getAttribute('isCheck');
	var checkTarget = cell.getAttribute('checkTarget');
	
	if('workflow'==nodeType){
		panel.panel('setTitle','流程配置');
		dataBuilder.addProperty({id:'workflowKey',name:'流程Key',value:workflowKey,height:35,required:true,group:'基本信息',editor:textEditor});
		dataBuilder.addProperty({id:'label',name:'流程名称',value:label,height:35,required:true,group:'基本信息',editor:textEditor});
		dataBuilder.addProperty({id:'version',name:'流程版本',value:version,height:35,required:true,group:'基本信息',editor:textEditor});
		dataBuilder.addProperty({id:'state',name:'流程状态',value:state,height:35,required:true,group:'基本信息',editor:booleanEditor});
		dataBuilder.addProperty({id:'description',name:'　流程描述',value:description,height:60,required:false,group:'基本信息',editor:textareaEditor});
		dataBuilder.addProperty({id:'manual',name:'手动触发',value:manual,height:35,required:true,group:'触发规则',editor:booleanEditor});
		dataBuilder.addProperty({id:'startRule',name:'　发起规则',value:startRule,height:35,required:false,group:'触发规则',editor:textareaEditor});
		dataBuilder.addProperty({id:'beforeInEvent',name:'　前置事件',value:beforeInEvent,height:35,required:false,group:'事件',editor:textareaEditor});
		dataBuilder.addProperty({id:'afterInEvent',name:'　发起事件',value:afterInEvent,height:35,required:false,group:'事件',editor:textareaEditor});
		dataBuilder.addProperty({id:'beforeOutEvent',name:'　结束事件',value:beforeOutEvent,height:35,required:false,group:'事件',editor:textareaEditor});
		dataBuilder.addProperty({id:'afterOutEvent',name:'　后置事件',value:afterOutEvent,height:35,required:false,group:'事件',editor:textareaEditor});
		$('#propertygrid').propertygrid('loadData',dataBuilder.data);
	}else if('task'==nodeType){
		panel.panel('setTitle','任务节点配置');
		dataBuilder.addProperty({id:'label',name:'任务名称',value:label,height:35,required:true,group:'基本信息',editor:textEditor});
		dataBuilder.addProperty({id:'description',name:'　任务描述',value:description,height:60,required:false,group:'基本信息',editor:textareaEditor});
		dataBuilder.addProperty({id:'form',name:'业务表单',value:form,height:35,required:true,group:'基本信息',editor:formSelector});
		dataBuilder.addProperty({id:'remindRule',name:'催办规则',value:remindRule,height:35,required:true,group:'基本信息',editor:textEditor});
		dataBuilder.addProperty({id:'handlerType',name:'参与类型',value:handlerType,height:35,required:true,group:'参与者',editor:textEditor});
		dataBuilder.addProperty({id:'handler',name:'处理人',value:handler,height:35,required:true,group:'参与者',editor:textEditor});
		dataBuilder.addProperty({id:'isTakeback',name:'允许取回',value:isTakeback,height:35,required:true,group:'权限配置',editor:booleanEditor});
		dataBuilder.addProperty({id:'isDelegate',name:'允许委派',value:isDelegate,height:35,required:true,group:'权限配置',editor:booleanEditor});
		dataBuilder.addProperty({id:'isUpload',name:'上传附件',value:isUpload,height:35,required:true,group:'权限配置',editor:booleanEditor});
		dataBuilder.addProperty({id:'isAdvice',name:'必填意见',value:isAdvice,height:35,required:true,group:'权限配置',editor:booleanEditor});
		dataBuilder.addProperty({id:'isSendback',name:'允许退回',value:isSendback,height:35,required:true,group:'权限配置',editor:booleanEditor});
		dataBuilder.addProperty({id:'sendbackTarget',name:'退回对象',value:sendbackTarget,height:35,required:true,group:'权限配置',editor:textEditor});
		dataBuilder.addProperty({id:'isCheck',name:'允许传阅',value:isCheck,height:35,required:true,group:'权限配置',editor:booleanEditor});
		dataBuilder.addProperty({id:'checkTarget',name:'传阅对象',value:checkTarget,height:35,required:true,group:'权限配置',editor:textEditor});
		$('#propertygrid').propertygrid('loadData',dataBuilder.data);
	}else if('start'==nodeType){
		panel.panel('setTitle','开始节点配置');
		dataBuilder.addProperty({id:'label',name:'名称',value:label,height:35,required:true,group:'基本信息',editor:textEditor});
		$('#propertygrid').propertygrid('loadData',dataBuilder.data);
	}else if('end'==nodeType){
		panel.panel('setTitle','结束节点配置');
		dataBuilder.addProperty({id:'label',name:'名称',value:label,height:35,required:true,group:'基本信息',editor:textEditor});
		$('#propertygrid').propertygrid('loadData',dataBuilder.data);
	}else if('error'==nodeType){
		panel.panel('setTitle','异常结束节点配置');
		dataBuilder.addProperty({id:'label',name:'名称',value:label,height:35,required:true,group:'基本信息',editor:textEditor});
		$('#propertygrid').propertygrid('loadData',dataBuilder.data);
	}
}

$.extend($.fn.datagrid.defaults.editors, {
	text:{
    	init: function(container, options){
            var input = $('<input type="text" class="datagrid-editable-input">').appendTo(container);
            return input;
        },
        getValue: function(target){
            return $(target).val();
        },
        setValue: function(target, value){
            $(target).val(value);
        },
        resize: function(target, width){
            var input = $(target);
            if ($.boxModel == true){
                input.width(width - (input.outerWidth() - input.width()));
            } else {
            	input.width(width-17);
            }
        }
    }
});

$.extend($.fn.datagrid.defaults.editors, {
	textarea:{
    	init: function(container, options){
            var input = $('<textarea class="datagrid-editable-input"></textarea>').appendTo(container);
            return input;
        },
        getValue: function(target){
            return $(target).val();
        },
        setValue: function(target, value){
            $(target).val(value);
        },
        resize: function(target, width){
            var input = $(target);
            if ($.boxModel == true){
                input.width(width - (input.outerWidth() - input.width()));
            } else {
            	input.width(width-17);
            }
        }
    }
});

$.extend($.fn.datagrid.defaults.editors, {
	combobox:{
    	init: function(container, options){
            var input = $('<input type=\"text\">').appendTo(container);
            input.combobox(options||{});
            return input;
        },
        destroy:function(target){
			$(target).combobox("destroy");
		},
        getValue: function(target){
        	return $(target).combobox("getValues");
        },
        setValue: function(target, value){
        	if(typeof(value)=='string'){
        		if(value.charAt(0)=='[' && value.charAt(value.length-1)==']'){
        			value = eval(value);
        		}else{
        			value = eval('['+value+']');
        		}
        	}
        	$(target).combobox("setValues",value);
        },
        resize: function(target, width){
            var input = $(target);
            if ($.boxModel == true){
                input.width(width - (input.outerWidth() - input.width()));
            } else {
            	input.width(width-17);
            }
        }
    }
});

function DataBuilder(){this.data = {total:0,rows:[]};}
DataBuilder.prototype.addProperty = function(column){
	var style='',title='';
	if(column.required){
		column.name = '<span class="icon-required">*</span>'+column.name;
	}
	if(column.height){
		style = ' style="height:'+column.height+';line-height:'+column.height+'px;"';
	}
	if(column.title){
		title = ' title="'+column.title+'"';
	}
	column.name = '<div '+style+title+'>'+column.name+'</div>';
	this.data.rows.push(column);
	this.data.total++;
	return this;
};