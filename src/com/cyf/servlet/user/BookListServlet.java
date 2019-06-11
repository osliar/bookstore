package com.cyf.servlet.user;

import com.cyf.bean.Book;
import com.cyf.bean.User;
import com.cyf.dao.BookDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/index/bookList")
public class BookListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDao dao = new BookDao();
        List<Book> bookList = dao.selectBook();
        req.setAttribute("bookList", bookList);
//        HttpSession session = req.getSession();
//        // 将数据存到session中以便于在前台获取
//       session.setAttribute("List", bookList);
//       int pageNos;
//       if (req.getParameter("pageNos") == null
//               || Integer.parseInt(req.getParameter("pageNos")) < 1) {
//           pageNos = 1;
//      } else {
//           pageNos = Integer.parseInt(req.getParameter("pageNos"));
//       }
//       session.setAttribute("pageNos", pageNos);
//       // 定义总页数并存到session中
//       int countPage = bookList.size()/15+1;
//       session.setAttribute("countPage", countPage);
        req.getRequestDispatcher("/user-index.jsp").forward(req, resp);
    }
}
