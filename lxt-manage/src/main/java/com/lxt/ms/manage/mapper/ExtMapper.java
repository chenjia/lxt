package com.lxt.ms.manage.mapper;

import com.lxt.ms.common.bean.web.PageData;
import com.lxt.ms.manage.model.UserExample;

import java.util.List;
import java.util.Map;

public interface ExtMapper {
    public abstract List<Map<String, Object>> selectUserByExample(UserExample example);
}
