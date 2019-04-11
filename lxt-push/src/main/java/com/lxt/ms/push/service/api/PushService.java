package com.lxt.ms.push.service.api;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;

import java.util.HashMap;

public interface PushService {

    public abstract Packages sendMessage(String messageJson) throws APIException;
}
