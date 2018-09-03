<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic_page>
    <jsp:attribute name="head">
     <link rel="stylesheet" href="<c:url value="/resources/css/movie.css"/>"/>
     <script src="<c:url value="/resources/js/lib.js" />"></script>
    </jsp:attribute>

    <jsp:body>
    <!-- Seats -->
    <div class="divisor">
        <div class="movie_divs description">
            <div class = "movie_buy">
                <button type="button" class="btn btn-secondary btn-lg" id="purchase">Confirmar compra</button>
            </div>
        </div>
    </div>
    </jsp:body>
</t:generic_page>