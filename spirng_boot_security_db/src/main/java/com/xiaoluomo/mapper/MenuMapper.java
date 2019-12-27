package com.xiaoluomo.mapper;

import com.xiaoluomo.po.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<Menu> getAllMenus();
}
