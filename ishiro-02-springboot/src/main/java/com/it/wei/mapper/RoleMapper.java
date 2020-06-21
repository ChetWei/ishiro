package com.it.wei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.it.wei.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    List<Role> findRoleByUsername(@Param("username") String username);
}
