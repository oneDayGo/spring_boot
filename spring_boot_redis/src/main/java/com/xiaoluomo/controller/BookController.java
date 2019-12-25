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
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/redis")
    public void redis(){
        //存字符串
        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
        ops.set("name","三国演义");


        //存对象
        ValueOperations ops2 = redisTemplate.opsForValue();

        Book book = new Book();
        book.setName("红楼梦");
        book.setAuthor("曹雪芹");
        book.setId(1);
        ops2.set("b1",book);

        //获取
        Book book2 = (Book) ops2.get("b1");
        System.out.println(book2);



    }
}
