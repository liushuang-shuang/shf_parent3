package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.en.HouseStatus;
import com.atguigu.entity.House;
import com.atguigu.service.*;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/house")
public class HouseController extends BaseController<House> {
    @Reference
    private HouseService houseService;
    @Reference
    private CommunityService communityService;
    @Reference
    private DictService dictService;
    @Reference
    private HouseImageService houseImageService;
    @Reference
    private HouseBrokerService houseBrokerService;
    @Reference
    private HouseUserService houseUserService;

    private static final String HOUSE_INDEX = "house/index";
    private static final String HOUSE_CREATE = "house/create";
    private static final String HOUSE_UPDATE = "house/edit";
    private static final String HOUSE_DELETE = "redirect:/house";
    private static final String HOUSE_DETAIL = "house/show";

    /**
     * 1.分页查询
     * 2.获取所有的数据字典分享到请求域
     * @param filters
     * @param model
     * @return
     */
    @RequestMapping
    public String index(@RequestParam Map filters, Model model){
        if (!filters.containsKey("pageNum")){
            filters.put("pageNum",1);
        }
        if (!filters.containsKey("pageSize")){
            filters.put("pageSize",10);
        }
        PageInfo<House> page = houseService.findPage(filters);
        model.addAttribute("page",page);
        model.addAttribute("filters",filters);
        //获取所有房屋信息到请求域
        saveAllHouseMessageToRequest(model);
        return HOUSE_INDEX;
    }

    private void saveAllHouseMessageToRequest(Model model) {
        //查询小区列表
        model.addAttribute("communityList",communityService.findAll());
        //查询houseTypeList--户型
        model.addAttribute("houseTypeList",dictService.findDictListByParentDictCode("houseType"));
        //查询floorList--楼层
        model.addAttribute("floorList",dictService.findDictListByParentDictCode("floor"));
        //查询buildStructureList--建筑结构
        model.addAttribute("buildStructureList",dictService.findDictListByParentDictCode("buildStructure"));
        //查询directionList--朝向
        model.addAttribute("directionList",dictService.findDictListByParentDictCode("direction"));
        //查询decorationList--装修情况
        model.addAttribute("decorationList",dictService.findDictListByParentDictCode("decoration"));
        //查询houseUseList--房屋用途
        model.addAttribute("houseUseList",dictService.findDictListByParentDictCode("houseUse"));
    }
    /**
     * 新增房源
     */
    @RequestMapping("/create")
    public String create(Model model){
        //获取所有房源信息到请求域
        saveAllHouseMessageToRequest(model);
        return HOUSE_CREATE;
    }
    @PostMapping("/save")
    public String save(House house,Model model){
        house.setStatus(HouseStatus.UNPUBLISHED.code);
        houseService.save(house);
        model.addAttribute("house",house);
        return getPageSuccess(model,"新增成功！");
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id")Long id,Model model){
        House house = houseService.getById(id);
        model.addAttribute("house",house);
        //获取所有房源信息到请求域
        saveAllHouseMessageToRequest(model);
        return HOUSE_UPDATE;
    }
    @RequestMapping("/update")
    public String update(House house,Model model){
        houseService.update(house);
        model.addAttribute("house",house);
        return getPageSuccess(model,"修改成功！");
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        houseService.delete(id);
        return HOUSE_DELETE;
    }
    @RequestMapping("/publish/{id}/{status}")
    public String publish(@PathVariable("id")Long id,@PathVariable("status")Integer status){
        houseService.publish(id, status);
        return HOUSE_DELETE;
    }

    /**
     * 展示房源详情
     * @return
     */
    @GetMapping("/{id}")
    public String detail(@PathVariable("id")Long id,Model model){
        House house = houseService.getById(id);
        model.addAttribute("house",house);
        //查询所有小区列表
        model.addAttribute("community",communityService.getById(house.getCommunityId()));
        //查询所有房源图片列表
        model.addAttribute("houseImage1List",houseImageService.findHouseImageByHouseIdAndType(id,1));
        //查询所有房产图片列表
        model.addAttribute("houseImage2List",houseImageService.findHouseImageByHouseIdAndType(id,2));
        //查询所有经纪人列表
        model.addAttribute("houseBrokerList",houseBrokerService.findHouseBrokerByHouseId(id));
        //查询素有房东列表
        model.addAttribute("houseUserList",houseUserService.findHouseUseByHouseId(id));
        return HOUSE_DETAIL;
    }
}
