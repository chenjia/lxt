<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
mxBasePath = '../../mxgraph';
mxLoadResources = false;
</script>
<script type="text/javascript" src="../../js/easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../../mxgraph/js/mxClient.js"></script>
</head>
<body>
	<div id="graph" style="width:98%;height:98%;position:absolute"></div>
	<div id="means" style="z-index:1;position:absolute;right:0px;top:0px;overflow:hidden;width:180px;height:135px;background:white;border:1px solid #91A1AE;"></div>
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
	
	<script type="text/javascript">
		String.prototype.getParam=function(name){
			var reg =new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			var r =this.substr(this.indexOf("\?")+1).match(reg);
			if(r!=null)return unescape(r[2]);return null;
		};
		
		$(function(){
			var height = document.documentElement.clientHeight;
			var editor = null,graph = null;
			var node = mxUtils.load('../../mxgraph/config/workflow-view.xml').getDocumentElement();
			editor = new mxEditor(node);
			graph = editor.graph;
			
			var workflowId = unescape(window.location.href.getParam('workflowId'));
			var executionId = unescape(window.location.href.getParam('executionId'));
			if(workflowId)
			$.ajax({
				async:false,
				dataType:'xml',
				url:'workflow!getGraphXml.action',
				data:{'param.workflowId':workflowId},
				success:function(data){
					editor.readGraphModel(data.documentElement);
					var workflowElement = data.documentElement.childNodes[0].childNodes[0];
					var workflowName = workflowElement.getAttribute('label');
					var str = '流程名称：'+workflowName;
					$('#means').html(str);
				},
				error:function(){
					alert('流程数据加载失败！');
				}
			});
			
			
			$.ajax({
				async:false,
				dataType:'json',
				type:'POST',
				url:'workflow!queryHistoryStep.action',
				data:{'param.executionId':executionId},
				success:function(json){
					var cells = graph.model.cells;
					var current = json.data.current;
					var history = json.data.history;
					graph.model.beginUpdate();
					for(var c in cells){
						var cell = cells[c];
						if(cell.isEdge() && cell.source.getAttribute('nodeType')=='start'){
							graph.model.setStyle(cell, 'historyEdge');
							continue;
						}
						for(var i=0;i<history.length;i++){
							if(cell.isEdge()){
								if(history[i].transition==cell.getAttribute("label")){
									if(cell.source.getAttribute('label')==history[i].activity){
										graph.model.setStyle(cell, 'historyEdge');
									}
								}
							}else{
								if(history[i].activity==cell.getAttribute('label')){
									cell.setAttribute('eid',history[i].eid);
									cell.setAttribute('assigner',history[i].assigner);
									cell.setAttribute('createTime',history[i].createTime);
									cell.setAttribute('endTime',history[i].endTime);
									cell.setAttribute('userId',history[i].userId);
									graph.model.setStyle(cell, 'historyVertex');
									var incomingEdges = graph.model.getIncomingEdges(cell);
									if(incomingEdges.length==1){
										graph.model.setStyle(incomingEdges[0], 'historyEdge');
									}
								}
							}
						}
						for(var i=0;i<current.length;i++){
							if(current[i].activity==cell.getAttribute('label')){
								graph.model.setStyle(cell, 'currentVertex');
								cell.setAttribute('current',true);
								cell.setAttribute('eid',current[i].eid);
								cell.setAttribute('assigner',current[i].assigner);
								cell.setAttribute('candidate',current[i].candidate);
							}
						}
					}
					graph.model.endUpdate();
				}
			});
			
			var outline = document.getElementById('outline');
			new mxOutline(graph, outline);
			graph.setCellsLocked(true);
			$('#splash').fadeOut('fast');
		});
	</script>
</body>
</html>
