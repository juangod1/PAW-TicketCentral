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
                    <img class="index_img" src=${premiere.img}>
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
                        <img class="index_img" src=${movie.img}>
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