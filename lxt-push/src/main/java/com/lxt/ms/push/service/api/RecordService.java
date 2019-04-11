package com.lxt.ms.push.service.api;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;

import java.util.HashMap;

public interface RecordService {

    public abstract Packages list(HashMap<String, Object> example, PageData pageData) throws APIException;
}
