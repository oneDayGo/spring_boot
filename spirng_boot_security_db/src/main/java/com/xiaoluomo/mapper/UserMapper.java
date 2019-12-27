package com.xiaoluomo.mapper;

import com.xiaoluomo.po.Role;
import com.xiaoluomo.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User loadUserByUsername(String username);
    List<Role> getUserRolesByUid(Integer id);
}


