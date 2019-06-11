<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>购书系统 - 管理</title>
    <style>
        h1 {
            text-align: center;
            border-bottom: 1px solid black;
            line-height: 100px;
        }

        .content {
            width: 1000px;
            margin-left: auto;
            margin-right: auto;
            text-align: center;
        }

        table {
            border: 1px solid black;
            margin-left: auto;
            margin-right: auto;
            width: 600px;
            text-align: center;
            width: 100%;
        }

        .content {
            width: 1000px;
            margin-left: auto;
            margin-right: auto;
        }

        .content-left {
            width: 28%;
            border: 1px solid black;
            float: left;
            min-height: 400px;
        }

        .content-right {
            width: 68%;
            border: 1px solid black;
            float: right;
            min-height: 370px;
            padding: 15px;
        }

        ul {
            text-align: left;
        }

    </style>
</head>
<body>
<div class="content">
    <h1>购书系统 - 管理</h1>
    <div class="content-left">
        <%@include file="../common/manage-sidebar.jsp"%>
    </div>
    <div class="content-right">
        ${search}
        <table>
            <thead>
                <tr>
                    <th>书名</th>
                    <th>价格</th>
                    <th>库存</th>
                    <th>作者</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${bookList}" var="book" begin="${(pageNos-1)*13 }"
        end="${pageNos*13-1}">
                    <tr>
                        <td>${book.bookName}</td>
                        <td>${book.bookSprice}</td>
                        <td>${book.bookCount}</td>
                        <td>${book.bookAuthor}</td>
                        <td>
                            <a href="/admin/updateBook?bookId=${book.bookId}">更新</a>  <!-- 调用UpdateBookServlet中的doGet方法 -->
                            <a href="/admin/deleteBook?bookId=${book.bookId}">删除</a>  <!-- get方法 -->
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <center>
    <c:if test="${pageNos>1 }"> 
 <a href="/admin/bookList?pageNos=1" >首页</a>
 <a href="/admin/bookList?pageNos=${pageNos-1}">上一页</a> 
 </c:if>
 <c:if test="${pageNos <countPage }"> 
 <a href="/admin/bookList?pageNos=${pageNos+1}">下一页</a> 
 <a href="/admin/bookList?pageNos=${countPage }">末页</a> 
 </c:if> 
<form action="/admin/bookList"> 
 <h4 align="center">共${countPage}页  
 <input type="text" value="${pageNos}" name="pageNos" size="1">页 
<input type="submit" value="go">
 </h4> 
</form> 
</center>
</div>

</body>
</html>
