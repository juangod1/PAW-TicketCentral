<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:generic_page>
    <jsp:attribute name="head">
    </jsp:attribute>
    <jsp:attribute name="body">
        <form action="/login" method="post"  enctype="application/x-www-form-urlencoded">
            <div>
                <label for="username"><spring:message code="login.username"/>
                    <input id="username" name="j_username" type="text">
                </label>
            </div>
            <div>
                <label for="password"><spring:message code="login.password"/>
                    <input id="password" name="j_password" type="password">
                </label>
            </div>
            <div>
                <label> Recuérdeme
                    <input name="j_rememberme" type="checkbox">
                </label>
            </div>
            <div>
                <input type="submit" value="login">
            </div>
            <div>
                    <a class="registerLink" href="/register">Nuevo usuario? Regístrese</a>
            </div>
        </form>
    </jsp:attribute>
</t:generic_page>
