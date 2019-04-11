package com.lxt.ms.push.server;

import com.lxt.ms.common.utils.JSONUtils;
import com.lxt.ms.common.utils.SpringUtils;
import com.lxt.ms.common.utils.UUIDUtils;
import com.lxt.ms.push.mapper.ChatRecordMapper;
import com.lxt.ms.push.vo.ChatRecordVO;
import org.directwebremoting.*;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.extend.ScriptSessionManager;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Map;

@RemoteProxy
@Controller(value="pushServer")
public class PushServer {
    private static ChatRecordMapper chatRecordMapper;

    private static ScriptSessionManager manager = null;

    private static void initServer() {
        Container container = ServerContextFactory.get().getContainer();
        ScriptSessionManager manager = container.getBean(ScriptSessionManager.class);
        manager.addScriptSessionListener(new PushSessionListener());
    }

    @RemoteMethod
    public static void binding(String userId) {
        ScriptSession scriptSession = WebContextFactory.get().getScriptSession();
        scriptSession.setAttribute("userId", userId);
    }

    @RemoteMethod
    public static void sendMessage(String messageJson) throws Exception {
        System.out.println(messageJson);
        if (manager == null) {
            initServer();
        }

        Map message = JSONUtils.json2Map(messageJson);
        String type = (String) message.get("type");
        if("chat".equalsIgnoreCase(type)){
            ChatRecordVO record = JSONUtils.json2Obj(messageJson, ChatRecordVO.class);
            record.setType("chat");
            record.setRecordId(UUIDUtils.UUID());
            record.setInsertTime(new Date());
            record.setMsgType(0);
            if (chatRecordMapper == null) {
                chatRecordMapper = (ChatRecordMapper) SpringUtils.getApplicationContext().getBean("chatRecordMapper");
            }
            chatRecordMapper.insert(record);
            messageJson = JSONUtils.obj2Json(record);
        }

        final String json = messageJson;
        ScriptSessionFilter filter = new PushSessionFilter(message);
        Browser.withAllSessionsFiltered(filter, new Runnable() {
            public void run() {
                ScriptSessions.addFunctionCall("receiveMessage", json);
            }
        });
    }
}
