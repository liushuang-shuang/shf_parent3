package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.Community;
import com.atguigu.entity.Dict;
import com.atguigu.service.CommunityService;
import com.atguigu.service.DictService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController<Community> {
    @Reference
    private CommunityService communityService;
    @Reference
    private DictService dictService;

    private static final String PAGE_INDEX = "community/index";//访问首页
    private final static String PAGE_CREATE = "community/create";//新增
    private final static String PAGE_EDIT = "community/edit";//修改
    private final static String PAGE_DELETE = "redirect:/community";//删除

    @RequestMapping
    public String index(@RequestParam Map filters, Model model){
        if (!filters.containsKey("areaId")){
            filters.put("areaId","");
        }
        if (!filters.containsKey("plateId")){
            filters.put("plateId","");
        }
        //分页查询
        PageInfo page = communityService.findPage(filters);
        //根据dictCode查询子节点
        List<Dict> areaList = dictService.findDictListByParentDictCode("Beijing");
        model.addAttribute("areaList",areaList);
        model.addAttribute("filters",filters);
        model.addAttribute("page",page);
        return PAGE_INDEX;
    }
    /**
     * 访问新增页面之前需要查询一些数据然后跳转到新增页面显示
     */
    @GetMapping("/create")
    public String create(Model model){
        List<Dict> areaList = dictService.findDictListByParentDictCode("beijing");
        model.addAttribute("areaList",areaList);
        return PAGE_CREATE;
    }

    /**
     * 新增
     * @param community
     * @param model
     * @return
     */
    @RequestMapping("/save")
    public String save(Community community,Model model){
        communityService.save(community);
        model.addAttribute("community",community);
        return getPageSuccess(model,"新增成功！");
    }

    /**
     * 修改回显
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id")Long id, Model model){
        Community community = communityService.getById(id);
        model.addAttribute("community",community);
        //查询所有北京下的所有区域
        List<Dict> areaList = dictService.findDictListByParentDictCode("beijing");
        model.addAttribute("areaList",areaList);
        return PAGE_EDIT;
    }
    @RequestMapping("/update")
    public String update(Community community,Model model){
        communityService.update(community);
        model.addAttribute("community",community);
        return getPageSuccess(model,"修改成功！");
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id,Model model){
        communityService.delete(id);
        return PAGE_DELETE;
    }
}
