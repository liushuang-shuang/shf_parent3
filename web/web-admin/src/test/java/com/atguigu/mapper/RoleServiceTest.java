package com.atguigu.mapper;

import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-service.xml")
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;
    @Test
    public void testIndex(){
        List<Role> list = roleService.findAll();
        System.out.println("list = " + list);
    }
}
