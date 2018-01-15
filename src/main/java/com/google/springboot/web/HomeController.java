package com.google.springboot.web;

import com.google.springboot.entity.ResponseResult;
import com.google.springboot.entity.request.OrgOperationRequest;
import com.google.springboot.service.HomeService;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;


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
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    HomeService homeService;

    @GetMapping("/index")
    public ResponseResult home(@RequestHeader("user-agent") String userAgent,@RequestHeader("X-AUTH-TOKEN") String token) {
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
    public ResponseResult invokeMongoDB(@RequestParam("param") String operator) {
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


    /**
     *          method annotated with @Scheduled are:
     *              a method should have void return type;
     *              a method should not accept any parameters
     *
     *          scheduled task,all in milliseconds,1/1000 seconds
     *          fixedRate:specifies the interval between method invocations measured from the start
     *                    time of each invocation,the beginning of the task execution doesn't wait for the completion
     *                    of the previous execution,this option should be used when each execution of task is independent.
     *          fixedDelay: which specifies the interval between invocations measured from the end of the last invocation and the start of the next.
     *                      the task always waits until the previous one is finished.
     *          initialDelay:number of milliseconds to delay before the first execution
     *
     *
     *          Hard coding these schedules is simple,but usually,you need to be able to control the schedule without
     *          re-compiling and re-deploying the entire app,so we make use of Spring Expressions to externalize
     *          the configuration of the tasks and store these in properties files.
     */
    @Scheduled(fixedRateString = "${fixedDelay.in.milliseconds}")
    public void reportCurrentTime(){
        System.out.println("the time is now"+dateFormat.format(new Date()));
    }

    @RequestMapping("/invokeMySQLProcedure")
    public ResponseResult invokeMySQLProcedure() {
        return homeService.invokeMySQLProcedure();
    }

    @RequestMapping(path = "/uploadFile",method = RequestMethod.POST)
    public ResponseResult uploadFile(@RequestParam("file") MultipartFile file) {

        return homeService.uploadFile(file);
    }

    @RequestMapping(path = "/poi")
    public ResponseResult poi() {
        return homeService.poi();
    }

    @RequestMapping(path = "/redisNewHash")
    public ResponseResult redisNewHash(@RequestParam("key") String id,@RequestParam("value") String name) {
        return homeService.redisNewHash(id,name);
    }

    @RequestMapping(path = "/redisHashValue")
    public ResponseResult redisHashValue(@RequestParam("key") String id) {
        return homeService.redisHashValue(id);
    }

    @RequestMapping(path = "/redisListOps")
    public ResponseResult redisListOps(@RequestParam("param") String param) {
        return homeService.redisListOps(param);
    }

    @RequestMapping(path = "/redisList")
    public ResponseResult redisList(){
        return homeService.redisList();
    }
    @RequestMapping(path = "/retrieveRedisListByIndex",method = RequestMethod.POST)
    public ResponseResult retrieveRedisListByIndex(@RequestHeader("X-MY-HEADER") String myheader,@Param("index") int index) {
        return homeService.retrieveRedisListByIndex(index);
    }

    @RequestMapping(path = "/cookie")
    public ResponseResult cookie(@CookieValue("cookie1") String cookie1, HttpServletResponse response) {
        Cookie cookie = new Cookie("cookie1",cookie1);
        cookie.setPath("/home");
        //cookie.setMaxAge(20);
        response.addCookie(cookie);
        return new ResponseResult<>("");
    }
    @RequestMapping(path = "/sendMail")
    public ResponseResult sendMain() {
        return homeService.sendMail();
    }
}
