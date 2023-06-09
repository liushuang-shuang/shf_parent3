package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.Role;
import com.atguigu.mapper.RoleMapper;
import com.atguigu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(propagation = Propagation.REQUIRED)
@Service(interfaceClass = RoleService.class)
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Autowired
    public RoleMapper roleMapper;

    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)//支持事务，若没有事务则以非事务方式运行
    @Override
    public List<Role> findAll(){
        List<Role> roleList = roleMapper.findAll();
        return roleList;
    }

    @Override
    public BaseMapper<Role> getEntityMapper() {
        return roleMapper;//重写抽象方法注入该对象
    }
}
