var checkLogin = true;
$.ajaxSetup({
	global:false,
	type:'POST',
	dataType:'json',
	error:function(data){
		if(checkLogin){
			checkLogin = false;
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
		}
	}
});

var editor,graph,curCell,workflowId;
$(function(){
	var node = mxUtils.load('../../mxgraph/config/workflow-editor.xml').getDocumentElement();
	editor = new mxEditor(node);
	graph = editor.graph;

	initGraph();
	initToolbar();
	
	if(!workflowId){
		showProperties(editor.graph.model.getCell(0));
	}
	
	$('#splash').fadeOut('fast');
	$('#graph').css({background:"url('../../mxgraph/images/grid.gif') repeat scroll 0 0 transparent"});
});

function initGraph(){
	graph.setConnectable(true);
	graph.setAutoSizeCells(true);
	graph.graphHandler.guidesEnabled = true;
	
	var oldGetPreferredSizeForCell = graph.getPreferredSizeForCell;
	graph.getPreferredSizeForCell = function(cell){
		var result = oldGetPreferredSizeForCell.apply(this, arguments);
		if (result != null){
			var label = cell.getAttribute('label');
			function cLength(str){
				var array = [0,0];
				var len = 0;
				for ( var i = 0; i < str.length; i++) {
					var a = str.charAt(i);
					if (a.match(/[^\x00-\xff]/ig) != null) {
						array[0] += 1;
					} else {
						array[1] += 1;
					}
				}
				return array;
			} 
			var lengthArray = cLength(label);
			var extWidth = lengthArray[0]*15.5+lengthArray[1]*8.2;
			result.width = Math.max(96, 36+extWidth);
		}
		return result;
	};
	
	graph.addListener(mxEvent.LABEL_CHANGED, function(sender, evt){
		var cell = evt.getProperty('cell');
		showProperties(cell);
		var isVertex = graph.model.isVertex(cell);
		if(isVertex){
			if(!cell.getAttribute('isSymbol')){
				var edges = cell.edges;
				for(var i in edges){
					var edge = edges[i];
					if(edge.target == cell){
						edge.setAttribute('label','to '+cell.getAttribute('label'));
					}
				}
				var model = graph.getModel();
				var size = graph.getPreferredSizeForCell(cell);
				var geometry = model.getGeometry(cell);
				geometry.width = size.width;
				geometry.height = size.height;
			}
		}
	});
	
	graph.addListener(mxEvent.CELL_CONNECTED, function(sender, evt){
		var edge = evt.getProperty('edge');
		var source = evt.getProperty('source');
		var terminal = evt.getProperty('terminal');
		if(!source && terminal){
			edge.setAttribute('label','to '+terminal.getAttribute('label'));
		}
	});
	graph.selectionModel.addListener(mxEvent.CHANGE, function(sender, evt){
		var added = evt.getProperty('added');
		var removed = evt.getProperty('removed');
		var propertyPanel = $('#eastPanel');
		var model = graph.getModel();
    	model.beginUpdate();
    	try{
    		var workflow = editor.graph.model.getCell(0);
    		if($('#wc_workflowKey')[0]){
    			workflowPropertyUpdate(workflow);
    		}
			if(added.length==1){
				eval(added[0].getAttribute('nodeType')+'PropertyUpdate(added[0])');
			}
			if(removed && removed.length==1){
				propertyPanel.panel('options').onLoad = function(){showProperties(removed[0]);};
				var nodeType = removed[0].getAttribute('nodeType');
				if(nodeType){
					propertyPanel.panel('setTitle',removed[0].getAttribute('propertyTitle'));
					propertyPanel.panel('refresh','../../mxgraph/pages/'+nodeType+'.jsp');
				}
			}else{
				propertyPanel.panel('options').onLoad = function(){showProperties(workflow);};
				propertyPanel.panel('setTitle',workflow.getAttribute('propertyTitle'));
				propertyPanel.panel('refresh','../../mxgraph/pages/workflow.jsp');
				curCell = workflow;
			}
			graph.refresh();
    	}catch(e){
    		alert(e);
    	}finally{
    		model.endUpdate();
    	}
	});
	
	new mxOutline(graph, $('#outline')[0]);
	
	workflowId = $.getUrlVar('workflowId');
	if(workflowId){
		$.ajax({
			dataType:'xml',
			url:'workflow!getGraphXml.action',
			data:{'param.workflowId':workflowId},
			success:function(data){
				editor.readGraphModel(data.documentElement);
				showProperties(editor.graph.model.getCell(0));
			},
			error:function(){
				alert('流程数据加载失败！');
			}
		});
	}
}

