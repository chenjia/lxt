package com.lxt.ms.manage.service.api;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.exception.APIException;

public interface OrganizationService {

    public abstract Packages children(String parentId) throws APIException;
}
