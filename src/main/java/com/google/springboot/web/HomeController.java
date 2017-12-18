package com.google.springboot.web;

import com.google.springboot.entity.ResponseResult;
import com.google.springboot.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/home",method = RequestMethod.GET)
public class HomeController {
    @Autowired
    HomeService homeService;

    @RequestMapping("/index")
    public ResponseResult home() {
        return homeService.home();
    }

    @RequestMapping("/userInfo")
    public ResponseResult getUserInfo() {
        return homeService.getUserInfo();
    }
}
