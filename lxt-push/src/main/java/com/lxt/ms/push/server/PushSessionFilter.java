package com.lxt.ms.push.server;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

import java.util.Map;

public class PushSessionFilter implements ScriptSessionFilter {
    private Map message = null;

    public PushSessionFilter(Map message) {
        this.message = message;
    }

    @Override
    public boolean match(ScriptSession session) {
        String userId = (String) session.getAttribute("userId");
        String receiveId = (String) message.get("receiveId");

        if(receiveId != null && receiveId.equalsIgnoreCase(userId)){
            return true;
        }else{
            return false;
        }
    }
}
