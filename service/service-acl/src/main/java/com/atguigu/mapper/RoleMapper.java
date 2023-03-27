package com.atguigu.mapper;
import com.atguigu.base.BaseMapper;
import com.atguigu.entity.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    //查询所有角色
    List<Role> findAll();//day5 12

}
