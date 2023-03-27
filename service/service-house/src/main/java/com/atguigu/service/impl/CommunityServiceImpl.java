package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.Community;
import com.atguigu.mapper.CommunityMapper;
import com.atguigu.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = CommunityService.class)
public class CommunityServiceImpl extends BaseServiceImpl<Community> implements CommunityService {
    @Autowired
    private CommunityMapper communityMapper;
    @Override
    public BaseMapper<Community> getEntityMapper() {
        return communityMapper;
    }

    /**
     * 查找所有小区---在房源管理
     *
     * @return
     */
    @Override
    public List<Community> findAll() {
        List<Community> list = communityMapper.findAll();
        return list;
    }
}
