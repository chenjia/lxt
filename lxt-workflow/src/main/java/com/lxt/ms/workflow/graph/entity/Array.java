package com.lxt.ms.workflow.graph.entity;

import java.util.ArrayList;
import java.util.List;

public class Array {
	private String as;
	List<MxPoint> mxPoints = new ArrayList<MxPoint>();
	
	public String getAs() {
		return as;
	}
	
	public void setAs(String as) {
		this.as = as;
	}
	
	public List<MxPoint> getMxPoints() {
		return mxPoints;
	}
	
	public void setMxPoints(List<MxPoint> mxPoints) {
		this.mxPoints = mxPoints;
	}
}
