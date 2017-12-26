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

/**
 *  one thing about @ResponseBody: Spring has a list of HttpMessageConverters registered in the background,,the responsibility
 *  of HttpMessageConverter is to convert the request body to a specific class and back to the response body again.depending
 *  on a predefined mime type.
 *
 *  '@RestController = @Controller + @ResponseBody
 */
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

    /**
     * HTTP post request ,the values are sent in the request body,in the format that the content type specifies,
     * Usually the content type is application/x-www-form-urlencoded,so the request body uses the same format as the query string:
     *  parameter=value&also=another,non-alphanumeric characters are percent encoded.
     * when you use a file upload in the form,you use the multipart/form-data encoding instead,which has a different format.
     * @param operator
     * @return
     */
    @RequestMapping(value = "/mongodb",method = RequestMethod.POST)
    public ResponseResult invokeMongoDB(String operator) {
        return homeService.invokeMongoDB();
    }

    @RequestMapping(value = "/testMongo")
    public ResponseResult testMongo() {
        return homeService.testMongo();
    }

    @RequestMapping(value = "/findCollectionsByNumber/{number}",method = RequestMethod.POST)
    public ResponseResult findCollectionsByNumber(@PathVariable int number) {
        return homeService.findCollectionsByNumber(number);
    }

    @RequestMapping(value = "/mybatis")
    public ResponseResult mybatis(){
        return homeService.mybatis();
    }

    @RequestMapping("/mybatisParameter")
    public ResponseResult mybatisParameter() {
        return homeService.mybatisParameter();
    }
}
