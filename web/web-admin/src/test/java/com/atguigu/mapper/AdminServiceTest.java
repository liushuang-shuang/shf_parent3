package com.atguigu.mapper;

import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-service.xml")
public class AdminServiceTest {
    @Autowired
    public AdminService adminService;
    @Test
    public void test(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageNum",2);
        map.put("pageSize",10);
        PageInfo<Admin> page = adminService.findPage(map);

    }
}
