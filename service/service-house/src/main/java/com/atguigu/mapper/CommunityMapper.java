package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.Community;

import java.util.List;

public interface CommunityMapper extends BaseMapper<Community> {
    /**
     * 查找所有小区---在房源管理
     * @return
     */
    List<Community> findAll();
}
