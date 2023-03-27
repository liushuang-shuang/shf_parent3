package com.atguigu.base;

import com.atguigu.util.CastUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Transactional(propagation = Propagation.REQUIRED)
public abstract class BaseServiceImpl<T> {

    public abstract BaseMapper<T> getEntityMapper();//实现抽象类的接口必须重写该方法，注入一个mapper对象


    public void save(T t) {
        getEntityMapper().save(t);

    }
    public T getById(Long id) {
        return getEntityMapper().getById(id);
    }

    public void update(T t) {
        getEntityMapper().update(t);
    }

    public void delete(Long id) {
        getEntityMapper().delete(id);
    }
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public PageInfo<T> findPage(Map<String, Object> filters) {
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 10);
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<T>(getEntityMapper().findPage(filters),1);
    }
}
