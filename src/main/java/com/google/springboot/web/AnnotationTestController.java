package com.google.springboot.web;

import com.google.springboot.entity.ResponseResult;
import com.google.springboot.entity.request.OrgOperationRequest;
import com.google.springboot.entity.response.UserInfoResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/annotation", method = RequestMethod.POST)
/**
 * ' @SessionAttributes is used to store model attributes in the HTTP Servlet session between requests.
 * It is a type-level annotation that declares session attributes used by a specific controller.
 *
 * On the first request when a model attribute with the name "pet" is added to the model, it is automatically promoted to
 * and saved in the HTTP Servlet session. It remains there until another controller method uses a SessionStatus method argument to clear the storage:
 *      public String handle(SessionStatus status) {
 *          status.setComplete();
 *      }
 * when a request comes in,the first thing Spring will do is to notice @SessionAttributes("user") and then attempt to find the value of
 * "user" in javax.servlet.http.HttpSession,if it doesn't find the value,then the method with @ModelAttribute having the same name "user"
 * will be invoked.The return value from such method will be used to populate the session with name "user"
 */
@SessionAttributes("user")
public class AnnotationTestController {

    @ModelAttribute("user")
    public UserInfoResponse user() {
        UserInfoResponse user =  new UserInfoResponse();
        user.setUserId(104);
        user.setUserName("system1");
        user.setUserPWd("666666");
        return user;
    }

    @RequestMapping("/pets/{petId}")
    /**
     * when an @RequestParam annotation is declared as Map<String,String> or MultiValueMap<String,String> argument,the map is populated
     * with all the request parameters,include query parameters and form data.
     */
    /**
     *  HTTP Headers:
     *      General header: Headers applying to both requests and responses but with no relation to the data eventually transmitted in the body
     *      Request header: Headers containing more information about the resource to be fetched or about the client itself.
     *      Response header: Headers with additional information about the response, like its location or about the server itself (name and version etc.).
     *      Entity header: Headers containing more information about the body of the entity, like its content length or its MIME-type
     */
    public ResponseResult pathVariable(@PathVariable String petId,
                                       @RequestHeader Map<String,String> headers,
                                       @RequestParam Map<String,String> params,
                                       @ModelAttribute("myModel") OrgOperationRequest orgOperationRequest) {
        System.out.println("petId is " + petId);
        return new ResponseResult<>("");
    }

    /**
     * Use the @ModelAttribute annotation on a method argument to access an attribute from the model, or have it instantiated if not present.
     * On the other hand the annotation is used to define objects which should be part of a Model.
     * @param orgOperationRequest
     * @return
     */

    /**
     * the Request Payload - or to be more precise,payload body of a HTTP Request - is the data normally send by a POST or POY Request,
     * It is the part after the headers and the CRLF of a HTTP Request
     *
     * A request with Content-Type: application/json may look like this:
     *      {"foo":"bar","name":"John"}
     *
     * If you submit a HTML-Form with method="POST" and Content-Type: application/x-www-form-urlencoded or Content-Type: multipart/form-data:
     *      foo=bar&name=John
     */

    /**
     * myModel instance is resolved as follows:
     *      from the model if already added via Model Methods:
     *          @ModelAttribute
     *          public void populateModel(Model model) {
     *              mode.addAttribute(...);
     *              ...
     *          }
     *      from the HTTP session via @SessionAttributes
     *      and more...
     */
    @RequestMapping("/modelAttribute")
    public ResponseResult modelAttribute(@ModelAttribute("user") UserInfoResponse userInfoResponse) {
        return new ResponseResult<>("");
    }
}
