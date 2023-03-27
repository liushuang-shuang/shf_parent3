package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.House;

import java.util.List;

public interface HouseService extends BaseService<House>{
    /**
     * 新增发布，取消发布接口
     */
    void publish(Long id,Integer status);

}
