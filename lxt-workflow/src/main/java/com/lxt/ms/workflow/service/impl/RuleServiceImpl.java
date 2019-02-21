package com.lxt.ms.workflow.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.workflow.mapper.PageMapper;
import com.lxt.ms.workflow.mapper.RuleMapper;
import com.lxt.ms.workflow.model.PageExample;
import com.lxt.ms.workflow.model.ProcessExample;
import com.lxt.ms.workflow.model.Rule;
import com.lxt.ms.workflow.model.RuleExample;
import com.lxt.ms.workflow.service.api.PageService;
import com.lxt.ms.workflow.service.api.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("ruleService")
public class RuleServiceImpl implements RuleService {
    @Autowired
    private RuleMapper ruleMapper;

    @Override
    public Packages save(Rule rule) throws APIException {
        Packages pkg = new Packages();

        Date date = new Date();
        rule.setUpdateTime(date);
        try {
            if(rule.getRuleId() == null){
                rule.setInsertTime(date);
                ruleMapper.insert(rule);
            }else{
                ruleMapper.updateByPrimaryKeySelective(rule);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("校验规则保存失败！");
        }

        return pkg;
    }

    @Override
    public Packages delete(String ruleId) throws APIException {
        Packages pkg = new Packages();

        try {
            ruleMapper.deleteByPrimaryKey(ruleId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("校验规则删除失败！");
        }

        return pkg;
    }

    @Override
    public Packages list(RuleExample example, PageData pageData) throws APIException {
        Packages pkg = new Packages();

        try {
            Page page = PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize(), true);
            List<Rule> list = ruleMapper.selectByExample(new RuleExample());
            pageData.setData(list);
            pageData.setTotal(page.getTotal());
            pkg.getBody().setData(pageData);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("校验规则查询失败！");
        }

        return pkg;
    }
}
