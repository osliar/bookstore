package com.cyf.servlet.admin;

import com.cyf.bean.Book;
import com.cyf.bean.User;
import com.cyf.dao.BookDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/admin/bookList")
public class BookListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	BookDao dao = new BookDao();
        List<Book> bookList = dao.selectBook();
        HttpSession session = req.getSession();
         // 将数据存到session中以便于在前台获取
        session.setAttribute("userList", bookList);
        
        req.setAttribute("bookList", bookList);//将bookList保存在当前请求，在manage-book-index.jsp中利用EL表达式打印
        int pageNos;
                 if (req.getParameter("pageNos") == null
                                 || Integer.parseInt(req.getParameter("pageNos")) < 1) {
                     pageNos = 1;
                } else {
                     pageNos = Integer.parseInt(req.getParameter("pageNos"));
                 }
                 session.setAttribute("pageNos", pageNos);
                 // 定义总页数并存到session中
                 int countPage;
                 if(bookList.size()%13==0) {
                	 countPage = bookList.size()/13;
                 }else {
                	 countPage = bookList.size()/13+1;
                 }
                 session.setAttribute("countPage", countPage);
        req.getRequestDispatcher("/admin/manage-book-index.jsp").forward(req, resp);
    }

}
