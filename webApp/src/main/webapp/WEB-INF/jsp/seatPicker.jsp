<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:generic_page>
    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/resources/css/movie.css"/>"/>
        <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
        <link rel="stylesheet" href="<c:url value="/resources/css/seatPicker.css"/>"/>
        <script src="<c:url value="/resources/js/lib.js" />"></script>
        <script src="<c:url value="/resources/js/seatPicker.js" />"></script>
    </jsp:attribute>

    <jsp:body>
        <!-- Seats -->
        <div class="divisor">
            <div id="seats" class="seatPickerDivisor movie_divs description">
                <c:forEach begin="1" end="${seatRowsMax}" varStatus="row">
                    <div class="row">
                        <c:forEach begin="1" end="${seatColumnsMax}" varStatus="col">
                            <div class="col">
                                <div class="btn-group-toggle" data-toggle="buttons">
                                                                      <!--    Hashing function N**2->N   -->
                                        <c:set var="hash" value="${(row.index+col.index+1)*(row.index+col.index)/2 + col.index}"/>
                                        <c:set var="hashKey">${hash.intValue()}</c:set>
                                        <c:choose>
                                            <c:when test="${empty seats[hashKey]}">
                                                e
                                            </c:when>
                                            <c:otherwise>
                                                <c:choose>
                                                    <c:when test="${seats[hashKey].occupied}">
                                                        <label class="btn btn-secondary active">
                                                        <input type="checkbox" id="${row.index}-${col.index}" checked autocomplete="off">
                                                        o
                                                    </c:when>
                                                    <c:otherwise>
                                                        <label class="btn btn-secondary active">
                                                        <input type="checkbox" id="${row.index}-${col.index}" checked autocomplete="off">
                                                        u
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>
                                    </label>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
            <div class="seatPickerDivisor movie_divs description">
                <div class = "movie_buy">
                    <button type="button" class="btn btn-secondary btn-lg" id="purchase">Revision de compra</button>
                </div>
            </div>
        </div >

        <!-- Food -->
        <div class="divisor">
            <div class="title">
                COMIDA
            </div>
            <div>
                <a> aca van la morfi</a>
            </div>
        </div>
    </jsp:body>
</t:generic_page>