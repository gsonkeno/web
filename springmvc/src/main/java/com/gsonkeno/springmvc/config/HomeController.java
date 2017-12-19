package com.gsonkeno.springmvc.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gaosong on 2017-12-01
 */
@Controller //声明为一个控制器
public class HomeController {

    @RequestMapping(value = {"/","/homePage"},method = RequestMethod.GET) //处理get请求
    public String home(HttpServletRequest request, HttpServletResponse response){
        System.out.println("request.getRemoteAddr()" + request.getRemoteAddr());
        System.out.println("request.getRemoteHost()" + request.getRemoteHost());
        System.out.println("request.getRemotePort()" + request.getRemotePort());

        return "home"; //视图名为home
    }
}
