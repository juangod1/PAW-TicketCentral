<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main_page>
    <jsp:attribute name="head">
        <script src="../../resources/js/main.js"></script>
    </jsp:attribute>
    <jsp:attribute name="premieres">
        <c:forEach var="premiere" items="${premieres}">
            <div class="col-md-6 col-lg-4">
                <a class="portfolio-item d-block mx-auto" href="#portfolio-modal-1">
                    <div class="portfolio-item-caption d-flex position-absolute h-100 w-100">
                        <div class="portfolio-item-caption-content my-auto w-100 text-center text-white">
                            <i class="fa fa-3x">${premiere.name}</i>
                        </div>
                    </div>
                    <img class="img-fluid" src="https://m.media-amazon.com/images/M/MV5BMTYxNDMyOTAxN15BMl5BanBnXkFtZTgwMDg1ODYzNTM@._V1_SY1000_CR0,0,674,1000_AL_.jpg" alt="">
                </a>
            </div>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="movies">
        <c:forEach var="movie" items="${movies}">
            <div class="col-md-6 col-lg-4">
                <a class="portfolio-item d-block mx-auto" href="#portfolio-modal-1">
                    <div class="portfolio-item-caption d-flex position-absolute h-100 w-100">
                        <div class="portfolio-item-caption-content my-auto w-100 text-center text-white">
                            <i class="fa fa-3x">${movie.name}</i>
                        </div>
                    </div>
                    <img class="img-fluid" src="https://m.media-amazon.com/images/M/MV5BODI4OTk1ODY3N15BMl5BanBnXkFtZTgwMDI1MTcwNjM@._V1_.jpg" alt="">
                </a>
            </div>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="food">
        <div class="col-md-6 col-lg-4">
            <a class="portfolio-item d-block mx-auto" href="#portfolio-modal-1">
                <div class="portfolio-item-caption d-flex position-absolute h-100 w-100">
                    <div class="portfolio-item-caption-content my-auto w-100 text-center text-white">
                        <i class="fa fa-search-plus fa-3x"></i>
                    </div>
                </div>
                <img class="img-fluid" src="../../resources/img/portfolio/cabin.png" alt="">
            </a>
        </div>
    </jsp:attribute>
    <jsp:attribute name="popups">
        <div class="portfolio-modal mfp-hide" id="portfolio-modal-1">
            <div class="portfolio-modal-dialog bg-white">
                <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="#">
                    <i class="fa fa-3x fa-times"></i>
                </a>
                <div class="container text-center">
                    <div class="row">
                        <div class="col-lg-6 mx-auto">
                            <h2 class="text-secondary text-uppercase mb-0" id="movie_name"></h2>
                            <hr class="star-dark mb-5">
                            <img class="img-fluid mb-5" src="../../resources/img/portfolio/cabin.png" alt="">
                            <p class="mb-5">
                                <div id="movie_genre">Genero: </div> <div class="movie_info"></div>
                                <div id="movie_release">Fecha de lanzamiento: </div><div class="movie_info"></div>
                                <div id="movie_length">Duracion: </div><div class="movie_info"></div>
                                <div id="movie_rating">Puntaje: </div><div class="movie_info"></div>
                            </p>
                        </div>
                        <div class="col-lg-6 mx-auto">
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
                            <div class="line">
                                <a class="btn btn-primary btn-lg rounded-pill portfolio-modal-dismiss" href="">
                                    <i class="fa"></i>
                                    Elegir Asientos
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</t:main_page>