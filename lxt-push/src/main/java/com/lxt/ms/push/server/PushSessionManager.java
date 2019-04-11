package com.lxt.ms.push.server;

import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.impl.DefaultScriptSessionManager;

public class PushSessionManager extends DefaultScriptSessionManager {

	public PushSessionManager() {
		System.out.println("PushSessionManager");
	}
	
	public void addScriptSessionListener(ScriptSessionListener listener) {
		super.addScriptSessionListener(listener);
	}
}
