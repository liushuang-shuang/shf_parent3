package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import com.atguigu.util.FileUtil;
import com.atguigu.util.QiniuUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController<Admin> {
    @Reference
    private AdminService adminService;
    private final static String PAGE_INDEX="admin/index";
    private final static String PAGE_EDIT="admin/edit";
    private final static String PAGE_DELETE="redirect:/admin";
    private final static String PAGE_UPLOAD="admin/upload";

    @RequestMapping
    public String index(@RequestParam Map filters,Model model){
        if (!filters.containsKey("pageNum")){
            filters.put("pageNum",1);
        }
        if (!filters.containsKey("pageSize")){
            filters.put("pageSize",10);
        }
        PageInfo page = adminService.findPage(filters);
        model.addAttribute("filters",filters);
        model.addAttribute("page",page);
        return PAGE_INDEX;
    }
    @PostMapping("/save")
    public String save(Admin admin,Model model){
        adminService.save(admin);
        model.addAttribute("admin",admin);
        return getPageSuccess(model,"新增成功！");
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model){
        Admin admin = adminService.getById(id);
        model.addAttribute("admin",admin);
        return PAGE_EDIT;
    }
    @PostMapping("/update")
    public String update(Admin admin,Model model){
        adminService.update(admin);
        model.addAttribute("admin",admin);
        return getPageSuccess(model,"修改成功！");
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,Model model){
        adminService.delete(id);
        return PAGE_DELETE;
    }
    /**
     * 上传头像
     */
    @RequestMapping("/uploadShow/{id}")
    public String upload(@PathVariable("id")Long id,Model model){
        model.addAttribute("id",id);
        return PAGE_UPLOAD;
    }
    @RequestMapping("/upload/{id}")
    public String update(@PathVariable("id")Long id, Model model, @RequestParam("file")MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String uuidName = FileUtil.getUUIDName(originalFilename);
        QiniuUtils.upload2Qiniu(file.getBytes(),uuidName);
        Admin admin = new Admin();
        admin.setId(id);
        admin.setHeadUrl("http://r9einmpyx.hn-bkt.clouddn.com/"+QiniuUtils.getUrl(uuidName));
        adminService.update(admin);
        return getPageSuccess(model,"上传成功！");
    }


}
