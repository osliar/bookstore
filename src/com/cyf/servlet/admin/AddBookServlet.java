package com.cyf.servlet.admin;

import com.cyf.bean.Book;
import com.cyf.dao.BookDao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/addBook")
public class AddBookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/manage-book-add.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String bookName = req.getParameter("bookName");
        String bookSprice = req.getParameter("bookSprice");
        String bookCount = req.getParameter("bookCount");
        String bookAuthor = req.getParameter("bookAuthor");
        Book book = new Book();
        book.setBookName(bookName);
        book.setBookSprice(Double.valueOf(bookSprice));
        book.setBookCount(Integer.valueOf(bookCount));
        book.setBookAuthor(bookAuthor);
        BookDao bookDao = new BookDao();
        bookDao.addBook(book);
        req.getRequestDispatcher("/admin/bookList").forward(req, resp);
    }
}
