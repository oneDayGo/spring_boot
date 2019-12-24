package com.xiaoluomo.service;
import com.xiaoluomo.dao.User;
import com.xiaoluomo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int addUser(User user){
       return this.userMapper.addUser(user);
    }

    public List<User> getUsers(){
        return this.userMapper.getUsers();
    }



}
