package com.xiaoluomo.controller;

import com.xiaoluomo.db.Book;
import com.xiaoluomo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/save")
    public void save(){
        Book book = new Book();
        book.setAuthor("鲁迅");
        book.setName("呐喊");
        book.setPrice(23F);
        bookService.addBook(book);

    }

    //分页
    @GetMapping("/all")
    public Page<Book> all(){

        PageRequest pageRequest = PageRequest.of(0,10);
        Page<Book> page = bookService.getBookPage(pageRequest);

        return page;
    }
}
