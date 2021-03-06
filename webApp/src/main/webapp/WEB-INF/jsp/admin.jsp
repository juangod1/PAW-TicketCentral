<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:generic_page>
    <jsp:attribute name="head">
        <script src="<c:url value="/resources/js/admin.js"/>"></script>
    </jsp:attribute>
    <jsp:attribute name="body">
        <div>
            <span><spring:message code="admin.search"/>Busque por codigo de reserva</span>
            <input id="transaction-input">
            <button id="transaction-button"><spring:message code="menu.confirm"/></button>
            <div id="transaction-div">
            </div>
        </div>
    </jsp:attribute>
</t:generic_page>
