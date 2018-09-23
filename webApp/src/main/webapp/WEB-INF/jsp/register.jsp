<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:generic_page>
    <jsp:attribute name="head">
    </jsp:attribute>
    <jsp:attribute name="body">
    <h2>Register</h2>
        <c:url value="/create" var="postPath"/>
    <form:form modelAttribute="registerForm" action="${postPath}" method="post">
        <div>
            <form:label path="name">Name: </form:label>
            <form:input type="text" path="name"/>
            <form:errors path="name" cssClass="formError" element="p"/>
        </div>
        <div>
            <form:label path="surname">Surname: </form:label>
            <form:input type="text" path="surname"/>
            <form:errors path="surname" cssClass="formError" element="p"/>
        </div>
        <div>
            <form:label path="dni">Dni: </form:label>
            <form:input type="text" path="dni"/>
            <form:errors path="dni" cssClass="formError" element="p"/>
        </div>
        <div>
            <form:label path="phone">Phone: </form:label>
            <form:input type="text" path="phone"/>
            <form:errors path="phone" cssClass="formError" element="p"/>
        </div>
        <div>
            <form:label path="email">Email: </form:label>
            <form:input type="text" path="email"/>
            <form:errors path="email" cssClass="formError" element="p"/>
        </div>
    <div>
        <form:label path="username">Username: </form:label>
        <form:input type="text" path="username"/>
        <form:errors path="username" cssClass="formError" element="p"/>
    </div>
    <div>
        <form:label path="password">Password: </form:label>
        <form:input type="password" path="password" />
        <form:errors path="password" cssClass="formError" element="p"/>
    </div>
    <div>
        <form:label path="repeatPassword">Repeat password: </form:label>
        <form:input type="password" path="repeatPassword"/>
        <form:errors path="repeatPassword" cssClass="formError" element="p"/>
    </div>
    <div>
        <input type="submit" value="Register!"/>
    </div>
</form:form>
    </jsp:attribute>
</t:generic_page>
