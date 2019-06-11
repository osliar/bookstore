package com.cyf.servlet.user;

import com.cyf.bean.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/totalPrice")
public class TotalPriceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");
        double totalPrice = 0;
        if (cartList == null) {
            req.setAttribute("message","您还没有选择商品哦，不需要结算");
        } else {
            for (Cart cart : cartList) {
                totalPrice += cart.getBook().getBookSprice() * cart.getNumber();  //更新结算价格
            }
        }
        req.setAttribute("totalPrice", totalPrice);
        req.getRequestDispatcher("/user/user-total.jsp").forward(req, resp);
    }
}
