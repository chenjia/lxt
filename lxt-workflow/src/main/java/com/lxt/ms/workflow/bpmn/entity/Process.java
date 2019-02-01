package com.lxt.ms.workflow.bpmn.entity;

import java.util.List;

public class Process {
	private String id;
	private String name;
	private List<AbstractActivity> activities;
	private List<AbstractTransition> transitions;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AbstractActivity> getActivities() {
		return activities;
	}

	public void setActivities(List<AbstractActivity> activities) {
		this.activities = activities;
	}

	public List<AbstractTransition> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<AbstractTransition> transitions) {
		this.transitions = transitions;
	}

}
