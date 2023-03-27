package com.atguigu.base;

import org.springframework.ui.Model;

public class BaseController<T> {
    private final static String PAGE_SUCCESS="common/successPage";
    public String getPageSuccess(Model model,String successfulMessage){
        model.addAttribute("messagePage",successfulMessage);
        return PAGE_SUCCESS;
    }
}
