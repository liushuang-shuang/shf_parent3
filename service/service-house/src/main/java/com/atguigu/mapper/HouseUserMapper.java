package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.HouseUser;

import java.util.List;

//房东
public interface HouseUserMapper extends BaseMapper<HouseUser> {
    /**
     * 根据houseId查找所有该房源的房东列表
     */
    List<HouseUser> findHouseUseByHouseId(Long houseId);
}
