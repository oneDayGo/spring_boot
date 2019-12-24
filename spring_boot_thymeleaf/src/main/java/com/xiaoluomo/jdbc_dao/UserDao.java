package com.xiaoluomo.jdbc_dao;

import com.xiaoluomo.jdbc.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addUser(User user){

        String sql = "insert into user (name,pass) values(?,?)";

        return  jdbcTemplate.update(sql,user.getName(),user.getPass());
    }

    //获取一个
    public User getUserById(Integer id){
        String sql = "select * from  user where  id=?";
        return this.jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }

    //获取所有用户
    public List<User> getUsers(){

        String sql = "select * from  user";
        return this.jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class));
    }
}
