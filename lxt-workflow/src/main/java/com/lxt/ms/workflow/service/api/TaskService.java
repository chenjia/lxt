package com.lxt.ms.workflow.service.api;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;

public interface TaskService {
    public Packages submit(String $userId, String taskId) throws APIException;

    public Packages rollback(String taskId, String nodeId, String target) throws APIException;

    public Packages historyList(String $userId, PageData page) throws APIException;

    public Packages historyActivity(String instanceId) throws APIException;

    public Packages transition(String taskId) throws APIException;

    public Packages details(String taskId) throws APIException;

    public Packages list(String $userId, PageData page) throws APIException;
}
