<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: juangod
  Date: 28/08/18
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>

</head>
<body>
<div class="background">
    <!-- Title and logo -->
    <div class="banner" >
        <a href="<c:url value="/"/>" class="atlas_title">Atlas</a>
    </div>

    <div class="divisor">
        <div>
            <div class="movie_divs">
                <figure class="image_container">
                    <figcaption class="movie_title"> ${chosenMovie.name} </figcaption>
                    <img class="movie_img" src="https://static.cinemarkhoyts.com.ar/Images/Posters/d6789ff05e374a7b80a0f15aaab5abc3.jpg">
                </figure>
            </div>
            <div class="movie_divs description">
                <div class="movie_info_title">Genero: </div> <div class="movie_info">${chosenMovie.genres}</div>
                <div class="movie_info_title">Premiere: </div><div class="movie_info">${chosenMovie.premiere}</div>
                <div class="movie_info_title">Fecha de lanzamiento: </div><div class="movie_info">${chosenMovie.year}</div>
                <div class="movie_info_title">Duracion: </div><div class="movie_info">${chosenMovie.runtime}  minuto${chosenMovie.runtime<=1 ? "" : "s"}</div>
                <div class="movie_info_title">Puntaje: </div><div class="movie_info">${chosenMovie.rating}/5.0</div>
            </div>
            <div class="movie_divs description movie_buy">
                <button type="button" class="btn btn-secondary btn-lg">Comprar</button>
            </div>
        </div>
        <div>
            <!-- trailer ?? -->
        </div>
    </div>

</div>
</body>
</html>
