package com.google.springboot.web;


import com.google.springboot.entity.ResponseResult;
import com.google.springboot.service.AOPWithAnnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class AOPWithAnnoController {
    @Autowired
    AOPWithAnnoService aopWithAnnoService;

    @RequestMapping("/anno")
    public ResponseResult anno() {
        return aopWithAnnoService.anno();
    }
}
