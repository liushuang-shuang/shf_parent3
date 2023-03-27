package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.HouseImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseImageMapper extends BaseMapper<HouseImage> {
    /**
     * 根据houseId和type获取houseImage列表
     * @param houseId
     * @param type
     * @return
     */
    List<HouseImage> findHouseImageByHouseIdAndType(@Param("houseId")Long houseId,@Param("type")Integer type);
}
