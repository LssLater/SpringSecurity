package com.yu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: yy
 * @Date: 2020/11/29 14:40
 * @Version: 1.0.0
 */
@Controller
public class MainController {

    /**
     * 首页
     * @return index.html
     */
    @RequestMapping({"/index","/",""})
    public String index(){
        return "index";
    }
}
