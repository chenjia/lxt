package com.lxt.ms.workflow.graph.entity;

public class MxGeometry {
	private Float x;
	private Float y;
	private Float width;
	private Float height;
	private String as = "geometry";
	private Integer relative;
	private Array Array;
	private MxPoint mxPoint;

	public Float getX() {
		return x;
	}

	public void setX(Float x) {
		this.x = x;
	}

	public Float getY() {
		return y;
	}

	public void setY(Float y) {
		this.y = y;
	}

	public Float getWidth() {
		return width;
	}

	public void setWidth(Float width) {
		this.width = width;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public String getAs() {
		return as;
	}

	public void setAs(String as) {
		this.as = as;
	}

	public Integer getRelative() {
		return relative;
	}

	public void setRelative(Integer relative) {
		this.relative = relative;
	}

	public Array getArray() {
		return Array;
	}
	
	public void setArray(Array array) {
		Array = array;
	}
	
	public MxPoint getMxPoint() {
		return mxPoint;
	}
	
	public void setMxPoint(MxPoint mxPoint) {
		this.mxPoint = mxPoint;
	}
}
