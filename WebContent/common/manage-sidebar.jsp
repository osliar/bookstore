<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul>
    当前登录：${currentUser.userName}
    <li style="margin-top: 20px"><a href="/admin/addBook">新增图书</a></li>
    <li><a href="/searchBook">搜索图书</a> </li>
    <li><a href="/admin/bookList">图书列表</a></li>
    <li><a href="/admin/importExcel">批量导入图书</a></li>
    <li><a href="/logout">退出系统</a></li>
</ul>