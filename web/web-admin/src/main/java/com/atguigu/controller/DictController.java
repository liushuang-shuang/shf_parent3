package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Dict;
import com.atguigu.result.Result;
import com.atguigu.service.DictService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/dict")
@RestController
public class DictController {
    @Reference
    private DictService dictService;
    @GetMapping("/findZnodes")
    public Result findZnode(@RequestParam(value = "id",defaultValue = "0")Long id){
        List<Map<String, Object>> znodes = dictService.findZnodes(id);
        return Result.ok(znodes);
    }

    /**
     * 根据父节点id查出所有子节点
     * @param parentId
     * @return
     */
    @GetMapping("/findListByParentId/{parentId}")
    public Result findListByParentId(@PathVariable Long parentId){
        List<Dict> list = dictService.findListByParentId(parentId);
        return Result.ok(list);
    }

    /**
     * 根据父节点dictCode查询所有子节点
     * @param parentId
     * @return
     */
    @GetMapping("/findDictListByParentDictCode/{dictCode}")
    public Result findDictListByParentDictCode(String dictCode){
        List<Dict> list = dictService.findDictListByParentDictCode(dictCode);
        return Result.ok(list);
    }
}
