package com.lxt.ms.workflow.service.impl;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.workflow.mapper.PageMapper;
import com.lxt.ms.workflow.model.Page;
import com.lxt.ms.workflow.model.PageExample;
import com.lxt.ms.workflow.service.api.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pageService")
public class PageServiceImpl implements PageService {
    @Autowired
    private PageMapper pageMapper;

    @Override
    public Packages save(Page page) throws APIException {
        Packages pkg = new Packages();

        try {
            pageMapper.insert(page);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("页面保存失败！");
        }

        return pkg;
    }

    @Override
    public Packages delete(String id) throws APIException {
        Packages pkg = new Packages();

        try {
            PageExample example = new PageExample();
            example.createCriteria().andIdEqualTo(id);
            pageMapper.deleteByExample(example);
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
            List<Page> list = pageMapper.selectByExample(new PageExample());
            pkg.getBody().setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("页面保存失败！");
        }

        return pkg;
    }
}
