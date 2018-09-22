<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:generic_page>
    <jsp:attribute name="head">
        <script src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
    </jsp:attribute>
    <jsp:attribute name="body">
        <header class="masthead bg-primary text-white text-center">
            <div class="container">
                <img class="img-fluid mb-5 d-block mx-auto" src="../../resources/img/profile_sad.png" alt="">
                <h1 class="text-uppercase mb-0">Error Interno</h1>
                <h2 class="font-weight-light mb-0">Lo sentimos! Hemos tenido un error interno.</h2>
                <h4 class="font-weight-light mb-0">Detalles técnicos:
                    <%@page pageEncoding="UTF-8" isErrorPage="true" %>
                    <ul>
                        <li>Exception: <c:out value="${requestScope['javax.servlet.error.exception']}" /></li>
                        <li>Exception type: <c:out value="${requestScope['javax.servlet.error.exception_type']}" /></li>
                        <li>Exception message: <c:out value="${requestScope['javax.servlet.error.message']}" /></li>
                        <li>Request URI: <c:out value="${requestScope['javax.servlet.error.request_uri']}" /></li>
                        <li>Servlet name: <c:out value="${requestScope['javax.servlet.error.servlet_name']}" /></li>
                        <li>Status code: <c:out value="${requestScope['javax.servlet.error.status_code']}" /></li>
                        <li>Stack trace: <pre>${pageContext.out.flush()}${exception.printStackTrace(pageContext.response.writer)}</pre></li>
                    </ul>
                </h4>
            </div>
        </header>
    </jsp:attribute>
</t:generic_page>
