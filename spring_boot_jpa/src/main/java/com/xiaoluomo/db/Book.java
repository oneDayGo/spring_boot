package com.xiaoluomo.db;

import javax.persistence.*;

@Entity(name="book")
public class Book {

    /**
     * 主键自动增长 GenerationType.IDENTITY
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /** 非空*/
    @Column(nullable = false)
    private String name;

    private String author;
    private Float price;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
