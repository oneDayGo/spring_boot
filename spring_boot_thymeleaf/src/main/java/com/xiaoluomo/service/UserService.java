package com.xiaoluomo.service;

import com.xiaoluomo.jdbc.User;
import com.xiaoluomo.jdbc_dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public int addUser(User user){
        return this.userDao.addUser(user);
    }
    public User getUserById(Integer id){
        return this.userDao.getUserById(id);
    }
    public List<User> getUses(){
        return  this.userDao.getUsers();
    }
}
