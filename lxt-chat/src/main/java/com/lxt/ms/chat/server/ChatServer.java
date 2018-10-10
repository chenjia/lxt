package com.lxt.ms.chat.server;

import com.lxt.ms.chat.mapper.ChatRecordMapper;
import com.lxt.ms.chat.vo.ChatRecordVO;
import com.lxt.ms.common.utils.JSONUtils;
import com.lxt.ms.common.utils.UUIDUtils;
import org.directwebremoting.*;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.extend.ScriptSessionManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Date;

@RemoteProxy
public class ChatServer {
	@Autowired
	private static ChatRecordMapper chatRecordMapper;

	private static ScriptSessionManager manager = null;
	
	private static void initChatServer(){
		Container container = ServerContextFactory.get().getContainer();
		ScriptSessionManager manager = container.getBean(ScriptSessionManager.class);
		manager.addScriptSessionListener(new ChatSessionListener());
	}
	
	@RemoteMethod
	public static void binding(String userId) {
		ScriptSession scriptSession = WebContextFactory.get().getScriptSession();
        scriptSession.setAttribute("userId", userId);
	}
	
	@RemoteMethod
	public static void sendMessage(String messageJson) throws Exception {
		if(manager==null){
			initChatServer();
		}
		ChatRecordVO record = JSONUtils.json2Obj(messageJson, ChatRecordVO.class);
		record.setRecordId(UUIDUtils.UUID());
		record.setInsertTime(new Date());
		record.setMsgType(0);
//		chatRecordMapper.insert(record);
		final String json = JSONUtils.obj2Json(record);
		
		ScriptSessionFilter filter = new ChatSessionFilter(record);
		Browser.withCurrentPageFiltered(filter,
			new Runnable() {
				public void run() {
					Collection<ScriptSession> sessions = Browser.getTargetSessions();
					System.out.println("【聊天会话数】" + sessions.size());
					System.out.println(json);
					
					ScriptSessions.addFunctionCall("receiveMessage", json);
				}
			}
		);
	}
}
