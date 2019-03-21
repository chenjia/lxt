package com.lxt.ms.workflow.service.impl;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.common.utils.UUIDUtils;
import com.lxt.ms.workflow.mapper.RegexMapper;
import com.lxt.ms.workflow.model.Regex;
import com.lxt.ms.workflow.model.RegexExample;
import com.lxt.ms.workflow.service.api.RegexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("regexService")
public class RegexServiceImpl implements RegexService {
    @Autowired
    private RegexMapper regexMapper;

    @Override
    public Packages save(Regex regex, String $userId) throws APIException {
        Packages pkg = new Packages();

        Date date = new Date();
        regex.setUpdateTime(date);
        try {
            if(regex.getId() == null){
                regex.setId(UUIDUtils.UUID());
                regex.setUserId($userId);
                regex.setInsertTime(date);
                regexMapper.insert(regex);
            }else{
                regexMapper.updateByPrimaryKeySelective(regex);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("校验规则保存失败！");
        }

        return pkg;
    }

    @Override
    public Packages delete(String id) throws APIException {
        Packages pkg = new Packages();

        try {
            regexMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("校验规则删除失败！");
        }

        return pkg;
    }

    @Override
    public Packages list(RegexExample example) throws APIException {
        Packages pkg = new Packages();

        try {
            List<Regex> list = regexMapper.selectByExample(new RegexExample());
            pkg.getBody().setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("校验规则查询失败！");
        }

        return pkg;
    }

    @Override
    public Packages details(String id) throws APIException {
        Packages pkg = new Packages();

        Regex regex = regexMapper.selectByPrimaryKey(id);
        pkg.getBody().setData(regex);

        return pkg;
    }
}
