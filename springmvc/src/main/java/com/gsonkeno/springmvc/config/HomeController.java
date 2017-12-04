package com.gsonkeno.springmvc.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by gaosong on 2017-12-01
 */
@Controller //声明为一个控制器
public class HomeController {

    @RequestMapping(value = {"/","/homePage"},method = RequestMethod.GET) //处理get请求
    public String home(){
        return "home"; //视图名为home
    }
}
