package com.lxt.ms.manage.service.api;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.manage.model.Role;
import com.lxt.ms.manage.model.RoleExample;

public interface RoleService {
	public abstract Packages list(RoleExample example, PageData pageData) throws APIException;

	public abstract Packages save(Role role) throws APIException;

	public abstract Packages delete(String roleId) throws APIException;

	public abstract Packages status(String roleId, int status) throws APIException;

	public abstract Packages details(String roleId) throws APIException;

	public abstract Packages grant(String roleId, String[] menuIds) throws APIException;
}
