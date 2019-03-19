package com.lxt.ms.drools.controller;


import com.lxt.ms.drools.model.Person;
import com.lxt.ms.drools.model.Rule;
import com.lxt.ms.drools.service.api.RuleService;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DroolsController {

    @Autowired
    private RuleService ruleService;

    @Resource
    private KieContainer kieContainer;

    @RequestMapping(value = "/reload")
    public String reload(@RequestParam("name") String name, @RequestParam("age") int age) throws Exception{

        List<Rule> ruleList = ruleService.reload();

        return "reload "+ruleList.size();
    }

    @RequestMapping(value = "/test")
    public String test(@RequestParam String name, @RequestParam int age) throws Exception{
        KieContainer kieContainer = ruleService.getKieContainer();
        KieSession kieSession = kieContainer.newKieSession();

        Person person = new Person();
        person.setAge(age);
        person.setName(name);
        kieSession.insert(person);

        int ruleFiredCount = kieSession.fireAllRules();
        kieSession.destroy();
        System.out.println("触发了" + ruleFiredCount + "条规则");

        return ""+ruleFiredCount;
    }

    @RequestMapping(value = "/test2")
    public String test2(@RequestParam String name, @RequestParam int age) throws Exception{
        KieSession kieSession = kieContainer.newKieSession();

        Person person = new Person();
        person.setAge(age);
        person.setName(name);
        kieSession.insert(person);

        int ruleFiredCount = kieSession.fireAllRules();
        kieSession.destroy();
        System.out.println("触发了" + ruleFiredCount + "条规则");

        return ""+ruleFiredCount;
    }



}
