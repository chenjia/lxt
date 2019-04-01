package com.lxt.ms.workflow.service.api;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.workflow.model.Rule;
import com.lxt.ms.workflow.model.RuleExample;

import java.util.List;

public interface RuleService {
    public Packages save(Rule rule, String $userId) throws APIException;

    public Packages delete(String ruleId) throws APIException;

    public Packages list(RuleExample example) throws APIException;

    public Packages details(String ruleId) throws APIException;

    public Packages reload() throws APIException;

    public Packages test(Rule rule, Object... params) throws APIException;

    public Packages testAll(Object... params) throws APIException;
}
