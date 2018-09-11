
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%--
  Created by IntelliJ IDEA.
  User: cderienzo
  Date: 9/10/2018
  Time: 9:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/login" method="post"  enctype="application/x-www-form-urlencoded">
        <div>
            <label for="username"> Username
                <input id="username" name="j_username" type="text">
            </label>
        </div>
        <div>
            <label for="password"> Password
                <input id="password" name="j_password" type="password">
            </label>
        </div>
        <div>
            <label>
                <input name="j_rememberme" type="checkbox">
            </label>
        </div>
        <div>
            <input type="submit" value="login">
        </div>
    </form>
</body>
</html>
