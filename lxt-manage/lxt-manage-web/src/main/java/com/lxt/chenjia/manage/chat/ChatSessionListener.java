package com.lxt.chenjia.manage.chat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;

public class ChatSessionListener implements ScriptSessionListener {
	@Override
	public void sessionCreated(ScriptSessionEvent event) {
//		ScriptSession scriptSession = event.getSession();
//		HttpSession httpSession = WebContextFactory.get().getSession();
//		String userId = (String) httpSession.getAttribute("userId");
//		System.out.println("【聊天用户】"+userId+" "+scriptSession.getId());
//		if (userId == null) {
//			scriptSession.invalidate();
//			httpSession.invalidate();
//		}
//		scriptSession.setAttribute("userId", userId);
	}
	
	@Override
	public void sessionDestroyed(ScriptSessionEvent event) {
		ScriptSession session = event.getSession();
		System.out.println("【销毁聊天用户】"+session.getAttribute("userId")+" "+session.getId());
	}
}
