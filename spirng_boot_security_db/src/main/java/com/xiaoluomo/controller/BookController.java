package com.xiaoluomo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/book/user")
    public String user(){

        return "book user";
    }

    @GetMapping("/book/users")
    public String users(){
        return "book users";
    }


}
