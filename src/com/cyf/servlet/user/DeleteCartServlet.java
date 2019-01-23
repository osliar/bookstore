package com.cyf.servlet.user;

import com.cyf.bean.Cart;
import com.cyf.dao.CartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/deleteCart")
public class DeleteCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        CartDao cartDao = new CartDao();
        HttpSession session = req.getSession();
        Integer bookId = Integer.parseInt(req.getParameter("bookId"));
        List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");
        if(cartList!=null) {
        	for (int i = 0; i < cartList.size(); i++) {
                if (cartList.get(i).getBook().getBookId() == bookId) {
                	cartDao.deleteCart(cartList.get(i).getUsername(), cartList.get(i).getBook().getBookId());//移除表中的数据
                    cartList.remove(i);  //移除相应bookID的图书
                }
            }
        }
        
        resp.sendRedirect("/user/user-cart.jsp");
    }
}
