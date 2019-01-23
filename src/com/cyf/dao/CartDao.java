package com.cyf.dao;

import com.cyf.bean.Book;
import com.cyf.bean.Cart;
import com.cyf.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class CartDao {
	/**
     * 购物车列表
     *
     * @param userName
     * @return
     */
	public List<Cart> listCart(String userName){
		List<Cart> list = new ArrayList<>();
		Connection conn = DbUtil.getConnection();
		String sql = "select * from cart where user_name = ? and if_pay = 0";
		try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, userName);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                Cart cart = new Cart();
                Book book = new Book();
                book.setBookId(rst.getInt("book_id"));
                book.setBookName(rst.getString("book_name"));
                book.setBookSprice(rst.getDouble("book_sprice"));
                book.setBookCount(rst.getInt("book_count"));
                book.setBookAuthor(rst.getString("book_author"));
                cart.setBook(book);
                cart.setNumber(rst.getInt("number"));
                cart.setUsername(rst.getString("user_name"));
                list.add(cart);
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		if(list.isEmpty()) {
			return null;
		}else {
			return list;
		}
		
	}
	/**
     * 历史记录
     *
     * @param userName
     * @return
     */
	public List<Cart> buyHistory(String userName){
		List<Cart> list = new ArrayList<>();
		Connection conn = DbUtil.getConnection();
		String sql = "select * from cart where user_name = ? and if_pay = 1";
		try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, userName);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                Cart cart = new Cart();
                Book book = new Book();
                book.setBookId(rst.getInt("book_id"));
                book.setBookName(rst.getString("book_name"));
                book.setBookSprice(rst.getDouble("book_sprice"));
                book.setBookCount(rst.getInt("book_count"));
                book.setBookAuthor(rst.getString("book_author"));
                cart.setBook(book);
                cart.setNumber(rst.getInt("number"));
                cart.setUsername(rst.getString("user_name"));
                cart.setTime(rst.getString("time"));
                list.add(cart);
            }
            rst.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		if(list.isEmpty()) {
			return null;
		}else {
			return list;
		}
		
	}
	/**
     * 删除商品
     *
     * @param userName bookId
     * @return
     */
	 public boolean deleteCart(String userName,int bookId) {
	        String sql = "delete from cart where book_id = ? and user_name = ? and if_pay = 0";
	        Connection conn = DbUtil.getConnection();
	        try {
	            PreparedStatement pst = conn.prepareStatement(sql);
	            pst.setInt(1, bookId);
	            pst.setString(2, userName);
	            int count = pst.executeUpdate();
	            pst.close();
	            return count > 0 ? true : false;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	 /**
	     * 添加商品
	     *
	     * @param book number username
	     * @return
	     */
	 public boolean addCart(Book book, int number, String userName) {
		 if(number>book.getBookCount()) {
			 return false;
		 }
		 String searsql = "select * from cart where user_name = ? and book_id = ? and if_pay = 0";
		 String sql = "INSERT INTO cart(user_name,book_id,book_name,book_sprice,book_author,book_count,if_pay,number) VALUES (?,?,?,?,?,?,?,?)";
		 String sql1 = "update cart set number = ? where book_id = ?";
		 Connection conn = DbUtil.getConnection();
		 try {
			 PreparedStatement pst1 = conn.prepareStatement(searsql);
			 pst1.setString(1, userName);
			 pst1.setInt(2, book.getBookId());
			 ResultSet rst = pst1.executeQuery();
			 if(rst.next()) {
				 PreparedStatement pst = conn.prepareStatement(sql1);
				 pst.setInt(1, number);
				 pst.setInt(2, book.getBookId());
				 int count = pst.executeUpdate();
				 	pst.close();
		            pst1.close();
		            rst.close();
		            return count > 0 ? true : false;
			 }else {
				 PreparedStatement pst = conn.prepareStatement(sql);
				 pst.setString(1, userName);
				 pst.setInt(2, book.getBookId());
				 pst.setString(3, book.getBookName());
				 pst.setDouble(4, book.getBookSprice());
				 pst.setString(5, book.getBookAuthor());
				 pst.setInt(6, book.getBookCount());
				 pst.setInt(7, 0);
				 pst.setInt(8, number);
				 int count = pst.executeUpdate();
		            pst.close();
		            pst1.close();
		            rst.close();
		            return count > 0 ? true : false;
			 }
		 }catch (SQLException e) {
	            e.printStackTrace();
	        }
		 return false;
	 }
	 /**
	     * 结算
	     *
	     * @param bookId bookCount userName
	     * @return
	     */
	 public boolean total(int bookId, int bookCount, String userName) {
	    	String sql = "update cart set if_pay = 1,time = ? where book_id = ? and user_name = ?";
	    	String sql1 = "update book set book_count = ? where book_id = ?";
	    	Date day=new Date();    
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String time = df.format(day); 
	    	Connection conn = DbUtil.getConnection();
     	try {
				PreparedStatement pst = conn.prepareStatement(sql);
				PreparedStatement pst1 = conn.prepareStatement(sql1);
				
				pst.setString(1, time);
				pst.setInt(2, bookId);
				pst.setString(3, userName);
				pst1.setInt(1, bookCount);
				pst1.setInt(2, bookId);
				
				pst.executeUpdate();
				pst1.executeUpdate();
				
				pst.close();
				pst1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return false;
	 }
}
