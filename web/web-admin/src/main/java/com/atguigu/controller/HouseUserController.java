package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.House;
import com.atguigu.entity.HouseUser;
import com.atguigu.service.HouseService;
import com.atguigu.service.HouseUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/houseUser")
public class HouseUserController extends BaseController<HouseUser> {
    @Reference
    private HouseUserService houseUserService;
    @Reference
    private HouseService houseService;

    private static final String HOUSE_USER_CREATE = "houseUser/create";
    private static final String HOUSE_USER_EDIT = "houseUser/edit";
    private static final String HOUSE_USER_DELETE = "redirect:/house/";

    @RequestMapping("/create")
    public String create(HouseUser houseUser,Model model){
        //House house = houseService.getById(id);
        model.addAttribute("houseUser",houseUser);
        return HOUSE_USER_CREATE;
    }
    @RequestMapping("/save")
    public String save(HouseUser houseUser,Model model){
        houseUserService.save(houseUser);
        model.addAttribute("houseUser",houseUser);
        return getPageSuccess(model,"新增房东成功！");
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id")Long id,Model model){
        HouseUser houseUser = houseUserService.getById(id);
        model.addAttribute("houseUser",houseUser);
        return HOUSE_USER_EDIT;
    }
    @RequestMapping("/update")
    public String update(HouseUser houseUser,Model model){
        houseUserService.update(houseUser);
        model.addAttribute("houseUser",houseUser);
        return getPageSuccess(model,"修改房东信息成功！");
    }
    @RequestMapping("/delete/{houseId}/{id}")
    public String delete(@PathVariable("houseId")Long houseId,@PathVariable("id")Long id){
        houseUserService.delete(id);
        return HOUSE_USER_DELETE+houseId;
    }
}
