package com.lxt.ms.chat.server;

import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.impl.DefaultScriptSessionManager;

public class ChatSessionManager extends DefaultScriptSessionManager {
	
	public ChatSessionManager() {
		System.out.println("ChatSessionManager");
	}
	
	public void addScriptSessionListener(ScriptSessionListener listener) {
		super.addScriptSessionListener(listener);
	}
}
