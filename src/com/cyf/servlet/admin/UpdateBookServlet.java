package com.cyf.servlet.admin;

import com.cyf.bean.Book;
import com.cyf.dao.BookDao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/updateBook")
public class UpdateBookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {   // 查询到选中ID的值所对应的数据
        String bookId = req.getParameter("bookId");
        if (bookId != null && !bookId.equals("")) {
            int sid = Integer.valueOf(bookId);
            BookDao dao = new BookDao();
            Book book = dao.getBook(sid);
            req.setAttribute("book", book);
        }
        req.getRequestDispatcher("/admin/manage-book-update.jsp").forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        String bookName = req.getParameter("bookName");
        String bookSprice = req.getParameter("bookSprice");
        String bookCount = req.getParameter("bookCount");
        String bookAuthor = req.getParameter("bookAuthor");
        Book book = new Book();
        book.setBookId(bookId);
        book.setBookName(bookName);
        book.setBookSprice(Double.valueOf(bookSprice));
        book.setBookCount(Integer.valueOf(bookCount));
        book.setBookAuthor(bookAuthor);
        BookDao dao = new BookDao();
        dao.updateBook(book);
        if(dao.dupChecking(book.getBookName(), "0", book.getBookAuthor())!=null) {
        	req.setAttribute("message", "图书已存在！");
        	req.getRequestDispatcher("/admin/manage-book-update.jsp").forward(req, resp);
        }else {
        req.getRequestDispatcher("/admin/bookList").forward(req, resp);}
    }
}