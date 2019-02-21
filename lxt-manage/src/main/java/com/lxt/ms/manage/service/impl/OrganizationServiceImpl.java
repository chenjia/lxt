package com.lxt.ms.manage.service.impl;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.manage.mapper.OrganizationMapper;
import com.lxt.ms.manage.model.Organization;
import com.lxt.ms.manage.model.OrganizationExample;
import com.lxt.ms.manage.service.api.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public Packages children(String parentId) throws APIException {
        Packages pkg = new Packages();

        OrganizationExample example = new OrganizationExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<Organization> list = organizationMapper.selectByExample(example);
        pkg.getBody().setData(list);

        return pkg;
    }
}
