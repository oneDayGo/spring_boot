package com.xiaoluomo.mapper;

import com.xiaoluomo.po.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> roles();
}
