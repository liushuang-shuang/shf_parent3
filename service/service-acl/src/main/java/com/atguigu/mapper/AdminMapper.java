package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.Admin;

import java.util.List;

public interface AdminMapper extends BaseMapper<Admin> {
    //只需要查询分页
    /**
     * 新增经纪人从现有用户中查找
     */
    List<Admin> findAll();
}
