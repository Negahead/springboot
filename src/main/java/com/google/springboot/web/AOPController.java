package com.google.springboot.web;

import com.google.springboot.entity.ResponseResult;
import com.google.springboot.entity.request.OrgOperationRequest;
import com.google.springboot.interfaces.EmptyInterface;
import com.google.springboot.service.AOPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/aspect",method = RequestMethod.POST)
public class AOPController implements EmptyInterface{
    @Autowired
    AOPService aopService;

    @RequestMapping("/beforeAdvice")
    public ResponseResult transfer(@RequestParam("name") String name,Map<String,String> json) {
        System.out.println("invoking transfer() method");
        return new ResponseResult<>("");
    }

    @RequestMapping("/targetAdvice")
    public ResponseResult targetAdvice(@RequestParam("name") String name) {
        System.out.println("invoking targetAdvice() method");
        return aopService.targetAdviceTest();
    }

    @RequestMapping("/aroundAdvice")
    public ResponseResult aroundAdvice(@RequestParam("name") String name) {
        System.out.println("invoking aroundAdvice() method");
        return aopService.aroundAdvice(name);
    }

    @RequestMapping("/parameterTest")
    public ResponseResult parameterTest(@RequestBody  OrgOperationRequest request, @RequestParam("name") String name) {
        return aopService.parameterTest(request,name);
    }

    @RequestMapping("/throwingException")
    public ResponseResult throwingException(@RequestBody  OrgOperationRequest request, @RequestParam("name") String name) {
        return aopService.throwingException(request,name);
    }
}
