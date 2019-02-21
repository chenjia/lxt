package com.lxt.ms.workflow.service.api;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.workflow.model.Rule;
import com.lxt.ms.workflow.model.RuleExample;

public interface RuleService {

    public Packages save(Rule rule) throws APIException;

    public Packages delete(String ruleId) throws APIException;

    public Packages list(RuleExample example, PageData pageData) throws APIException;

}
