package com.google.springboot.service;

import com.google.springboot.entity.POJO.Mercedes;
import com.google.springboot.entity.POJO.Tree;
import com.google.springboot.entity.ResponseResult;
import com.google.springboot.entity.beans.Person;
import com.google.springboot.entity.enums.ErrorCodeEnum;
import com.google.springboot.entity.request.OrgOperationRequest;
import com.google.springboot.entity.response.UserInfoResponse;
import com.google.springboot.interfaces.UserDAO;
import com.google.springboot.mapper.r.UserInfoRMapper;
import com.google.springboot.mapper.w.UserInfoWMapper;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
/**
 * At a high level,Spring creates proxies for all the classes annotated with @Transactional,either on the
 * class or on any of the methods.The proxy allows the framework to inject transactional logic before
 * and after the method being invoked,mainly for starting and committing the transaction.
 *
 * only public methods should be annotated with @Transactional.
 */
@Transactional
public class HomeService {
    @Autowired
    UserInfoRMapper userInfoRMapper;

    @Autowired
    UserInfoWMapper userInfoWMapper;

    /**
     *  '@Autowired is fundamentally about type-driven injection with optional semantic qualifiers,
     *  the bean's id(name) will be used as a fallback,this is the case when you use @Qualifier("beanName")
     *
     *  '@Inject @YourQualifier private Foo foo()
     *
     *  '@Resource is similar to @Autowired and @Inject,but the main difference is the execution paths token to
     *  find out the required bean to inject,@Resource will narrow down the search first by name then
     *  by type and finally by Qualifiers
     */
//    @Autowired
//    UserDAO userDAO;

    @Autowired
    Person person;

    @Autowired
    Tree tree;

    @Autowired
    Mercedes mercedes;

    public ResponseResult home() {
        try {
            Class.forName("com.google.springboot");
            return new ResponseResult<>("Hello World");
        } catch (ClassNotFoundException e) {
            person.setName("will");
            return new ResponseResult<>(person.getName()+tree.getName()+mercedes.getName());
        }
    }

    public ResponseResult getUserInfo() {
        UserInfoResponse userInfoResponse =  userInfoRMapper.getUserInfo();
        if(userInfoResponse != null) {
            return new ResponseResult<>(userInfoResponse);
        }
        //return new ResponseResult<>(userDAO.getAllUserNames());
        return new ResponseResult<>("");
    }

    public ResponseResult transferOrgCrew(OrgOperationRequest request) {
        if(request.getUserIds() == null || request.getUserIds().isEmpty()) {
            return new ResponseResult<>(ErrorCodeEnum.PARAMETER_ERROR);
        }
        List<Integer> existingIds = userInfoRMapper.getExistingIds(request);
        int affectedRow = userInfoWMapper.transferOrgCrew(request);
        return new ResponseResult<>(affectedRow);
    }
}
