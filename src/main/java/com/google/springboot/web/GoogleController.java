package com.google.springboot.web;

import com.google.springboot.entity.ResponseResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/google")
public class GoogleController {
    @RequestMapping(path = "/home")
    public ResponseResult googleHome(@CookieValue("cookie1") String cookie1) {
        return new ResponseResult<>("");
    }
}
