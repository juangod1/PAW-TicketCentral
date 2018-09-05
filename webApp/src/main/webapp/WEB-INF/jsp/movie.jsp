<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic_page>
    <jsp:attribute name="head">
     <link rel="stylesheet" href="<c:url value="/resources/css/movie.css"/>"/>
     <script src="<c:url value="/resources/js/lib.js" />"></script>
    <script src="<c:url value="/resources/js/movie.js" />"></script>
    </jsp:attribute>
    <jsp:body>
        <div class="divisor">
            <div>
                <div class="movie_divs">
                    <figure class="image_container">
                        <figcaption class="movie_title"> ${chosenMovie.name} </figcaption>
                        <img class="movie_img" src=${chosenMovie.img}>
                    </figure>
                </div>
                <div class="movie_divs description">
                    <div class="movie_info_title">Genero: </div> <div class="movie_info">${chosenMovie.genres}</div>
                    <div class="movie_info_title">Fecha de lanzamiento: </div><div class="movie_info">${chosenMovie.releaseDate}</div>
                    <div class="movie_info_title">Duracion: </div><div class="movie_info">${chosenMovie.runtime}  minuto${chosenMovie.runtime<=1 ? "" : "s"}</div>
                    <div class="movie_info_title">Puntaje: </div><div class="movie_info">${chosenMovie.rating}/5.0</div>
                </div>
                <div class="movie_divs description">
                    <div class="line">
                        <img src="<c:url value="/resources/images/movie.png"/>">
                        <div class="name">Nombre pelicula</div>
                    </div>
                    <div class="line">
                        <img src="<c:url value="/resources/images/calendar.png"/>">
                        <div class="name">Dia</div>
                        <select title="date">
                            <option value="seleccionar">Seleccionar</option>
                            <option value="diaTal">Dia tal</option>
                        </select>
                    </div>
                    <div class="line">
                        <img src="<c:url value="/resources/images/hour.png"/>">
                        <div class="name">Horario</div>
                        <select title="hour">
                            <option value="seleccionar">Seleccionar</option>
                            <option value="horaTal">Hora tal</option>
                        </select>
                    </div>
                    <div class="line">
                        <img src="<c:url value="/resources/images/ticket.png"/>">
                        <div class="name">Cantidad de entradas</div>
                        <select id="amount" title="ticketsAmount">
                            <option value="0">0</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </div>

                <div class = "movie_buy">
                    <button type="button" class="btn btn-secondary btn-lg" id="purchase">Elegir Asientos</button>
                </div>
            </div>
        </div>
        <div>
            <!-- trailer ?? -->
        </div>
        </jsp:body>
</t:generic_page>