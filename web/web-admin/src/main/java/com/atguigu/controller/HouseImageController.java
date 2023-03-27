package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.HouseImage;
import com.atguigu.result.Result;
import com.atguigu.service.HouseImageService;
import com.atguigu.util.FileUtil;
import com.atguigu.util.QiniuUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/houseImage")
public class HouseImageController {
    @Reference
    private HouseImageService houseImageService;

    private static final String HOUSE_IMAGE_UPLOAD = "house/upload";
    private static final String HOUSE_IMAGE_DETAIL = "redirect:/house/";

    @RequestMapping("/uploadShow/{houseId}/{type}")//上传房源照片
    public String uploadOne(@PathVariable("houseId")Long houseId,
                            @PathVariable("type")Integer type, Model model){
        model.addAttribute("houseId",houseId);
        model.addAttribute("type",type);
        return HOUSE_IMAGE_UPLOAD;
    }

    /**
     * 上传文件到七牛云
     * 将上传的文件保存到数据库
     * @return
     */
    @ResponseBody
    @RequestMapping("/upload/{houseId}/{type}")
    public Result upload(@PathVariable("houseId")Long houseId,
                         @PathVariable("type")Integer type,
                         @RequestParam("file") MultipartFile[] files, Model model) throws IOException {
        for (MultipartFile multipartFile : files) {
            //1. 将文件上传到七牛云
            //1.1 获取文件名
            String fileName = multipartFile.getOriginalFilename();
            //1.2 生成唯一的文件名
            String uuidName = FileUtil.getUUIDName(fileName);
            //1.3 上传文件
            QiniuUtils.upload2Qiniu(multipartFile.getBytes(), uuidName);

            //2. 将文件的url保存到数据库
            //2.1 拼接图片的url
            String url = QiniuUtils.getUrl(uuidName);
            //2.2 创建HouseImage对象
            HouseImage houseImage = new HouseImage();
            houseImage.setImageName(uuidName);
            houseImage.setHouseId(houseId);
            houseImage.setType(type);
            houseImage.setImageUrl(url);
            //2.3 保存到数据库
            houseImageService.save(houseImage);
        }
        return Result.ok();
    }
    @RequestMapping("/delete/{houseId}/{id}")
    public String delete(@PathVariable("houseId")Long houseId,@PathVariable("id")Long id){
        houseImageService.delete(id);
        return HOUSE_IMAGE_DETAIL+houseId;
    }
}
