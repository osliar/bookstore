package com.cyf.bean;

public class Cart {

    private Book book;  //书
    private Integer number; // 购买数量
    private Integer pay;//是否付款
    private String username;//用户名
    private String time;

	public Integer getPay() {
		return pay;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setPay(Integer pay) {
		this.pay = pay;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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
