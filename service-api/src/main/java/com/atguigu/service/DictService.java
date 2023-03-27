package com.atguigu.service;

import com.atguigu.entity.Dict;

import java.util.List;
import java.util.Map;

public interface DictService {
    /**
     * 根据parentID查询
     * @return
     */
    List<Map<String,Object>> findZnodes(Long id);
    /**
     * 根据父节点的dict_code找到所有子节点
     * @param dictCode
     * @return
     */
    List<Dict> findDictListByParentDictCode(String dictCode);
    /**
     * 根据parent_id查询所有parent_id相同的数据
     * @param
     * @return
     */
    List<Dict> findListByParentId(Long parentId);
}
