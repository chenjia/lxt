package com.lxt.ms.workflow.service.api;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.workflow.model.Regex;
import com.lxt.ms.workflow.model.RegexExample;

public interface RegexService {

    public Packages save(Regex regex, String $userId) throws APIException;

    public Packages delete(String id) throws APIException;

    public Packages list(RegexExample example) throws APIException;

    public Packages details(String id) throws APIException;

}
