package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.HouseBroker;

import java.util.List;

public interface HouseBrokerMapper extends BaseMapper<HouseBroker> {
    /**
     * 查找房源的经纪人
     */
    List<HouseBroker> findHouseBrokerByHouseId(Long houseId);
}
