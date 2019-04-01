package com.lxt.ms.manage.mapper.ext;

import com.lxt.ms.manage.model.User;
import com.lxt.ms.manage.model.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserExtMapper {
    /**
     * 查询用户是否有访问某菜单权限
     * @param userId
     * @return
     */
    List<String> resourceList(String userId);

    /**
     * 条件查询用户
     * @param example
     * @return
     */
    public abstract List<Map<String, Object>> selectUserByExample(UserExample example);
}