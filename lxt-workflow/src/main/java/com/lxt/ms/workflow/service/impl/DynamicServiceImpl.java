package com.lxt.ms.workflow.service.impl;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.workflow.mapper.DynamicMapper;
import com.lxt.ms.workflow.model.Dynamic;
import com.lxt.ms.workflow.model.DynamicExample;
import com.lxt.ms.workflow.model.Page;
import com.lxt.ms.workflow.model.PageExample;
import com.lxt.ms.workflow.service.api.DynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dynamicService")
public class DynamicServiceImpl implements DynamicService {
    @Autowired
    private DynamicMapper dynamicMapper;

    @Override
    public Packages save(Dynamic dynamic) throws APIException {
        Packages pkg = new Packages();

        try {
            dynamicMapper.insert(dynamic);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("页面保存失败！");
        }

        return pkg;
    }

    @Override
    public Packages delete(String dynamicId) throws APIException {
        Packages pkg = new Packages();

        try {
            DynamicExample example = new DynamicExample();
            example.createCriteria().andIdEqualTo(dynamicId);
            dynamicMapper.deleteByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("页面保存失败！");
        }

        return pkg;
    }

    @Override
    public Packages list() throws APIException {
        Packages pkg = new Packages();

        try {
            List<Dynamic> list = dynamicMapper.selectByExample(new DynamicExample());
            pkg.getBody().setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("页面保存失败！");
        }

        return pkg;
    }
}
