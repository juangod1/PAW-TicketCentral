<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="head" fragment="true" %>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap-4.1.3-dist/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <script src="<c:url value="/resources/js/jquery-3.3.1.min.js" />"></script>
    <jsp:invoke fragment="head"/>
</head>
<body>
<div class="background">
    <!-- Title and logo -->
    <div class="banner" >
        <a class="atlas_title" href="<c:url value="/"/>">Atlas</a>
    </div>

    <jsp:doBody/>

</div>
</body>
</html>