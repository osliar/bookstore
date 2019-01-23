<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
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
		.page{
			width: 1000px;
            margin-left: auto;
            margin-right: auto;
            text-align: center;
		}
        ul {
            text-align: left;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
<div class="content">
    <h1>购书系统 - 购书</h1>
    <div class="content-left">
        <%@include file="common/user-sidebar.jsp"%>
    </div>
    <div class="content-right">
        ${search}
        <span style="color: red;">${message}</span><br/>
        <table>
            <thead>
            <tr>
                <th>书名</th>
                <th>价格</th>
                <th>库存</th>
                <th>作者</th>
                <c:choose>
                    <c:when test="${!empty currentUser}">
                        <th>购买数量</th>
                        <th>操作</th>
                    </c:when>
                </c:choose>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${bookList}" var="book" varStatus="status" begin="${(pageNos-1)*13 }"
        end="${pageNos*13-1}">
                <tr>
                    <td>${book.bookName}</td>
                    <td>${book.bookSprice}</td>
                    <td>${book.bookCount}</td>
                    <td>${book.bookAuthor}</td>
                    <c:choose>
                        <c:when test="${!empty currentUser}">
                            <td><input type="number" class="bookNumber" value="1" min="1" ></td>
                            <td>
                                <a href="javascript:;" onclick="addCartFun('${book.bookId}',${status.index},'${currentUser.userName }')">加入购物车</a>
                            </td>
                        </c:when>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class = "page">
    <c:if test="${pageNos>1 }"> 
 <a href="?pageNos=1" >首页</a>
 <a href="?pageNos=${pageNos-1}">上一页</a> 
 </c:if>
 <c:if test="${pageNos <countPage }"> 
 <a href="?pageNos=${pageNos+1}">下一页</a> 
 <a href="?pageNos=${countPage }">末页</a> 
 </c:if> 
 </div>
    

<form action=""> 
 <h4 align="center">共${countPage}页  
 <input type="text" value="${pageNos}" name="pageNos" size="1">页 
<input type="submit" value="go">
 </h4> 
</form> 

</div>
 
<script>
    function addCartFun(bookId,ind,userName) {
        var number = document.getElementsByClassName("bookNumber")[ind].value;
        var regu = "^[0-9]+$";
        var re = new RegExp(regu);
        if (number.search(re) != -1) {
        	window.location.href = "/user/addCart?bookId=" + bookId + "&number=" + number + "&userName=" + userName;
        	} else {
        		alert("请正确输入数量");
        	}
    }
</script>

</body>
</html>

