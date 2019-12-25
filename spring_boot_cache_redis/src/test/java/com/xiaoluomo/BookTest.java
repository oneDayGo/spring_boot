package com.xiaoluomo;

import com.xiaoluomo.dao.BookDao;
import com.xiaoluomo.po.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookTest {
    @Autowired
    BookDao bookDao;
    @Test
    public void book(){
        System.out.println(bookDao.getBookById(1));
        System.out.println(bookDao.getBookById(2));
        System.out.println(bookDao.getBookById2(1));
        System.out.println(bookDao.getBookById2(1));

    }
}
