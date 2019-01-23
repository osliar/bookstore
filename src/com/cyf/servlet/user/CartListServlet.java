package com.cyf.servlet.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cyf.bean.Book;
import com.cyf.bean.Cart;
import com.cyf.dao.CartDao;

import java.io.IOException;
import java.util.List;

@WebServlet("/user/cartList")
public class CartListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("utf-8");
    	String userName = req.getParameter("userName");
    	CartDao cartDao = new CartDao();
    	HttpSession session = req.getSession();
    	List<Cart> list = cartDao.listCart(userName); 
    	req.setAttribute("cartList",list);
    	session.setAttribute("cartList", list);
    	int pageNos = 0;
        if (req.getParameter("pageNos") == null
                        || Integer.parseInt(req.getParameter("pageNos")) < 1) {
            pageNos = 1;
       } else {
            pageNos = Integer.parseInt(req.getParameter("pageNos"));
        }
        session.setAttribute("pageNos", pageNos);
        // 定义总页数并存到session中
        int countPage = 1;
        if(list!=null) {
        	
            if(list.size()%13==0) {
           	 countPage = list.size()/13;
            }else {
           	 countPage = list.size()/13+1;
            }
        }
        session.setAttribute("countPage", countPage); 
        req.getRequestDispatcher("/user/user-cart.jsp").forward(req, resp);
    }

}
