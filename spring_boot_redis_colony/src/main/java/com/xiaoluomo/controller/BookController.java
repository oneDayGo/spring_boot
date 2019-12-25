package com.xiaoluomo.controller;

import com.xiaoluomo.po.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/book")
    public void book(){

        ValueOperations ops = redisTemplate.opsForValue();
        Book book = new Book();
        book.setAuthor("施耐庵");
        book.setName("水浒传");

        ops.set("b1",book);

        ValueOperations<String,String> ops2 = stringRedisTemplate.opsForValue();
        ops2.set("k1","hello");
        ops2.set("k2","hello2");
        ops2.set("k3","hello3");

        System.out.println(ops.get("b1"));
    }
}
