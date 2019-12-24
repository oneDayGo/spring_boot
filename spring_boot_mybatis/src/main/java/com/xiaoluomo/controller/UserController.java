package com.xiaoluomo.controller;

import com.xiaoluomo.dao.User;
import com.xiaoluomo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/add")
    public Map<String,Object> add(){
        User user = new User();
        user.setName("mybatis");
        user.setPass("123456");
        Map<String,Object> maps = new HashMap<>();
        if(this.userService.addUser(user)>=1){
            maps.put("msg","添加成功");
        }else {
            maps.put("msg","添加失败");
        }

        return maps;

    }

    @GetMapping("/users")
    public List<User> users(){
        return this.userService.getUsers();
    }
}
