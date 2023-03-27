package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.entity.Dict;
import com.atguigu.mapper.DictMapper;
import com.atguigu.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service(interfaceClass = DictService.class)
public class DictServiceImpl implements DictService {
    @Autowired
    private DictMapper dictMapper;
    /**
     * 根据parentID查询
     *
     * @param id
     * @return
     */
    @Override
    public List<Map<String, Object>> findZnodes(Long id) {
        List<Dict> list = dictMapper.findListByParentId(id);
        List<Map<String, Object>> znodes = list.stream().map(dict -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", dict.getId());
            map.put("name", dict.getName());
            map.put("isParent", dictMapper.countIsParent(dict.getId()) > 0);
            return map;
        }).collect(Collectors.toList());
        return znodes;
    }

    /**
     * 根据父节点的dict_code找到所有子节点
     *
     * @param dictCode
     * @return
     */
    @Override
    public List<Dict> findDictListByParentDictCode(String dictCode) {
        return dictMapper.findDictListByParentDictCode(dictCode);
    }

    /**
     * 根据parent_id查询所有parent_id相同的数据
     *
     * @param parentId@return
     */
    @Override
    public List<Dict> findListByParentId(Long parentId) {
        return dictMapper.findListByParentId(parentId);
    }
}
