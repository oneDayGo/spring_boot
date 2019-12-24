package com.xiaoluomo.controller;

import com.xiaoluomo.jdbc.User;
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
    private UserService userService;

    @GetMapping(value = "/05add",produces = "application/json")
    public Map<String,Object> user(){
        User user = new User();
        user.setName("boot");
        user.setPass("password");
        Map<String,Object> maps = new HashMap<>();
        if(this.userService.addUser(user) >= 1){
            maps.put("message","添加成功");
            maps.put("status","10000");
        }else {
            maps.put("message","添加失败");
            maps.put("status","10001");
        }

        return maps;

    }

    @GetMapping(value = "/05user",produces = "application/json")
    public User user(Integer id){
        return this.userService.getUserById(id);
    }

    @GetMapping(value = "/05users",produces = "application/json")
    public List<User> users(){
        return this.userService.getUses();
    }
}
