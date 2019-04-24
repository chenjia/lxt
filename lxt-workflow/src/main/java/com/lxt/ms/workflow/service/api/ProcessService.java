package com.lxt.ms.workflow.service.api;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.workflow.model.ProcessExample;

import java.util.Map;

public interface ProcessService {

    public Packages save(String graphXml) throws APIException;

    public Packages publish(String pid, int status) throws APIException;

    public Packages delete(String pid) throws APIException;

    public Packages deleteAll() throws APIException;

    public Packages start(String $userId, String pid, Map<String, Object> variables) throws APIException;

    public Packages details(String pid) throws APIException;

    public Packages graph(String taskId, String $userId) throws APIException;

    public Packages list(ProcessExample example, PageData page) throws APIException;

    public Packages logs(String instanceId) throws APIException;

    public Packages history(String processInstanceId) throws APIException;
}
