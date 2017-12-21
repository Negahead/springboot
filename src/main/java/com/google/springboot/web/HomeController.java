package com.google.springboot.web;

import com.google.springboot.entity.ResponseResult;
import com.google.springboot.entity.request.OrgOperationRequest;
import com.google.springboot.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


// The key difference between a traditional spring MVC controller and the RESTful controller
// is the way the HTTP response body is created,while the traditional MVC controller relies on the View technology
// the RESTful controller simply returns the object and the object is written directly to the HTTP
// response as JSON/XML
@RestController
@RequestMapping(path="/home")
public class HomeController {
    @Autowired
    HomeService homeService;

    @GetMapping("/index")
    public ResponseResult home() {
        return homeService.home();
    }

    @GetMapping("/userInfo")
    public ResponseResult getUserInfo() {
        return homeService.getUserInfo();
    }


    @PostMapping("/transferOrgCrew")
    /**
     *  the @RequestBody method parameter annotation indicates that a method parameter should be bound to the value of the
     *  HTTP request body,you convert the request body to the method argument by using an HttpMessageConverter,HttpMessageConverter
     *  is responsible for converting from the HTTP request message to an object and converting from an object to the HTTP response
     *  body.======>org.springframework.http.converter.HttpMessageConverter
     */
    public ResponseResult transferOrgCrew(@RequestBody OrgOperationRequest request) {
        return homeService.transferOrgCrew(request);
    }
}
