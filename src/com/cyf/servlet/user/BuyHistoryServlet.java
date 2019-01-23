package com.cyf.servlet.user;

import com.cyf.bean.Book;
import com.cyf.bean.Cart;
import com.cyf.bean.User;
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

@WebServlet("/user/buyHistory")
public class BuyHistoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartDao cartDao = new CartDao();
        String userName = req.getParameter("userName");
//        HttpSession session = req.getSession();
        List<Cart> cartList = cartDao.buyHistory(userName);
        HttpSession session = req.getSession();
        // 将数据存到session中以便于在前台获取
      session.setAttribute("cartList", cartList);
      req.setAttribute("cartList", cartList);//将cartList保存在当前请求，在jsp中利用EL表达式打印
       int pageNos = 2;
                if (req.getParameter("pageNos") == null
                                || Integer.parseInt(req.getParameter("pageNos")) < 1) {
                    pageNos = 1;
               } else {
                    pageNos = Integer.parseInt(req.getParameter("pageNos"));
                }
                session.setAttribute("pageNos", pageNos);
                // 定义总页数并存到session中
                int countPage = 1;
                if(cartList!=null) {
                	
                    if(cartList.size()%13==0) {
                   	 countPage = cartList.size()/13;
                    }else {
                   	 countPage = cartList.size()/13+1;
                    }
                }
                session.setAttribute("countPage", countPage); 
        req.getRequestDispatcher("/user/user-history.jsp").forward(req, resp);
    }
}
