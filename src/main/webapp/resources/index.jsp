<%--
  Created by IntelliJ IDEA.
  User: S3461968
  Date: 03.12.2019
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <body>
    <title>Login</title>
    <form action="/perform_login" method="post">
        Login:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="login"/><br/><br/>
        Password:&nbsp;&nbsp;&nbsp;<input type="password" name="password"/><br/><br/>
        <input type="submit" value="login"/>
    </form>
    </body>
</head>

</html>
