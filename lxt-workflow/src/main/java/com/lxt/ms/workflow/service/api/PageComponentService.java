package com.lxt.ms.workflow.service.api;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.workflow.model.Page;
import com.lxt.ms.workflow.model.PageComponent;

public interface PageComponentService {

    public Packages save(PageComponent pageComponent) throws APIException;

    public Packages delete(String componentId) throws APIException;

    public Packages list(String pageId) throws APIException;

}
