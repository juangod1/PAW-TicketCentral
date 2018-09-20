<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<body>

<h2><spring:message code="user.greeting" arguments="${user.username}"/></h2>
<h5><spring:message code="user.id" arguments="${user.id}"/></h5>
</body>
</html>
