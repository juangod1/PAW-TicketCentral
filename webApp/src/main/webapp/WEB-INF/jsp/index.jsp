<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic_page>
    <!-- Premieres -->
    <div class="divisor">
        <div class="title">
            ESTRENOS
        </div>
        <div class="images">
            <c:forEach var="premiere" items="${premieres}">
                <figure class="image_container">
                    <a href="<c:url value="/movie?movieID=${premiere.id}"/>">
                    <img class="index_img" src="https://static.cinemarkhoyts.com.ar/Images/Posters/d6789ff05e374a7b80a0f15aaab5abc3.jpg">
                    </a>
                    <figcaption> ${premiere.name} </figcaption>
                </figure>
            </c:forEach>
        </div>
    </div >

    <!-- Movies -->
    <div class="divisor">
        <div class="title">
            PELICULAS
        </div>
        <div class="images">

            <c:forEach var="movie" items="${movies}">
                <figure class="image_container">
                    <a href="<c:url value="/movie?movieID=${movie.id}"/>">
                        <img class="index_img" src="https://m.media-amazon.com/images/M/MV5BODcwZWFiNTEtNDgzMC00ZmE2LWExMzYtNzZhZDgzNDc5NDkyXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SY1000_CR0,0,654,1000_AL_.jpg">
                    </a>
                    <figcaption> ${movie.name} </figcaption>
                </figure>
            </c:forEach>
        </div>
    </div>

    <!-- Food -->
    <div class="divisor">
        <div class="title">
            COMIDA
        </div>
        <div>
            <a> aca van la morfi</a>
        </div>
    </div>
</t:generic_page>