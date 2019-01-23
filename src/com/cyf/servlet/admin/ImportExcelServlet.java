package com.cyf.servlet.admin;


import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyf.bean.Book;
import com.cyf.dao.BookDao;
import com.cyf.util.DbUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/admin/importExcel")
public class ImportExcelServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/manage-book-import.jsp").forward(req, resp);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	BookDao dao = new BookDao();
        Connection conn = DbUtil.getConnection();
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List items = upload.parseRequest(req);
            InputStream is = null;
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (!item.isFormField()) {
                    is = item.getInputStream();
                }
            }
            //判断其兼容版本 调用了判断版本的方法
            Workbook workbook = Workbook.getWorkbook(is);
            Sheet sheet = workbook.getSheet(0);
            // 行数
            int rows = sheet.getRows();
            // 列数
            int columns = sheet.getColumns();
            String sql = "INSERT INTO book(book_name,book_sprice,book_author,book_count) VALUES (?,?,?,?)";
            String sql1 = "update book set book_name=?, book_sprice=?, book_count=?, book_author=? where book_id = ?";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement ps1 = conn.prepareStatement(sql1);

            for (int i = 0; i < rows; i++) {
                if (i == 0) {  // 第一行是属性，不读取
                    continue;
                }
                Cell ce0 = sheet.getCell(0, i);
                Cell ce1 = sheet.getCell(1, i);
                Cell ce2 = sheet.getCell(2, i);
                Cell ce3 = sheet.getCell(3, i);

                String c0 = ce0.getContents();
                String c1 = ce1.getContents();
                String c2 = ce2.getContents();
                String c3 = ce3.getContents();
                
                if(ce3.getContents()==null) {
                	c3 = "0";
                }
                List<Book> bookList = dao.dupChecking(c0, c1, c2);
//                System.out.println(c0+c1+c2+c3);
                
                if(bookList!=null) {
                	int count = Integer.parseInt(c3.trim());
//                	System.out.println(bookList.get(0).getBookCount());
                	for(int j = 0;j<bookList.size();j++) {
//                		System.out.println(bookList.get(j).getBookCount());
                		count = count+bookList.get(j).getBookCount();
//                		System.out.println(c3);
                		c3 = String.valueOf(count);
//                		System.out.println(c3);
                		ps1.setString(1, c0);
                        ps1.setString(2, c1);
                        ps1.setString(3, c3);
                        ps1.setString(4, c2);
                        ps1.setString(5, bookList.get(j).getBookId().toString());
                        ps1.execute();
//                        System.out.println(count+" "+bookList.get(j).getBookId());
                	}
                	
                }else {
                	ps.setString(1, c0);
                    ps.setString(2, c1);
                    ps.setString(3, c2);
                    ps.setString(4, c3);
                    ps.execute();
                }
                }
            ps.close();
            ps1.close();
            req.setAttribute("message", "导入成功");
            req.getRequestDispatcher("/admin/manage-book-import.jsp").forward(req, resp);
//            req.getRequestDispatcher("/admin/importExcel").forward(req, resp);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
