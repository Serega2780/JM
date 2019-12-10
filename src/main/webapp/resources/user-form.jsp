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
        <a href="/list">List All Users</a>

    </h2>
</center>
<div align="center">
    <c:if test="${user != null}">
    <form action="/admin/update" method="post">
        </c:if>
        <c:if test="${user == null}">
        <form action="/admin/insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${user != null}">
                            Edit User
                        </c:if>
                        <c:if test="${user == null}">
                            Add New User
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${user != null}">
                    <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
                </c:if>
                <tr>
                    <th>User Name:</th>
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value="${user.name}" />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>User Password:</th>
                    <td>
                        <input type="password" name="password" size="45"
                               value="<c:out value="${user.password}" />"
                        />
                    </td>
                </tr>
                <tr>
                <tr>
                    <th>User Role:</th>
                    <td>
                        <select id="rolesList"
                                name="role"
                                multiple="multiple"
                        >
                            <c:forEach items="${user.getAuthorities()}" var="authority">
                                <option
                                        selected="selected"
                                        style="font-weight: bold;"
                                        value="<c:out value="${authority.role}" />">
                                    <c:out value="${authority.role}"/>
                                </option>
                            </c:forEach>
                            <option value=""> --</option>
                            <c:forEach items="${roles}" var="role">
                                <option
                                        value="<c:out value="${role.role}" />"
                                >
                                    <c:out value="${role.role}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>User Email:</th>
                    <td>
                        <input type="text" name="email" size="45"
                               value="<c:out value="${user.email}" />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Country:</th>

                    <td>
                        <select id="countriesList"
                                name="country"
                        >
                            <option
                                    selected="selected"
                                    style="font-weight: bold;"
                                    value="<c:out value="${user.country}"/>"
                            >
                                <c:out value="${user.country}"/>
                            </option>
                            <option value=""> --</option>
                            <c:forEach items="${countries}" var="country">
                                <option
                                        value="<c:out value="${country}"/>"
                                >
                                    <c:out value="${country}"/>
                                </option>
                            </c:forEach>

                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <button type="submit">Save</button>
                    </td>
                </tr>
            </table>
        </form>
    </form>
</div>
</body>
</html>
