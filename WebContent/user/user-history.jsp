<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>购书系统 - 购书</title>
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
		
		.clean{
		float:right;
		}
		
        ul {
            text-align: left;
        }

    </style>
    <link rel="stylesheet" type="text/css" href="http://localhost:8080//css/index.css">
</head>
<body>
<div class="content">
    <h1>购书系统 - 购书</h1>
    <div class="content-left">
        <%@include file="../common/user-sidebar.jsp"%>
    </div>
    <div class="content-right">
        <c:choose>
            <c:when test="${empty cartList}">
                <h3 class="text-center">还没买过东西呢</h3>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                    <tr>
                        <th>书名</th>
                        <th>价格</th>
                        <th>作者</th>
                        <th>购买数量</th>
                        <th>购买时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cartList}" var="cart" varStatus="status" begin="${(pageNos-1)*13 }"
        end="${pageNos*13-1}">
                        <tr>
                            <td>${cart.book.bookName}</td>
                            <td>${cart.book.bookSprice}</td>
                            <td>${cart.book.bookAuthor}</td>
                            <td>${cart.number}</td>
                            <td>${cart.time}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
      <div class = "page">
    <c:if test="${pageNos>1 }"> 
 <a href="/user/buyHistory?userName=${currentUser.userName}&pageNos=1" >首页</a>
 <a href="/user/buyHistory?userName=${currentUser.userName}&pageNos=${pageNos-1}">上一页</a> 
 </c:if>
 <c:if test="${pageNos <countPage }"> 
 <a href="/user/buyHistory?userName=${currentUser.userName}&pageNos=${pageNos+1}">下一页</a> 
 <a href="/user/buyHistory?userName=${currentUser.userName}&pageNos=${countPage }">末页</a> 
 </c:if> 
 </div>
 
<form action="/user/buyHistory?userName=${currentUser.userName}"> 
 <h4 align="center">共${countPage}页  
<!--  传值当前页码跟用户名 -->
 <input type="hidden" value="${currentUser.userName}" name="userName"> 
 <input type="text" value="${pageNos}" name="pageNos" size="1">页 
<input type="submit" value="go">
 </h4> 
</form> 
</div>
</body>
</html>
