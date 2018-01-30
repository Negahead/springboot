package com.google.springboot.service;

import com.google.springboot.entity.ResponseResult;
import com.google.springboot.entity.request.OrgOperationRequest;
import com.google.springboot.interfaces.EmptyInterface;
import com.google.springboot.mapper.r.UserInfoRMapper;
import com.google.springboot.mapper.w.UserInfoWMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AOPService{
    @Autowired
    UserInfoRMapper userInfoRMapper;

    @Autowired
    UserInfoWMapper userInfoWMapper;


    public ResponseResult targetAdviceTest() {
        System.out.println("target advice test...................");
        return new ResponseResult<>("");
    }

    public ResponseResult aroundAdvice(String name) {
        return new ResponseResult<>("");
    }

    public ResponseResult parameterTest(OrgOperationRequest request, String name) {
        System.out.println("invoking request test..........");
        return new ResponseResult<>("");
    }

    public ResponseResult throwingException(OrgOperationRequest request, String name) {
        if(! "Java".equals(name)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return new ResponseResult<>("");
    }
}
