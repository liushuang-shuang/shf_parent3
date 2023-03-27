package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.Community;

import java.util.List;

public interface CommunityService extends BaseService<Community> {
    /**
     * 查找所有小区---在房源管理
     * @return
     */
    List<Community> findAll();
}
