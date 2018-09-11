<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main_page>
    <jsp:attribute name="head">
        <script src="../../resources/js/jquery-3.3.1.min.js"></script>
        <script src="../../resources/js/seatPicker.js"></script>
        <script src="../../resources/js/main.js"></script>
        <script src="../../resources/js/foodPicker.js"></script>
        <script src="../../resources/js/ReviewAndPostPurchase.js"></script>
    </jsp:attribute>
    <jsp:attribute name="premieres">
        <c:forEach var="premiere" items="${premieres}">
            <div class="col-md-6 col-lg-4">
                <a class="portfolio-item d-block mx-auto" id="movie-${premiere.id}" href="#popup-movie-${premiere.id}">
                    <div class="portfolio-item-caption d-flex position-absolute h-100 w-100">
                        <div class="portfolio-item-caption-content my-auto w-100 text-center text-white">
                            <i class="fa fa-3x">${premiere.name}</i>
                        </div>
                    </div>
                    <img class="img-fluid" src="" alt="${premiere.name}">
                </a>
            </div>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="movies">
        <c:forEach var="movie" items="${movies}">
            <div class="col-md-6 col-lg-4">
                <a class="portfolio-item d-block mx-auto" id="movie-${movie.id}" href="#popup-movie-${movie.id}">
                    <div class="portfolio-item-caption d-flex position-absolute h-100 w-100">
                        <div class="portfolio-item-caption-content my-auto w-100 text-center text-white">
                            <i class="fa fa-3x">${movie.name}</i>
                        </div>
                    </div>
                    <img class="img-fluid" src="" alt="${movie.name}">
                </a>
            </div>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="food">
        <c:forEach var="food" items="${foods}">
            <div class="col-md-6 col-lg-4">
                <a class="portfolio-item d-block mx-auto" id="food-${food.id}"><!--href="#popup-food-${food.id}"-->
                    <div class="portfolio-item-caption d-flex position-absolute h-100 w-100">
                        <div class="portfolio-item-caption0-content my-auto w-100 text-center text-white">
                            <i class="fa fa-3x">${food.name}</i>
                        </div>
                    </div>
                    <img class="img-fluid" src="https://images-na.ssl-images-amazon.com/images/I/71IFuRy1uwL._SL1229_.jpg" alt="${food.name}">
                </a>
            </div>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="popups">
        <!-- View movie popup -->
        <c:forEach var="movie" items="${movies}">
            <div class="portfolio-modal mfp-hide" id="popup-movie-${movie.id}">
                <div class="portfolio-modal-dialog bg-white">
                    <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="#" onclick="wipeData()">
                        <i class="fa fa-3x fa-times"></i>
                    </a>
                    <div class="container text-center">
                        <div class="row">
                            <div class="col-lg-6 mx-auto">
                                <h2 class="text-secondary text-uppercase mb-0" id="movie_name">${movie.name}</h2>
                                <hr class="star-dark mb-5">
                                <img class="img-fluid mb-5" src="https://m.media-amazon.com/images/M/MV5BMTYxNDMyOTAxN15BMl5BanBnXkFtZTgwMDg1ODYzNTM@._V1_SY1000_CR0,0,674,1000_AL_.jpg" alt="${movie.name}">
                            </div>
                            <div class="col-lg-6 mx-auto">
                                <div class="flex-column">
                                    <div class="col-lg-6 movie_content">
                                        <p class="p-5"></p>
                                        <div id="movie_genre">Genero: </div><div class="movie_info">${movie.genres}</div>
                                        <div id="movie_release">Fecha de lanzamiento: </div><div class="movie_info">${movie.releaseDate}</div>
                                        <div id="movie_length">Duracion: </div><div class="movie_info">${movie.runtime} minutos</div>
                                        <div id="movie_rating">Puntaje: </div><div class="movie_info">${movie.rating}/10.0</div>
                                    </div>
                                    <div class="col-lg-6 movie_content">
                                        <div class="line">
                                            <img src="<c:url value="/resources/images/movie.png"/>">
                                            <div class="name">${movie.name}</div>
                                        </div>
                                        <div class="line">
                                            <img src="<c:url value="/resources/images/calendar.png"/>">
                                            <div class="name">Fecha</div>
                                            <select id="date-movie-${movie.id}" title="date">
                                                <option value="seleccionar">Seleccionar</option>
                                            </select>
                                        </div>
                                        <div class="line">
                                            <img src="<c:url value="/resources/images/ticket.png"/>">
                                            <div class="name">Cantidad de entradas</div>
                                            <select id="amount-movie-${movie.id}" title="ticketsAmount">
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
                                            <a class="btn btn-primary btn-lg rounded-pill d-block mx-auto" onclick="checkTriggerSeatPicker(${movie.id})">
                                                <i class="fa"></i>
                                                Continuar
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        <c:forEach var="premiere" items="${premieres}">
            <div class="portfolio-modal mfp-hide" id="popup-movie-${premiere.id}">
                <div class="portfolio-modal-dialog bg-white">
                    <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="#" onclick="wipeData()">
                        <i class="fa fa-3x fa-times"></i>
                    </a>
                    <div class="container text-center">
                        <div class="row">
                            <div class="col-lg-6 mx-auto">
                                <h2 class="text-secondary text-uppercase mb-0" id="movie_name">${premiere.name}</h2>
                                <hr class="star-dark mb-5">
                                <img class="img-fluid mb-5" src="https://m.media-amazon.com/images/M/MV5BMTYxNDMyOTAxN15BMl5BanBnXkFtZTgwMDg1ODYzNTM@._V1_SY1000_CR0,0,674,1000_AL_.jpg" alt="${movie.name}">
                            </div>
                            <div class="col-lg-6 mx-auto">
                                <div class="flex-column">
                                    <div class="col-lg-6 movie_content">
                                        <p class="p-5"></p>
                                        <div id="movie_genre">Genero: </div><div class="movie_info">${premiere.genres}</div>
                                        <div id="movie_release">Fecha de lanzamiento: </div><div class="movie_info">${premiere.releaseDate}</div>
                                        <div id="movie_length">Duracion: </div><div class="movie_info">${premiere.runtime} minutos</div>
                                        <div id="movie_rating">Puntaje: </div><div class="movie_info">${premiere.rating}/10.0</div>
                                    </div>
                                    <div class="col-lg-6 movie_content">
                                        <div class="line">
                                            <img src="<c:url value="/resources/images/movie.png"/>">
                                            <div class="name">${premiere.name}</div>
                                        </div>
                                        <div class="line">
                                            <img src="<c:url value="/resources/images/calendar.png"/>">
                                            <div class="name">Fecha</div>
                                            <select id="date-movie-${premiere.id}" title="date">
                                                <option value="seleccionar">Seleccionar</option>
                                            </select>
                                        </div>
                                        <div class="line">
                                            <img src="<c:url value="/resources/images/ticket.png"/>">
                                            <div class="name">Cantidad de entradas</div>
                                            <select id="amount-movie-${premiere.id}" title="ticketsAmount">
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
                                            <a class="btn btn-primary btn-lg rounded-pill d-block mx-auto" onclick="checkTriggerSeatPicker(${premiere.id})">
                                                <i class="fa"></i>
                                                Continuar
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

        <!-- Seat picker popup -->
        <div class="portfolio-modal mfp-hide" id="seatPicker">
            <div class="portfolio-modal-dialog bg-white">
                <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="#" onclick="wipeData()">
                    <i class="fa fa-3x fa-times"></i>
                </a>
                <div class="container text-center">
                    <div class="row">
                        <div class="col-lg-6 mx-auto">
                            <h2 class="text-secondary text-uppercase mb-0" id="">Elija sus asientos</h2>
                            <hr class="star-dark mb-5">
                            <div id="seat-div"></div>
                        </div>
                        <div class="col-lg-6">
                            <a class="btn btn-primary btn-lg rounded-pill d-block mx-auto" onclick="checkConfirmSeats()">
                                <i class="fa"></i>
                                Continuar
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Buy food popup -->
        <div class="portfolio-modal mfp-hide" id="buyFood">
            <div class="portfolio-modal-dialog bg-white">
                <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="#" onclick="wipeData()">
                    <i class="fa fa-3x fa-times"></i>
                </a>
                <div class="container text-center">
                    <div class="row">
                        <div class="col-lg-6 mx-auto">
                            <h2 class="text-secondary text-uppercase mb-0" id="movie_name">Desea comer algo en la funcion?</h2>
                            <hr class="star-dark mb-5">
                        </div>
                        <div class="col-lg-6">
                            <a class="btn btn-primary btn-lg rounded-pill d-block mx-auto" onclick="checkConfirmFood()">
                                <i class="fa"></i>
                                Continuar
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Purchase review popup -->
        <div class="portfolio-modal mfp-hide" id="reviewPurchase">
            <div class="portfolio-modal-dialog bg-white">
                <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="#" onclick="wipeData()">
                    <i class="fa fa-3x fa-times"></i>
                </a>
                <div class="container text-center">
                    <div class="row">
                        <div class="col-lg-6 mx-auto">
                            <h2 class="text-secondary text-uppercase mb-0" id="movie_name">Revisión de compra</h2>
                            <hr class="star-dark mb-5">
                        </div>
                        <div class="col-lg-6">
                            <a class="btn btn-primary btn-lg rounded-pill portfolio-item d-block mx-auto" href="#postPurchase" onclick="confirmPurchase()">
                                <i class="fa"></i>
                                Confirmar compra
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Recibo -->
        <div class="portfolio-modal mfp-hide" id="postPurchase">
            <div class="portfolio-modal-dialog bg-white">
                <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="#">
                    <i class="fa fa-3x fa-times"></i>
                </a>
                <div class="container text-center">
                    <div class="row">
                        <div class="col-lg-6 mx-auto">
                            <h2 class="text-secondary text-uppercase mb-0" id="movie_name">Éxito! Aqui esta su codigo de compra.</h2>
                            <hr class="star-dark mb-5">
                        </div>
                        <div class="col-lg-6">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</t:main_page>