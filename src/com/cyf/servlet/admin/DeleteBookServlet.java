package com.cyf.servlet.admin;

import com.cyf.dao.BookDao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/deleteBook")
public class DeleteBookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("bookId");
        if (idStr != null && !idStr.equals("")) {
            int bookId = Integer.valueOf(idStr);
            BookDao bookDao = new BookDao();
            bookDao.deleteBook(bookId);
        }
        req.getRequestDispatcher("/admin/bookList").forward(req, resp);
    }

}
