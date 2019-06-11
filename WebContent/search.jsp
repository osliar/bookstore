<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:choose>
        <c:when test="${currentUser.userLevelId == 0}">
            <title>购书系统 - 管理</title>
        </c:when>
        <c:otherwise>
            <title>购书系统 - 购书</title>
        </c:otherwise>
    </c:choose>
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

        input {
            margin: 15px;
        }

        .input-css {
            width: 250px;
            height: 35px;
        }

        .btn {
            width: 80px;
            height: 35px;
        }

    </style>
</head>
<body>
<div class="content">
    <c:choose>
        <c:when test="${currentUser.userLevelId == 0}">
            <h1>购书系统 - 管理</h1>
            <div class="content-left">
                <%@include file="common/manage-sidebar.jsp"%>
            </div>
        </c:when>
        <c:otherwise>
            <h1>购书系统 - 购书</h1>
            <div class="content-left">
                <%@include file="common/user-sidebar.jsp"%>
            </div>
        </c:otherwise>
    </c:choose>
    <div class="content-right">
        <form action="/searchBook" method="post">
            关键字：<input type="text" name="key" required class="input-css"/><br/>
            <button type="submit" class="btn">搜索</button>
        </form>
    </div>
</div>
</body>
</html>
