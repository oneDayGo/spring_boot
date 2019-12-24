package com.xiaoluomo.dao;

import com.xiaoluomo.db.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookDao extends JpaRepository<Book,Integer> {
    /**
     * like %ahthor%
     * */
    List<Book> getBooksByAuthorStartingWith(String author);

    /**
     *例子 price>100
     * */
    List<Book> getBooksByPriceGreaterThan(Float price);

    /**原生语句查询*/
    @Query(value = "select * from  book where id=(select max(id) from book)",nativeQuery = true) Book getMaxIdBook();

    @Query("select b from book b where b.id>:id and b.author=:author")
    List<Book> getBooksByAndAuthor(@Param("author") String author,@Param("id") Integer id);

    @Query("select  b from book b where b.id<?2 and b.name like %?1%")
    List<Book> getBooksByIdAndAndName(String name,Integer id);



}
