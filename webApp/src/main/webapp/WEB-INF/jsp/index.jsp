<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style><%@include file="/WEB-INF/assets/css/style.css"%></style>


<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
    <body>
        <div class="background">
            <!-- Title and logo -->
            <div class="banner" >
                <h2>Atlas</h2>
            </div>

            <!-- Premieres -->
            <div class="divisor">
                <div class="title">
                    ESTRENOS
                </div>
                <div class="images">
                    <c:forEach var="premiere" items="${premieres}">
                        <figure class="image_container">
                            <a href="<c:url value="/movie?movieID=${premiere.id}"/>">
                            <img src="https://static.cinemarkhoyts.com.ar/Images/Posters/d6789ff05e374a7b80a0f15aaab5abc3.jpg">
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
                                <img src="https://m.media-amazon.com/images/M/MV5BODcwZWFiNTEtNDgzMC00ZmE2LWExMzYtNzZhZDgzNDc5NDkyXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SY1000_CR0,0,654,1000_AL_.jpg">
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

            <!-- COSA DE PABLO
            <table>
                <c:forEach var="show" items="${shows}">
                    <tr>
                        <td>${show}</td>
                    </tr>
                </c:forEach>
            </table>
            -->
        </div>
    </body>
</html>