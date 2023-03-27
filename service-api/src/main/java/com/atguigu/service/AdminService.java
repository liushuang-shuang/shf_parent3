package com.atguigu.service;
import com.atguigu.base.BaseService;
import com.atguigu.entity.Admin;

import java.util.List;

public interface AdminService extends BaseService<Admin> {
    //只需要查询分页

    /**
     * 新增经纪人需从现有用户中查找所有
     */
    List<Admin> findAll();
}
