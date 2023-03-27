package com.atguigu.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController<Role> {

    @Reference
    private RoleService roleService;

    private final static String PAGE_INDEX="role/index";//被final修饰代表只能被赋值一次，被static修饰代表是静态的
    private final static String PAGE_DEIT="role/edit";//被final修饰代表只能被赋值一次，被static修饰代表是静态的
    private final static String PAGE_DELETE="redirect:/role";

    @RequestMapping
    public String index(@RequestParam Map filters,Model model){
        if (!filters.containsKey("pageNum")){
            filters.put("pageNum",1);
        }
        if (filters.containsKey("pageSize")){
            filters.put("pageSize",10);
        }
        PageInfo<Role> page = roleService.findPage(filters);
        model.addAttribute("filters",filters);
        model.addAttribute("page",page);
        return PAGE_INDEX;
    }
    @PostMapping("/save")
    public String save(Role role,Model model){
        roleService.save(role);
        model.addAttribute("role",role);
        return getPageSuccess(model,"新增成功！");
    }
    @GetMapping("edit/{id}")
    public String getById(@PathVariable Long id,Model model){//@PathVariable绑定id
        Role role = roleService.getById(id);
        model.addAttribute("role",role);
        return PAGE_DEIT;
    }
    @PostMapping("/update")
    public String update(Role role,Model model){
     roleService.update(role);
     model.addAttribute("role",role);
     return getPageSuccess(model,"修改成功！");
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        roleService.delete(id);
        return PAGE_DELETE;
    }
}
