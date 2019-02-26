package com.lxt.ms.workflow.service.api;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.workflow.model.Dynamic;

public interface DynamicService {

    public Packages save(Dynamic dynamic) throws APIException;

    public Packages delete(String pageId) throws APIException;

    public Packages list() throws APIException;

}
