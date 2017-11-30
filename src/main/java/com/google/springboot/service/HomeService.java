package com.google.springboot.service;

import com.google.springboot.entity.ResponseResult;
import org.springframework.stereotype.Service;

@Service
public class HomeService {
    public ResponseResult home() {
        return new ResponseResult<>("hello world");
    }
}
