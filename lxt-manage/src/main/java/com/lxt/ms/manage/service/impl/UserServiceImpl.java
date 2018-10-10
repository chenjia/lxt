package com.lxt.ms.manage.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.common.utils.CacheUtils;
import com.lxt.ms.common.utils.CaptchaUtils;
import com.lxt.ms.common.utils.JWTUtils;
import com.lxt.ms.common.utils.SecurityUtils;
import com.lxt.ms.manage.mapper.UserMapper;
import com.lxt.ms.manage.mapper.UserSettingMapper;
import com.lxt.ms.manage.model.User;
import com.lxt.ms.manage.model.UserExample;
import com.lxt.ms.manage.model.UserExample.Criteria;
import com.lxt.ms.manage.model.UserSetting;
import com.lxt.ms.manage.service.api.UserService;
import com.lxt.ms.manage.service.bo.LoginBO;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

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

		String temp = (String) CacheUtils.get(captchaToken);
		System.out.println(temp);
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
				UserSetting userSetting = userSettingMapper
						.selectByPrimaryKey(user.getUserId());
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
}
