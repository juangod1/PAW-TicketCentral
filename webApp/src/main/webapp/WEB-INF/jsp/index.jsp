<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
    </head>
    <body>
        <h2>Hello ${greeting}!</h2>
        <table>
            <c:forEach var="show" items="${shows}">
                <tr>
                    <td>${show}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>