function initToolbar(){
	var start = editor.getTemplate('start');
	var end = editor.getTemplate('end');
	var error = editor.getTemplate('error');
	var task = editor.getTemplate('task');
	
	var nodes = $('.graphElement');
	
	var mxCells = [
 		new mxCell(start.value, start.geometry, start.style),
 		new mxCell(task.value, task.geometry, task.style),
 		new mxCell(end.value, end.geometry, end.style),
 		new mxCell(error.value, error.geometry, error.style)
 	];
	
	var installDrag = function(index){
		var cells = [mxCells[index]];
		cells[0].vertex = true;
		
		var funct = function(graph, evt, target, x, y){
	 		cells = graph.getImportableCells(cells);
	 		if (cells.length > 0){
	 			var validDropTarget = (target != null)?graph.isValidDropTarget(target, cells, evt) : false;
	 			var select = null;
	 			if (target != null && !validDropTarget && graph.getModel().getChildCount(target) == 0 && graph.getModel().isVertex(target) == cells[0].vertex){
	 				graph.getModel().setStyle(target, style);
	 				select = [target];
	 			}else{
	 				if (target != null && !validDropTarget){
	 					target = null;
	 				}
	 				if (graph.isSplitEnabled() && graph.isSplitTarget(target, cells, evt)){
	 					graph.splitEdge(target, cells, null, x, y);
	 					select = cells;
	 				}else{
	 					cells = graph.getImportableCells(cells);
	 					if (cells.length > 0){
	 						select = graph.importCells(cells, x, y, target);
	 					}
	 				}
	 			}
	 			
	 			if (select != null && select.length > 0){
	 				graph.scrollCellToVisible(select[0]);
	 				graph.setSelectionCells(select);
	 			}
	 		}
	 	};
	 	var dragPreview = $('<div style="cursor:move;border:dashed red 2px;width:48px;height:48px;"><img src="'+nodes[index].src+'" width="48" height="48"/><div class="drop-no"></div></div>');
 		var ds = mxUtils.makeDraggable(nodes[index], graph, funct, dragPreview[0], -5, -5,graph.autoscroll, true,true);
 		ds.dragEnter = function(){
 			$(ds.dragElement).css({border:'dashed green 2px'}).find('.drop-no').removeClass('drop-no').addClass('drop-yes');
 		};
 		ds.dragExit = function(){
 			$(ds.dragElement).css({border:'dashed red 2px'}).find('.drop-yes').removeClass('drop-yes').addClass('drop-no');
 		};
	};
	
	$.each(mxCells,function(index){
		installDrag(index);
	});
}

function showProperties(cell){
	var nodeType = cell.getAttribute('nodeType');
	eval(nodeType+'PropertyInit(cell)');
	curCell = cell;
}

function setEdgeStype(style){
	var model = graph.getModel();
	model.beginUpdate();
	try{
		if(curCell.isEdge()){
			curCell.setStyle(style);
			graph.refresh();
		}
	}catch(e){
		alert(e);
	}finally{
		model.endUpdate();
	}
}

function saveWorkflow(){
	eval(curCell.getAttribute('nodeType')+'PropertyUpdate(curCell)');
	var enc = new mxCodec();
    var node = enc.encode(editor.graph.model);
    var graphXml = mxUtils.getXml(node, editor.linefeed);
    alert(graphXml);
    $.ajax({
    	url:'workflow!save.action',
    	data:{'param.graphXml':graphXml},
    	success:function(json){
    		var status = json.status;
    		if(status==0){
    			var workflow = editor.graph.model.getCell(0);
				var cells = editor.graph.model.cells;
				workflow.setAttribute("workflowId",json.data.workflowId);
				for(var i in cells){
					if(graph.model.isVertex(cells[i])){
						var nodeId = json.data[cells[i].id];
						cells[i].setAttribute("nodeId",nodeId);
					}
				}
    			MessageUtils.success("保存流程", "流程保存成功！");
    		}else if(status==2){
    			alert('对不起，您没有权限执行此操作，请联系管理员！');
    		}else if(status==3){
    			alert('流程保存失败！失败原因：'+json.msg);
    		}
    	}
    });
}