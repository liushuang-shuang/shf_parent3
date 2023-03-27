package com.atguigu.mapper;

import com.atguigu.entity.Dict;

import java.util.List;
import java.util.Map;

public interface DictMapper {
    /**
     * 根据parent_id查询所有parent_id相同的数据
     * @param id
     * @return
     */
    List<Dict> findListByParentId(Long parentId);

    /**
     * 判断获取的id是否为parent_id
     * parent_id有一个明显的特征就是另一行数据的id，如果数量不为零，如果数量为零，则称获取的数据有父节点
     * @param id
     * @return
     */
    Integer countIsParent(Long id);

    /**
     * 根据父节点的dict_code找到所有子节点
     * @param dictCode
     * @return
     */
    List<Dict> findDictListByParentDictCode(String dictCode);
}
