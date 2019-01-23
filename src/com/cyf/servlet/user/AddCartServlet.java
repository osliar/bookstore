package com.cyf.servlet.user;

import com.cyf.bean.Book;
import com.cyf.bean.Cart;
import com.cyf.dao.BookDao;
import com.cyf.dao.CartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user/addCart")
public class AddCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Integer bookId = Integer.parseInt(req.getParameter("bookId"));
        Integer number = Integer.parseInt(req.getParameter("number"));
        String userName = req.getParameter("userName");
        BookDao bookDao = new BookDao();
        CartDao cartDao = new CartDao();
        Book book = bookDao.getBook(bookId);
        boolean judg = cartDao.addCart(book, number, userName);
        if(!judg) {
        	System.out.println("库存不足，更新或者插入失败");
        	req.setAttribute("message", "库存不足？加入购物车失败");
        	resp.sendRedirect("/");  //重定向
        }else {
        	HttpSession session = req.getSession();
//          List<Cart> cartList =(List<Cart>) session.getAttribute("cartList");  //获取session的cartList属性的值
          List<Cart> cartList = cartDao.listCart(userName);
//          if (cartList != null) {  //购物车为空
//              cartList = new ArrayList<>();
//              Cart cart = new Cart();  //新建购物车对象
//              cart.setBook(book);
//              cart.setNumber(number);
//              cartList.add(cart);
//          } else {
//              boolean flag = false;
          if(cartList!=null) {
        	  for (int i = 0;i<cartList.size();i++) {  //遍历cartList
        		  
                  if (cartList.get(i).getBook().getBookId() == book.getBookId()) {
//                      flag = true;
//                	  System.out.println(cartList.get(i).getBook().getBookSprice());//价格输入限制测试
                      cartList.get(i).setNumber(number);  //更新购物车相同书目法的购买数量
                  }
              }
          }
//              if (!flag) {
//                  Cart cart = new Cart();  //购买购物车中没有的图书
//                  cart.setBook(book);
//                  cart.setNumber(number);
//                  cartList.add(cart);
//              }
//          }
          session.setAttribute("cartList", cartList);  //设置session的cartList属性的值为cartList
          resp.sendRedirect("/");  //重定向
        }
        
    }
}
