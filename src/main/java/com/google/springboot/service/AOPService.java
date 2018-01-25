package com.google.springboot.service;

import com.google.springboot.entity.ResponseResult;
import com.google.springboot.mapper.r.UserInfoRMapper;
import com.google.springboot.mapper.w.UserInfoWMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AOPService {
    @Autowired
    UserInfoRMapper userInfoRMapper;

    @Autowired
    UserInfoWMapper userInfoWMapper;


    public ResponseResult targetAdviceTest() {
        System.out.println("target advice test...................");
        return new ResponseResult<>("");
    }
}
