package com.lxt.ms.workflow.graph.entity;

import java.util.ArrayList;
import java.util.List;

public class Root {
	private Workflow Workflow = new Workflow();
	private Layer Layer = new Layer();
	private List<Vertex> vertexs = new ArrayList<Vertex>();
	private List<Edge> edges = new ArrayList<Edge>();

	public Root() {
		Workflow.setId("0");
		
		Layer.setId("1");
		Layer.setLabel("Default Layer");
		MxCell layerCell = new MxCell();
		layerCell.setParent("0");
		Layer.setMxCell(layerCell);
	}
	
	public Workflow getWorkflow() {
		return Workflow;
	}

	public void setWorkflow(Workflow workflow) {
		Workflow = workflow;
	}

	public Layer getLayer() {
		return Layer;
	}

	public void setLayer(Layer layer) {
		this.Layer = layer;
	}

	public List<Vertex> getVertexs() {
		return vertexs;
	}
	
	public void setVertexs(List<Vertex> vertexs) {
		this.vertexs = vertexs;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

}
