package com.xiaoluomo.service;

import com.xiaoluomo.dao.BookDao;
import com.xiaoluomo.db.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public void addBook(Book book){

        this.bookDao.save(book);
    }

    public Page<Book> getBookPage(Pageable pageable){
        return this.bookDao.findAll(pageable);
    }

    public List<Book> getBooksByAuthorStartingWith(String author){
        return this.bookDao.getBooksByAuthorStartingWith(author);
    }

    public List<Book> getBooksByPriceGreaterThan(Float price){
        return this.bookDao.getBooksByPriceGreaterThan(price);
    }

    public Book getMaxIdBook(){
        return this.bookDao.getMaxIdBook();
    }

    public List<Book> getBookByIdAndAuthor(String author,Integer id){
        return this.bookDao.getBooksByAndAuthor(author,id);
    }

    public List<Book> getBooksByIdAndName(String name,Integer id){
        return this.bookDao.getBooksByIdAndAndName(name, id);
    }
}
