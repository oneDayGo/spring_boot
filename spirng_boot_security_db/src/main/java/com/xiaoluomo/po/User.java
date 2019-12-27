package com.xiaoluomo.po;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//继承security提供的接口
public class User implements UserDetails {

    private Integer id;
    private  String username;
    private String password;
    private Boolean enabled;
    private  Boolean locked;
    private List<Role> roles;

    /**获取当前用户对象所具有的角色信息*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
    /**获取当前用户对象密码*/
    @Override
    public String getPassword() {
        return this.password;
    }
    /**获取当前用户对象的用户名*/
    @Override
    public String getUsername() {
        return this.username;
    }
    /** 判断当前账户是否过期*/
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /**判断当前账户是否锁定*/
    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }
    /**当前密码是否过期*/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /** 当前账户是否可用*/
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
