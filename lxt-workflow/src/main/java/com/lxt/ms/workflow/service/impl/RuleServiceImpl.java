package com.lxt.ms.workflow.service.impl;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.common.utils.UUIDUtils;
import com.lxt.ms.workflow.mapper.RuleMapper;
import com.lxt.ms.workflow.model.Rule;
import com.lxt.ms.workflow.model.RuleExample;
import com.lxt.ms.workflow.model.User;
import com.lxt.ms.workflow.service.api.RuleService;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ruleService")
public class RuleServiceImpl implements RuleService {
    public static KieContainer kieContainer;

    @Autowired
    private RuleMapper ruleMapper;

    @Override
    public Packages save(Rule rule, String $userId) throws APIException {
        Packages pkg = new Packages();

        Date date = new Date();
        rule.setUpdateTime(date);
        try {
            if(rule.getId() == null){
                rule.setId(UUIDUtils.UUID());
                rule.setUserId($userId);
                rule.setInsertTime(date);
                ruleMapper.insert(rule);
            }else{
                ruleMapper.updateByPrimaryKeySelective(rule);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("规则保存失败！");
        }

        return pkg;
    }

    @Override
    public Packages delete(String id) throws APIException {
        Packages pkg = new Packages();

        try {
            ruleMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("规则删除失败！");
        }

        return pkg;
    }

    @Override
    public Packages list(RuleExample example) throws APIException {
        Packages pkg = new Packages();

        try {
            List<Rule> list = ruleMapper.selectByExample(new RuleExample());
            pkg.getBody().setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("规则查询失败！");
        }

        return pkg;
    }

    @Override
    public Packages details(String id) throws APIException {
        Packages pkg = new Packages();

        Rule rule = ruleMapper.selectByPrimaryKey(id);
        pkg.getBody().setData(rule);

        return pkg;
    }

    @Override
    public Packages reload() throws APIException {
        Packages pkg = new Packages();

        List<Rule> ruleList = ruleMapper.selectByExampleWithBLOBs(new RuleExample());
        KieContainer kieContainer = loadContainerFromString(ruleList);
        this.kieContainer = kieContainer;
        pkg.getBody().setData(ruleList);

        return pkg;
    }

    @Override
    public Packages test(Rule rule, Object... params) throws APIException {
        Packages pkg = new Packages();

        KieServices ks = KieServices.Factory.get();
        KieRepository kr = ks.getRepository();
        KieFileSystem kfs = ks.newKieFileSystem();

        String  drl=rule.getContent();
        kfs.write("src/main/resources/rules" + drl.hashCode() + ".drl", drl);

        KieBuilder kb = ks.newKieBuilder(kfs);

        kb.buildAll();
        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("规则内容错误，编译失败！");
        }
        KieContainer kContainer = ks.newKieContainer(kr.getDefaultReleaseId());

        KieSession kieSession = kContainer.newKieSession();
        for (Object param : params) {
            kieSession.insert(param);
        }

        int ruleFiredCount = kieSession.fireAllRules();
        kieSession.destroy();
        System.out.println("触发了" + ruleFiredCount + "条规则");
        Map<String, Object> map = new HashMap<>();
        map.put("count", ruleFiredCount);
        map.put("data", params);
        pkg.getBody().setData(map);

        return pkg;
    }

    @Override
    public Packages testAll(Object... params) throws APIException {
        Packages pkg = new Packages();

        if(this.kieContainer == null || true){
            this.reload();
        }

        KieSession kieSession = this.kieContainer.newKieSession();

        for (Object param : params) {
            kieSession.insert(param);
        }

        int ruleFiredCount = kieSession.fireAllRules();
        kieSession.destroy();
        System.out.println("触发了" + ruleFiredCount + "条规则");
        pkg.getBody().setData("触发了" + ruleFiredCount + "条规则");

        return pkg;
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
}
