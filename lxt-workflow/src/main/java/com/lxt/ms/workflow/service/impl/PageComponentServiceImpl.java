package com.lxt.ms.workflow.service.impl;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.workflow.mapper.PageComponentMapper;
import com.lxt.ms.workflow.mapper.PageMapper;
import com.lxt.ms.workflow.model.Page;
import com.lxt.ms.workflow.model.PageComponent;
import com.lxt.ms.workflow.model.PageComponentExample;
import com.lxt.ms.workflow.model.PageExample;
import com.lxt.ms.workflow.service.api.PageComponentService;
import com.lxt.ms.workflow.service.api.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pageComponentService")
public class PageComponentServiceImpl implements PageComponentService {
    @Autowired
    private PageComponentMapper pageComponentMapper;

    @Override
    public Packages save(PageComponent pageComponent) throws APIException {
        Packages pkg = new Packages();

        try {
            pageComponentMapper.insert(pageComponent);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("组件保存失败！");
        }

        return pkg;
    }

    @Override
    public Packages delete(String componentId) throws APIException {
        Packages pkg = new Packages();

        try {
            pageComponentMapper.deleteByPrimaryKey(componentId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("组件删除失败！");
        }

        return pkg;
    }

    @Override
    public Packages list(String pageId) throws APIException {
        Packages pkg = new Packages();

        try {
            PageComponentExample example = new PageComponentExample();
            example.createCriteria().andPageIdEqualTo(pageId);
            pageComponentMapper.selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("组件查询失败！");
        }

        return pkg;
    }
}
