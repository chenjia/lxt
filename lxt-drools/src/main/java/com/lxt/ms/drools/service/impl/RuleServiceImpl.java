package com.lxt.ms.drools.service.impl;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.drools.mapper.RuleMapper;
import com.lxt.ms.drools.model.Rule;
import com.lxt.ms.drools.model.RuleExample;
import com.lxt.ms.drools.service.api.RuleService;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {
    public static KieContainer kieContainer;

    @Autowired
    private RuleMapper ruleMapper;

    @Override
    public KieContainer getKieContainer() throws APIException {
        if(kieContainer == null){
            this.reload();
        }
        return kieContainer;
    }

    @Override
    public List<Rule> reload(){
        List<Rule> ruleList = ruleMapper.selectByExampleWithBLOBs(new RuleExample());
        KieContainer kieContainer = loadContainerFromString(ruleList);
        this.kieContainer = kieContainer;
        return ruleList;
    }

    private  KieContainer loadContainerFromString(List<Rule> rules) {
        long startTime = System.currentTimeMillis();
        KieServices ks = KieServices.Factory.get();
        KieRepository kr = ks.getRepository();
        KieFileSystem kfs = ks.newKieFileSystem();

        for (Rule rule:rules) {
            String  drl=rule.getContent();
            kfs.write("src/main/resources/rules" + drl.hashCode() + ".drl", drl);
        }

        KieBuilder kb = ks.newKieBuilder(kfs);

        kb.buildAll();
        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Build Errors:\n" + kb.getResults().toString());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time to build rules : " + (endTime - startTime)  + " ms" );
        startTime = System.currentTimeMillis();
        KieContainer kContainer = ks.newKieContainer(kr.getDefaultReleaseId());
        endTime = System.currentTimeMillis();
        System.out.println("Time to load container: " + (endTime - startTime)  + " ms" );
        return kContainer;
    }

    @Override
    public Packages list() throws APIException {
        Packages pkg = new Packages();

        List<Rule> ruleList = ruleMapper.selectByExampleWithBLOBs(new RuleExample());
        pkg.getBody().setData(ruleList);

        return pkg;
    }
}
