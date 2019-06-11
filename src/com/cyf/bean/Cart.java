package com.cyf.bean;

public class Cart {

    private Book book;  //书
    private Integer number; // 购买数量

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
