package com.google.springboot.service;

import com.google.springboot.entity.ResponseResult;
import com.google.springboot.interfaces.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    public ResponseResult home() {
        try {
            Class.forName("com.google.springboot");
            return new ResponseResult<>("Hello World");
        } catch (ClassNotFoundException e) {
            return new ResponseResult<>("class not found");
        }
    }
}
