package com.it.wei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.it.wei.model.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 根据用户名查询拥有的权限
     * @param roleId
     * @return
     */
    List<Permission> findPermissionByRoleId(@Param("roleId") Integer roleId);
}
