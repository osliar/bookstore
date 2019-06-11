package com.cyf.servlet.user;

import com.cyf.bean.User;
import com.cyf.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/user/updateUser")
public class UpdateUserServlet  extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doPost(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int userId = Integer.parseInt(req.getParameter("userId"));
        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPassword");
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        currentUser.setUserId((userId));
        currentUser.setUserName(userName);
        currentUser.setUserPassword(userPassword);
        UserDao userdao = new UserDao();
        boolean flag = userdao.updateUser(currentUser);
        if (flag == true){
            req.setAttribute("message","修改成功");
            session.setAttribute("currentUser", currentUser);
            req.getRequestDispatcher("/logout").forward(req,resp);
        }
        else {
            req.setAttribute("message","修改失败");
            req.getRequestDispatcher("/user/user-update.jsp").forward(req,resp);
        }
    }
}
