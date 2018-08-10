package com.lxt.chenjia.manage.chat;

import java.util.Collection;
import java.util.Date;

import org.directwebremoting.Browser;
import org.directwebremoting.Container;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.extend.ScriptSessionManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.lxt.chenjia.common.utils.JSONUtils;
import com.lxt.chenjia.common.utils.UUIDUtils;
import com.lxt.chenjia.manage.mapper.ChatRecordMapper;
import com.lxt.chenjia.manage.vo.ChatRecordVO;

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
	
	public static void main(String[] args) {
		ChatRecordVO vo = new ChatRecordVO();
		vo.setContent("testtesttest");
		vo.setInsertTime(new Date());
		vo.setInsertTimeMillis(System.currentTimeMillis());
		vo.setMsgType(0);
		vo.setReceiveId("admin");
		
		System.out.println(JSONUtils.obj2Json(vo));
		
	}
}
