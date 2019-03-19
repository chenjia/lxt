package com.lxt.ms.drools.service.api;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.drools.model.Rule;
import org.drools.core.command.GetKieContainerCommand;
import org.kie.api.runtime.KieContainer;

import java.util.List;

public interface RuleService {

    public abstract List<Rule> reload() throws APIException;

    public abstract KieContainer getKieContainer() throws APIException;

    public abstract Packages list() throws APIException;
}
