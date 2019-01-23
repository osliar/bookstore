package com.cyf.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cyf.bean.Cart;
import com.cyf.dao.CartDao;

import java.io.IOException;
import java.util.List;

@WebServlet("/clearCart")
public class ClearCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        CartDao cartDao = new CartDao();
        List<Cart> cartList =(List<Cart>) session.getAttribute("cartList");  //获取session的cartList属性的值
        if(cartList!=null) {
        for(int i = 0;i<cartList.size();i++) {
        	cartDao.deleteCart(cartList.get(i).getUsername(), cartList.get(i).getBook().getBookId());
        }
        }
        session.removeAttribute("cartList");
        req.getRequestDispatcher("/user/user-cart.jsp").forward(req, resp);
    }
}