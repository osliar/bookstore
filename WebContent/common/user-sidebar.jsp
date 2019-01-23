<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul>
    <c:choose>
    <c:when test="${!empty currentUser}">
        当前登录：<strong>${currentUser.userName}</strong>
    </c:when>
    </c:choose>
    <li style="margin-top: 20px;"><a href="/">图书列表</a></li>
    <li><a href="/searchBook">搜索图书</a> </li>
    <c:choose>
        <c:when test="${!empty currentUser}">
            <li><a href="/user/user-update.jsp">个人中心</a></li>
            <li><a href="/user/cartList?userName=${currentUser.userName}">购物车</a></li>
            <li><a href="/user/buyHistory?userName=${currentUser.userName}">购买记录</a></li>
            <li><a href="/logout">退出系统</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="/login.jsp">登录</a></li>
            <li><a href="/register.jsp">注册</a></li>
        </c:otherwise>
    </c:choose>
    <h3>当前在线人数：${onLineCount} </h3>
</ul>
