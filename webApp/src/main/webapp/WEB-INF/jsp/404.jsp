<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:generic_page>
    <jsp:attribute name="head">
    </jsp:attribute>
    <jsp:attribute name="body">
        <header class="masthead bg-primary text-white text-center">
            <div class="container">
                <img class="img-fluid mb-5 d-block mx-auto" src="../../resources/img/profile_sad.png" alt="">
                <h1 class="text-uppercase mb-0">404</h1>
                <h2 class="font-weight-light mb-0"><spring:message code="error.404"/></h2>
            </div>
        </header>
    </jsp:attribute>
</t:generic_page>
