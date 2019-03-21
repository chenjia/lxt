package com.lxt.ms.workflow.service.api;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.workflow.model.Page;

public interface PageService {

    public Packages save(Page page) throws APIException;

    public Packages delete(String pageId) throws APIException;

    public Packages list() throws APIException;

}
