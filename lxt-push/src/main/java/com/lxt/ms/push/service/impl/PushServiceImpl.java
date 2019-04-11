package com.lxt.ms.push.service.impl;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.common.utils.JSONUtils;
import com.lxt.ms.push.server.PushServer;
import com.lxt.ms.push.service.api.PushService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("serverPushService")
public class PushServiceImpl implements PushService {

    @Override
    public Packages sendMessage(String messageJson) throws APIException {
        Packages pkg = new Packages();

        try {
            PushServer.sendMessage(messageJson);
            Map message = JSONUtils.json2Map(messageJson);
            pkg.getBody().setData(message.get("msg"));
        } catch(Exception e) {
            e.printStackTrace();
            throw new APIException(e);
        }

        return pkg;
    }
}
