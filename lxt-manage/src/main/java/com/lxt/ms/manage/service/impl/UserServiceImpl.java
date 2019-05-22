package com.lxt.ms.manage.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.common.utils.*;
import com.lxt.ms.manage.mapper.UserLogMapper;
import com.lxt.ms.manage.mapper.UserMapper;
import com.lxt.ms.manage.mapper.UserRoleMapper;
import com.lxt.ms.manage.mapper.UserSettingMapper;
import com.lxt.ms.manage.mapper.ext.UserExtMapper;
import com.lxt.ms.manage.model.*;
import com.lxt.ms.manage.model.UserExample.Criteria;
import com.lxt.ms.manage.service.api.UserService;
import com.lxt.ms.manage.service.bo.LoginBO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserLogMapper userLogMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private UserExtMapper userExtMapper;

	@Autowired
	private UserSettingMapper userSettingMapper;

	@Autowired
	RestTemplate restTemplate;

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
	public Packages qrcodeLogin(String qrcode) throws APIException {
		Packages pkg = new Packages();

		String userId = (String) CacheUtils.get("QRCODE_"+qrcode);

		if(userId == null){
			pkg.getHead().setStatus(500);
			pkg.getHead().setMsg("无效的二维码！");
		}else {
			CacheUtils.del("QRCODE_" + userId);
			UserExample example = new UserExample();
			Criteria criteria = example.createCriteria();
			criteria.andUserIdEqualTo(userId);
			List<User> list = userMapper.selectByExample(example);
			if (list.size() == 1) {
				LoginBO loginBO = new LoginBO();
				User user = list.get(0);
				user.setPassword(null);
				loginBO.setUser(user);
				UserSetting userSetting = userSettingMapper.selectByPrimaryKey(user.getUserId());
				List<String> resourceList = userExtMapper.resourceList(user.getUserId());
				CacheUtils.del("RESOURCE_" + user.getUserId());
				for (String resource : resourceList) {
					CacheUtils.sSet("RESOURCE_" + user.getUserId(), resource);
				}
				loginBO.setUserSetting(userSetting);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", user.getUserId());
				String token = JWTUtils.build(map);
				pkg.getHead().setUserId(user.getUserId());
				pkg.getHead().setToken(token);
				pkg.getBody().setData(loginBO);
			}
		}

		return pkg;
	}

	@Override
	public Packages qrcode() throws APIException {
		Packages pkg = new Packages();

		String uuid = UUIDUtils.UUID();
		CacheUtils.set("QRCODE_"+uuid, UUIDUtils.UUID(), 3 * 60);
		pkg.getBody().setData(uuid);

		return pkg;
	}

	@Override
	public Packages scan(String $userId, String $token, String qrcode, String type, String msg) throws APIException {
		Packages pkg = new Packages();
		pkg.getHead().setUserId($userId);
		pkg.getHead().setToken($token);

		if("qrcodeScan".equalsIgnoreCase(type)){
			CacheUtils.set("QRCODE_"+qrcode, $userId, 2 * 60);
		}
		MultiValueMap<String, Object> restParamMap = new LinkedMultiValueMap<String, Object>();
		Map<String, Object> message = new HashMap<>();
		message.put("receiveId", "QRCODE_"+qrcode);
		message.put("qrcode", qrcode);
		message.put("type", type);
		message.put("msg", msg);
		String messageJson = JSONUtils.obj2Json(message);
		message.clear();
		message.put("messageJson", messageJson);
		pkg.getHead().setUserId($userId);
		pkg.getHead().setToken($token);
		pkg.getBody().setData(message);

		try {
			String encryptedText = SecurityUtils.encrypt(JSONUtils.obj2Json(pkg)).replaceAll("\r\n|\n", "");
			restParamMap.add("request", encryptedText);
		} catch (Exception e) {
			e.printStackTrace();
			throw new APIException(e);
		}
		// 推送二维码登录消息
		pkg = restTemplate.postForObject("http://lxt-gateway/api/push/serverPush/sendMessage", restParamMap, Packages.class);

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

	@Override
	public Packages consoleLog(String $userId, List<Map> logs) throws APIException {
		Packages pkg = new Packages();

		System.out.println(JSONUtils.obj2Json(logs));

		Date date = new Date();
		UserLog log = new UserLog();
		for(Map map : logs){
			map.put("time", DateUtils.str2Date((String) map.get("time"), DateUtils.LONG_DATE));
			try {
				BeanUtils.copyProperties(log, map);
				log.setUserId($userId);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new APIException(e);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				throw new APIException(e);
			}
			log.setLogId(UUIDUtils.UUID());
			log.setInsertTime(date);
			userLogMapper.insert(log);
		}

		return pkg;
	}
}
