package com.lxt.ms.manage.service.api;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.manage.model.UserLogExample;

import java.util.List;
import java.util.Map;

public interface LogService {

    public abstract Packages save(String $userId, List<Map> logs) throws APIException;

    public abstract Packages list(UserLogExample example, PageData pageData) throws APIException;
}
