package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.House;
import com.atguigu.mapper.HouseMapper;
import com.atguigu.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = HouseService.class)
public class HouseServiceImpl extends BaseServiceImpl<House> implements HouseService {
    @Autowired
    private HouseMapper houseMapper;
    @Override
    public BaseMapper getEntityMapper() {
        return houseMapper;
    }

    /**
     * 新增发布，取消发布接口
     *
     * @param id
     * @param status
     */
    @Override
    public void publish(Long id, Integer status) {
        House house = houseMapper.getById(id);
        house.setId(id);
        house.setStatus(status);
        houseMapper.update(house);
    }
}
