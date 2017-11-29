import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

// The key difference between a traditional spring MVC controller and the RESTful web service contoller
// is the way the HTTP response body is created,while the traditional MVC controller relies on the View technology
// the RESTfu; web service controller simply returns the object and the object is written directly to the HTTP
// response as JSON/XML
@RestController()
@EnableAutoConfiguration
@RequestMapping(path = "/",method = RequestMethod.POST)
public class Example {

    @RequestMapping(value = "")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }

}
