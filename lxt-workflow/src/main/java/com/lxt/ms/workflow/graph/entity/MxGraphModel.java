package com.lxt.ms.workflow.graph.entity;

import java.util.List;
import java.util.Map;

public class MxGraphModel {
	private Root root;
	
	public Root getRoot() {
		return root;
	}
	
	public void setRoot(Root root) {
		this.root = root;
	}
	
	public void setDescriptions(Map<String, String> map){
		List<Vertex> vertexList = root.getVertexs();
		for(Vertex vertex : vertexList){
			String nodeId = map.get(vertex.getId());
			vertex.setNodeId(nodeId);
		}
	}
}
