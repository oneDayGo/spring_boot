package com.xiaoluomo;

import com.xiaoluomo.controller.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelloControllerTest {

    @Autowired
    HelloController helloController;
    @Test
    public void hello(){

       System.out.println(helloController.hello().equals("hello"));
    }
}
