package com.lxt.ms.workflow.graph.entity;

public class Edge {
	private String label;
	private String description;
	private String id;
	private MxCell mxCell = new MxCell();

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MxCell getMxCell() {
		return mxCell;
	}

	public void setMxCell(MxCell mxCell) {
		this.mxCell = mxCell;
	}

}
