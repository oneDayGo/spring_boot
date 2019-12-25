package com.xiaoluomo.dao;

import com.xiaoluomo.po.Book;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository

public class BookDao {


    /** 默认key方法的参数名*/
    @Cacheable(value = "c1")
    public Book getBookById(Integer id){
        System.out.println("byId");
        Book book = new Book();
        book.setId(id);
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        return book;
    }

    @Cacheable(value = "c3")
    public Book getBookById2(Integer id){
        System.out.println("byId");
        Book book = new Book();
        book.setId(id);
        book.setName("三国");
        book.setAuthor("罗贯中");
        return book;
    }


    @CachePut(value = "c1")
    public Book updateBookById(Book book){

        //key为当前id的值
        //缓存只对查询有用,如果数据更新了会自动缓存

        System.out.println("upid");
        book.setName("三国演义2");
        return book;
    }


}
