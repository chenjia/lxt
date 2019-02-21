package com.lxt.ms.manage.service.api;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;

public interface DepartmentService {

    public abstract Packages list(PageData pageData) throws APIException;
}
