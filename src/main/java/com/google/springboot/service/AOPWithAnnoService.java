package com.google.springboot.service;

import com.google.springboot.entity.ResponseResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AOPWithAnnoService {

    public ResponseResult anno() {
        return new ResponseResult<>("");
    }
}
