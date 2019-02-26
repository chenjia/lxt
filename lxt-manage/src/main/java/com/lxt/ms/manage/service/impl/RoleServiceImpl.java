package com.lxt.ms.manage.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.common.utils.UUIDUtils;
import com.lxt.ms.manage.mapper.RoleMapper;
import com.lxt.ms.manage.mapper.RoleMenuMapper;
import com.lxt.ms.manage.model.*;
import com.lxt.ms.manage.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public Packages list(RoleExample example, PageData pageData) throws APIException {
        Packages pkg = new Packages();

        Page page = null;
        if(pageData != null){
            page = PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize(), true);
        }else{
            pageData = new PageData();
        }

        List<Role> list = roleMapper.selectByExample(example);
        pageData.setData(list);

        if(page != null){
            pageData.setTotal(page.getTotal());
        }

        pkg.getBody().setData(pageData);

        return pkg;
    }

    @Override
    public Packages save(Role role) throws APIException {
        Packages pkg = new Packages();

        Date date = new Date();
        role.setUpdateTime(date);
        if(StringUtils.isEmpty(role.getRoleId())){
            role.setRoleId(UUIDUtils.UUID());
            role.setInsertTime(date);
            roleMapper.insert(role);
        }else{
            roleMapper.updateByPrimaryKeySelective(role);
        }

        return pkg;
    }

    @Override
    public Packages delete(String roleId) throws APIException {
        Packages pkg = new Packages();

        roleMapper.deleteByPrimaryKey(roleId);

        return pkg;
    }

    @Override
    public Packages status(String roleId, int status) throws APIException {
        Packages pkg = new Packages();

        Role role = new Role();
        role.setRoleId(roleId);
        role.setStatus(status);
        roleMapper.updateByPrimaryKeySelective(role);

        return pkg;
    }

    @Override
    public Packages details(String roleId) throws APIException {
        Packages pkg = new Packages();

        Role role = roleMapper.selectByPrimaryKey(roleId);
        pkg.getBody().setData(role);

        return pkg;
    }

    @Override
    public Packages grant(String roleId, String[] menuIds) throws APIException {
        Packages pkg = new Packages();

        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleMenuMapper.deleteByExample(example);

        for(String menuId : menuIds){
            RoleMenuKey roleMenuKey = new RoleMenuKey();
            roleMenuKey.setRoleId(roleId);
            roleMenuKey.setMenuId(menuId);
            roleMenuMapper.insert(roleMenuKey);
        }

        return pkg;
    }
}
