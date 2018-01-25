package com.google.springboot.web;

import com.google.springboot.entity.ResponseResult;
import com.google.springboot.service.AOPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/aspect",method = RequestMethod.POST)
public class AOPController {
    @Autowired
    AOPService aopService;

    @RequestMapping("/beforeAdvice")
    public ResponseResult transfer(@RequestParam("name") String name,Map<String,String> json) {
        System.out.println("invoking transfer() method");
        return new ResponseResult<>("");
    }

    @RequestMapping("/targetAdvice")
    public ResponseResult targetAdvice() {
        System.out.println("invoking targetAdvice() method");
        return aopService.targetAdviceTest();
    }
}
