package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.Admin;
import com.atguigu.entity.HouseBroker;
import com.atguigu.service.AdminService;
import com.atguigu.service.HouseBrokerService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/houseBroker")
public class HouseBrokerController extends BaseController<HouseBroker> {
    @Reference
    private HouseBrokerService houseBrokerService;
    @Reference
    private AdminService adminService;

    private static final String HOUSE_BROKER_CREATE = "houseBroker/create";
    private static final String HOUSE_BROKER_DEIT = "houseBroker/edit";
    private static final String HOUSE_DETAIL = "redirect:/house/";

    @RequestMapping("/create")
    public String create(HouseBroker houseBroker, Model model){
        findAllAdmin(model);
        model.addAttribute("houseBroker",houseBroker);
        return HOUSE_BROKER_CREATE;
    }
    @RequestMapping("/save")
    public String save(HouseBroker houseBroker, Model model){
        Admin admin = adminService.getById(houseBroker.getBrokerId());//houseBroker /save = HouseBroker(houseId=4, brokerId=3, brokerName=null, brokerHeadUrl=null)
        houseBroker.setBrokerHeadUrl(admin.getHeadUrl());
        houseBroker.setBrokerName(admin.getName());
        houseBrokerService.save(houseBroker);
        return getPageSuccess(model,"新增成功！");
    }

    @RequestMapping("/edit/{id}")
    public String update(@PathVariable("id")Long id,Model model){
        HouseBroker houseBroker = houseBrokerService.getById(id); //houseBroker/edit = HouseBroker(houseId=4, brokerId=6, brokerName=house1, brokerHeadUrl=http://r61cnlsfq.hn-bkt.clouddn.com/23ebd355-1e6a-4eea-b5a8-f9b112971e5c)
        model.addAttribute("houseBroker",houseBroker);
        //查询所有经纪人返回到下一页面
        findAllAdmin(model);
        return HOUSE_BROKER_DEIT;
    }

    /**
     * 修改经纪人
     * @param houseBroker
     * @param model
     * @return
     */
    @PostMapping("/update")
    public String update(HouseBroker houseBroker,Model model){
        Admin admin = adminService.getById(houseBroker.getBrokerId());
        //houseBroker.setHouseId();
        houseBroker.setBrokerName(admin.getName());
        houseBroker.setBrokerHeadUrl(admin.getHeadUrl());
        houseBrokerService.update(houseBroker);
        return getPageSuccess(model,"修改经纪人成功");
    }
    @RequestMapping("/delete/{houseId}/{id}")
    public String delete(@PathVariable("houseId")Long houseId,@PathVariable("id")Long id){
        houseBrokerService.delete(id);
        return HOUSE_DETAIL+houseId;//重新跳转该房源详情页面
    }
    /**
     * 查询所有用户
     * @param model
     */
    private void findAllAdmin(Model model) {
        model.addAttribute("adminList",adminService.findAll());
    }
}
