package com.atguigu.base;

import com.github.pagehelper.Page;

import java.util.Map;

/**
 * 作为基类接口
 */
public interface BaseMapper<T> {
    //插入
    void save(T t);
    //修改
    void update(T t);
    //根据id获取
    T getById(Long id);
    //删除
    void delete(Long id);
    //分页查询
    Page<T> findPage(Map<String,Object> filters);
}
