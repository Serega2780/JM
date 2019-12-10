<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 03.12.2019
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="/admin/new">Add New User</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/admin/list">List All Users</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Role</th>
            <th>Email</th>
            <th>Country</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${listUser}" var="user" >
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>
				    <c:forEach items="${user.getAuthorities()}" var="role">
					    <c:out value="${role.getRole()}"/><br>
                    </c:forEach>
                </td>

                <td>${user.email}</td>
                <td>${user.country}</td>
                <td>
                    <a href="/admin/edit?id=<c:out value="${user.id}" />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/admin/delete?id=<c:out value="${user.id}" />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
