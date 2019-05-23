package com.lxt.ms.manage.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.common.exception.APIException;
import com.lxt.ms.common.utils.DateUtils;
import com.lxt.ms.common.utils.JSONUtils;
import com.lxt.ms.common.utils.UUIDUtils;
import com.lxt.ms.manage.mapper.UserLogMapper;
import com.lxt.ms.manage.model.UserLog;
import com.lxt.ms.manage.model.UserLogExample;
import com.lxt.ms.manage.service.api.LogService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("logService")
public class LogServiceImpl implements LogService {

    @Autowired
    private UserLogMapper userLogMapper;

    @Override
    public Packages save(String $userId, List<Map> logs) throws APIException {
        Packages pkg = new Packages();

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

    @Override
    public Packages list(UserLogExample example, PageData pageData) throws APIException {
        Packages pkg = new Packages();

        Page page = PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize(), true);
        List<UserLog> userList = userLogMapper.selectByExample(example);
        pageData.setData(userList);
        pageData.setTotal(page.getTotal());
        pkg.getBody().setData(pageData);

        return pkg;
    }
}
