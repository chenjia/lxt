package com.lxt.ms.manage.service.impl;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.manage.mapper.DepartmentMapper;
import com.lxt.ms.manage.model.Department;
import com.lxt.ms.manage.model.DepartmentExample;
import com.lxt.ms.manage.service.api.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Packages list(PageData pageData) throws APIException {
        Packages pkg = new Packages();

        DepartmentExample example = new DepartmentExample();

        List<Department> list = departmentMapper.selectByExample(example);

        return pkg;
    }
}
