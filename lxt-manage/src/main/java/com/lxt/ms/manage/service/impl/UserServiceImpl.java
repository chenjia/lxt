package com.lxt.ms.manage.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.common.utils.CacheUtils;
import com.lxt.ms.common.utils.CaptchaUtils;
import com.lxt.ms.common.utils.JWTUtils;
import com.lxt.ms.common.utils.SecurityUtils;
import com.lxt.ms.manage.mapper.UserMapper;
import com.lxt.ms.manage.mapper.UserRoleMapper;
import com.lxt.ms.manage.mapper.UserSettingMapper;
import com.lxt.ms.manage.mapper.ext.UserExtMapper;
import com.lxt.ms.manage.model.*;
import com.lxt.ms.manage.model.UserExample.Criteria;
import com.lxt.ms.manage.service.api.UserService;
import com.lxt.ms.manage.service.bo.LoginBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private UserExtMapper userExtMapper;

	@Autowired
	private UserSettingMapper userSettingMapper;

	@Override
	public Packages captcha() throws APIException {
		return new Packages(CaptchaUtils.getCaptcha());
	}

	@Override
	public Packages login(String username, String password, String captcha,
			String captchaToken) throws APIException {
		Packages pkg = new Packages();

		if (captcha.equalsIgnoreCase((String) CacheUtils.get(captchaToken))) {
			CacheUtils.del(captchaToken);
			
			UserExample example = new UserExample();
			Criteria criteria = example.createCriteria();
			criteria.andUsernameEqualTo(username);
			criteria.andPasswordEqualTo(SecurityUtils.md5Encrypt(password));
			List<User> list = userMapper.selectByExample(example);
			if (list.size() == 1) {
				LoginBO loginBO = new LoginBO();
				User user = list.get(0);
				user.setPassword(null);
				loginBO.setUser(user);
				UserSetting userSetting = userSettingMapper.selectByPrimaryKey(user.getUserId());
				List<String> resourceList = userExtMapper.resourceList(user.getUserId());
                CacheUtils.del("RESOURCE_"+user.getUserId());
				for (String resource : resourceList) {
                    CacheUtils.sSet("RESOURCE_"+user.getUserId(), resource);
                }
				loginBO.setUserSetting(userSetting);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", user.getUserId());
				String token = JWTUtils.build(map);
				pkg.getHead().setUserId(user.getUserId());
				pkg.getHead().setToken(token);
				pkg.getBody().setData(loginBO);
			}else{
				pkg.getHead().setStatus(500);
				pkg.getHead().setMsg("用户名或密码错误！");
			}
		} else {
			pkg.getHead().setStatus(500);
			pkg.getHead().setMsg("验证码错误！");
		}

		return pkg;
	}

	@Override
	public Packages list(UserExample example, PageData pageData) throws APIException {
		Packages pkg = new Packages();

		Page page = PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize(), true);
		List<Map<String, Object>> userList = userExtMapper.selectUserByExample(example);
		pageData.setData(userList);
		pageData.setTotal(page.getTotal());
		pkg.getBody().setData(pageData);

		return pkg;
	}

	@Override
	public Packages save(User user) throws APIException {
		Packages pkg = new Packages();

		if(StringUtils.isEmpty(user.getUserId())){
			userMapper.insert(user);
		}else{
			userMapper.updateByPrimaryKeySelective(user);
		}

		return pkg;
	}

	@Override
	public Packages delete(String userId) throws APIException {
		Packages pkg = new Packages();

		userMapper.deleteByPrimaryKey(userId);

		return pkg;
	}

	@Override
	public Packages status(String userId, int status) throws APIException {
		Packages pkg = new Packages();

		User user = new User();
		user.setUserId(userId);
		user.setStatus(status);
		userMapper.updateByPrimaryKeySelective(user);

		return pkg;
	}

	@Override
	public Packages grant(String userId, String[] roleIds) throws APIException {
		Packages pkg = new Packages();

		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUserIdEqualTo(userId);
		userRoleMapper.deleteByExample(example);

		for(String roleId : roleIds){
			UserRoleKey userRoleKey = new UserRoleKey();
			userRoleKey.setUserId(userId);
			userRoleKey.setRoleId(roleId);
			userRoleMapper.insert(userRoleKey);
		}

		return pkg;
	}
}
