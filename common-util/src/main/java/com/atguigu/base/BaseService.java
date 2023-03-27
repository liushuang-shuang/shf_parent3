package com.atguigu.base;

import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface BaseService<T> {
    //新增
    void save(T t);
    //根据id获取
    T getById(Long id);
    //修改
    void update(T t);
    //删除
    void delete(Long id);
    //分页查询
    PageInfo<T> findPage(Map<String, Object> filters);
}
