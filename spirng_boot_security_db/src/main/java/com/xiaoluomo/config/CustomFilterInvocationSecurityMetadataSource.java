package com.xiaoluomo.config;

import com.xiaoluomo.mapper.MenuMapper;
import com.xiaoluomo.po.Menu;
import com.xiaoluomo.po.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/** 动态权限配置　　第一步*/

@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {


    /** 实现and　url风格匹配*/
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    MenuMapper menuMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        /**
         * 可以在这里验证前后台登陆
         * */

        /** 获取当前请求url*/
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        /** 从数据库获取资源*/

        System.out.println(requestUrl);

        List<Menu> allMenus = menuMapper.getAllMenus();
        for(Menu menu:allMenus){

            if(antPathMatcher.match(menu.getPattern(),requestUrl)){

                List<Role> roles = menu.getRoles();
                String[] roleArr = new String[roles.size()];

                for(int i=0;i<roleArr.length;i++){
                    roleArr[i] = roles.get(i).getName();
                }

                return SecurityConfig.createList(roleArr);
            }

        }
        /** 如果当前请求不在url资源表中就返回ROLE_LOGIN*/
        System.out.println("okkkkkk");
        return SecurityConfig.createList("ROLE_LOGIN");

    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
