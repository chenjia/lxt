package com.lxt.ms.chat.server;

import com.lxt.ms.chat.model.ChatRecord;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

public class ChatSessionFilter implements ScriptSessionFilter{
	private ChatRecord record = null;
	
	public ChatSessionFilter(ChatRecord record) {
		this.record = record;
	}
	
	@Override
	public boolean match(ScriptSession session) {
		String userId = (String) session.getAttribute("userId");
		if(record.getReceiveId().equals(userId)){
			return true;
		}else{
			return false;
		}
	}
}
