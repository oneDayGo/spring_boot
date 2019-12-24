package com.xiaoluomo.mapper;

import com.xiaoluomo.dao.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {
    int addUser(User user);
    User getUserById(Integer id);
    List<User> getUsers();
}
