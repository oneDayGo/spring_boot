package com.xiaoluomo;

import com.xiaoluomo.dao.BookDao;
import com.xiaoluomo.po.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookDaoTest {

    @Autowired
    BookDao bookDao;

    @Test
    public void book(){

        System.out.println(bookDao.getBookById(1));
        System.out.println(bookDao.getBookById(1));
        System.out.println(bookDao.getBookById(2));

    }

    @Test
    public void updateBook(){

        Book book = new Book();
        book.setName("三国演义");
        book.setId(1);
        book.setAuthor("罗贯中");

        System.out.println(bookDao.updateBookById(book));
        System.out.println(bookDao.updateBookById(book));
        System.out.println(bookDao.updateBookById(book));
    }
}
