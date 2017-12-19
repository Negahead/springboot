package java;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
/**
 *  the @SpringBootTest annotation tells the Spring Boot to go and look for a main
 *  configuration class,and the use that to start a Spring application context
 */
@SpringBootTest
public class ApplicationTest {
    @Test
    public void test() {

    }
}
