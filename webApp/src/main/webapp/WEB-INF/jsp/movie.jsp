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
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap-4.1.3-dist/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/movie.css"/>"/>
    <script src="<c:url value="/resources/js/jquery-3.3.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/lib.js" />"></script>
    <script src="<c:url value="/resources/js/movie.js" />"></script>
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
                <div class="movie_info_title">Fecha de lanzamiento: </div><div class="movie_info">${chosenMovie.releaseDate}</div>
                <div class="movie_info_title">Duracion: </div><div class="movie_info">${chosenMovie.runtime}  minuto${chosenMovie.runtime<=1 ? "" : "s"}</div>
                <div class="movie_info_title">Puntaje: </div><div class="movie_info">${chosenMovie.rating}/5.0</div>
            </div>
            <div class="movie_divs description">
                <div class="movie_schedule dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownSchedule" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Horarios
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                </div>

                <div class="movie_amount input-group">
                    Cantidad de entradas:<br>
                    <input type="text" id="ticketsAmount"><br>
                </div>

                <div class = "movie_buy">
                    <button type="button" class="btn btn-secondary btn-lg" id="purchase">Elegir Asientos</button>
                </div>
            </div>
        </div>
        <div>
            <!-- trailer ?? -->
        </div>
    </div>

</div>
</body>
</html>
