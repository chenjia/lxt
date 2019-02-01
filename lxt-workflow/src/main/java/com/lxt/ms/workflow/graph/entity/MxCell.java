package com.lxt.ms.workflow.graph.entity;

public class MxCell {
	private String style;
	private String vertex;
	private String parent;
	private String edge;
	private String source;
	private String target;
	private MxGeometry mxGeometry = new MxGeometry();

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getVertex() {
		return vertex;
	}

	public void setVertex(String vertex) {
		this.vertex = vertex;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getEdge() {
		return edge;
	}

	public void setEdge(String edge) {
		this.edge = edge;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public MxGeometry getMxGeometry() {
		return mxGeometry;
	}

	public void setMxGeometry(MxGeometry mxGeometry) {
		this.mxGeometry = mxGeometry;
	}

}
