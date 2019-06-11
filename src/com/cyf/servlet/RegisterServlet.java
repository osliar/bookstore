package com.cyf.servlet;

import com.cyf.bean.User;
import com.cyf.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPassword");
        UserDao userDao = new UserDao();
        User check = userDao.checkUserName(userName);
        if (check != null) {
            req.setAttribute("message", "该用户名已经被使用");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        } else {
            userDao.register(userName, userPassword, 1);  //注册用户
            req.setAttribute("message", "注册成功");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }
}
