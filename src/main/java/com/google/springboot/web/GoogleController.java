package com.google.springboot.web;

import com.google.springboot.configuration.ClusterProperties;
import com.google.springboot.entity.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/google")
public class GoogleController {
    @Autowired
    ClusterProperties clusterProperties;


    @RequestMapping(path = "/home")
    public ResponseResult googleHome(@CookieValue("cookie1") String cookie1) {
        return new ResponseResult<>("");
    }
}
