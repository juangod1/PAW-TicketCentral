<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main_page>
    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/resources/css/loading.css"/>"/>
    <link href="<c:url value="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"/>" rel="stylesheet" type="text/css">
      <script src="<c:url value="/resources/js/seatPicker.js"/>"></script>
      <script src="<c:url value="/resources/js/util.js"/>"></script>
      <script src="<c:url value="/resources/js/main.js"/>"></script>
      <script src="<c:url value="/resources/js/userProfile.js"/>"></script>
      <script src="<c:url value="/resources/js/foodPicker.js"/>"></script>
      <script src="<c:url value="/resources/js/ReviewAndPostPurchase.js"/>"></script>
    </jsp:attribute>
    <jsp:attribute name="premieres">
        <c:choose>
            <c:when test="${premieres.size()>'0'}">
                <c:forEach var="premiere" items="${premieres}">
                    <div class="col-md-6 col-lg-4">
                        <a class="portfolio-item d-block mx-auto" id="movie-${premiere.id}" href="#popup-movie" onclick="setupMoviePopup(${premiere.id})">
                            <div class="portfolio-item-caption d-flex position-absolute h-100 w-100">
                                <div class="portfolio-item-caption-content my-auto w-100 text-center text-white">
                                    <i class="fa fa-3x">${premiere.name}</i>
                                </div>
                            </div>
                            <img class="img-fluid" src="" alt="${premiere.name}">
                        </a>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="col-xs-12">
                    <h2 class="font-weight-light mb-0"><spring:message code="movies.notfound"/></h2>
                </div>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
    <jsp:attribute name="movies">
        <c:choose>
            <c:when test="${movies.size()>'0'}">
                <c:forEach var="movie" items="${movies}">
                    <div class="col-md-6 col-lg-4">
                        <a class="portfolio-item d-block mx-auto" id="movie-${movie.id}" href="#popup-movie" onclick="setupMoviePopup(${movie.id})">
                            <div class="portfolio-item-caption d-flex position-absolute h-100 w-100">
                                <div class="portfolio-item-caption-content my-auto w-100 text-center text-white">
                                    <i class="fa fa-3x">${movie.name}</i>
                                </div>
                            </div>
                            <img class="img-fluid" src="" alt="${movie.name}">
                        </a>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="col-xs-12">
                    <h2 class="font-weight-light mb-0" ><spring:message code="movies.notfound"/></h2>
                </div>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
    <jsp:attribute name="food">
        <c:choose>
            <c:when test="${foods.size()>'0'}">
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
            </c:when>
            <c:otherwise>
                <div class="col-xs-12">
                    <h2 class="font-weight-light mb-0"><spring:message code="food.notfound"/></h2>
                </div>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
    <jsp:attribute name="popups">
        <!-- View movie popup -->
        <div class="portfolio-modal mfp-hide" id="popup-movie">
            <div class="portfolio-modal-dialog bg-white">
                <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="#" onclick="wipeData()">
                    <i class="fa fa-3x fa-times"></i>
                </a>
                <div class="container text-center">
                    <div class="row">
                        <div class="col-lg-6 mx-auto">
                            <h2 class="text-secondary text-uppercase mb-0" id="movie_name"></h2>
                            <hr class="star-dark mb-5">
                            <img id="popup-image" class="img-fluid mb-5" src="https://m.media-amazon.com/images/M/MV5BMTYxNDMyOTAxN15BMl5BanBnXkFtZTgwMDg1ODYzNTM@._V1_SY1000_CR0,0,674,1000_AL_.jpg">
                        </div>
                        <div class="col-lg-6 mx-auto">
                            <div class="flex-column">
                                <div class="col-lg-6 movie_content">
                                    <p class="p-5"></p>
                                    <div id="movie_genre">Genero: </div><div class="movie_info"></div>
                                    <div id="movie_release">Fecha de lanzamiento: </div><div class="movie_info"></div>
                                    <div id="movie_length">Duracion: </div><div class="movie_info"></div>
                                    <div id="movie_rating">Puntaje: </div><div class="movie_info"></div>
                                </div>
                                <div class="col-lg-6 movie_content">
                                    <div class="line">
                                        <img src="<c:url value="/resources/images/movie.png"/>">
                                        <div class="name">Cine</div>
                                        <div>
                                        <p><select id="theatrepicker" title="theatre"></select></p>
                                    </div>
                                    <div class="line">
                                        <img src="<c:url value="/resources/images/calendar.png"/>">
                                        <div class="name">Fecha</div>
                                        <div>
                                            <p><input type="text" id="datepicker" placeholder="Seleccione fecha" title="date" ></p>
                                        </div>
                                    </div>
                                    <div class="line">
                                        <img src="<c:url value="/resources/images/hour.png"/>">
                                        <div class="name">Horario</div>
                                        <p><select id="hourpicker" title="theatre"></select></p>
                                    </div>
                                    <div class="line">
                                        <img src="<c:url value="/resources/images/ticket.png"/>">
                                        <div class="name">Cantidad de entradas</div>
                                        <select id="amount-movie" title="ticketsAmount">
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
                                    <div class="line" id="continue_button">
                                        <a class="btn btn-primary btn-lg rounded-pill d-block mx-auto" onclick="checkTriggerSeatPicker()">
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

        <!-- Seat picker popup -->
        <div class="portfolio-modal mfp-hide" id="seatPicker">
            <div class="portfolio-modal-dialog bg-white">
                <a id="back-button" href="#popup-movie" onclick="openPopup('popup-movie')">
                    <i class="fa fa-3x fa-arrow-left"></i>
                </a>
                <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="#" onclick="wipeData()">
                    <i class="fa fa-3x fa-times"></i>
                </a>
                <div class="container text-center">
                    <div class="col">
                        <div class="row">
                        <div class="col-lg-6 mx-auto">
                            <h2 class="text-secondary text-uppercase mb-0" id=""><spring:message code="seat.title"/></h2>
                            <hr class="star-dark mb-5">
                            <div id="screen" align="center"><img class="screenImage" src="<c:url value="/resources/img/Screen_final.png"/>"/></div>
                            <div id="seat-div"></div>
                                <div class="row">
                                    <div class="card seat_demo">
                                        <img  class="card-img-top" src="<c:url value="/resources/images/availableSeat.png"/>" style="width: 37%; align-self: center" height='50px' width='50px'/>
                                        <div class="card-footer"><spring:message code="seat.available"/></div>
                                    </div>
                                    <div class="card seat_demo">
                                        <img  class="card-img-top" src="<c:url value="/resources/images/unavailableSeat.png"/>" style="width: 37%; align-self: center" height='50px' width='50px'/>
                                        <div class="card-footer"><spring:message code="seat.unavailable"/></div>
                                    </div>
                                    <div class="card seat_demo">
                                        <img  class="card-img-top" src="<c:url value="/resources/images/selectedSeat.png"/>" style="width: 37%; align-self: center" height='50px' width='50px'/>
                                        <div class="card-footer"><spring:message code="seat.selected"/></div>
                                   </div>
                                </div>
                            </div>
                        </div>
                    </div>



                    <div class="row">
                        <div class="col-lg-3"></div>
                    <div class="col-lg-6" style="padding-top: 5px;">
                        <a class="btn btn-primary btn-lg rounded-pill d-block mx-auto" onclick="checkConfirmSeats()">
                            <i class="fa"></i>
                            <spring:message code="menu.continue"/>
                        </a>
                    </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Buy food popup -->
        <div class="portfolio-modal mfp-hide" id="buyFood">
            <div class="portfolio-modal-dialog bg-white">
                <a id="back-button" href="#seatPicker" onclick="openPopup('seatPicker')">
                    <i class="fa fa-3x fa-arrow-left"></i>
                </a>
                <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="#" onclick="wipeData()">
                    <i class="fa fa-3x fa-times"></i>
                </a>
                <div class="container text-center">
                    <div class="row">
                        <div class="col-lg-6 mx-auto">
                            <h2 class="text-secondary text-uppercase mb-0" id="food_name">Desea comer algo en la funcion?</h2>
                            <hr class="star-dark mb-5">
                        </div>
                    </div>
                    <div class="row">
                        <div id="foodOptions" style="padding-bottom: 10px;">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <a class="btn btn-primary btn-lg rounded-pill d-block mx-auto" onclick="checkConfirmFood()">
                                <i class="fa"></i>
                                <spring:message code="menu.continue"/>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Purchase review popup -->
        <div class="portfolio-modal mfp-hide" id="reviewPurchase">
            <div class="portfolio-modal-dialog bg-white">
                <a id="back-button" href="#buyFood" onclick="openPopup('buyFood')">
                    <i class="fa fa-3x fa-arrow-left"></i>
                </a>
                <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="#" onclick="wipeData()">
                    <i class="fa fa-3x fa-times"></i>
                </a>
                <div class="container text-center">
                    <div class="row">
                        <div class="col-lg-8 mx-auto">
                            <h2 class="text-secondary text-uppercase"><spring:message code="review.title"/></h2>
                            <hr class="star-dark mb-5">
                            <div id="purchaseReviewTextGoesHere">

                            </div>
                        </div>
                        <div class="col-lg-4">
                            <a class="btn btn-primary btn-lg rounded-pill portfolio-item d-block mx-auto" href="#postPurchase" onclick="confirmPurchase()">
                                <i class="fa"></i>
                                <spring:message code="review.confirm"/>
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
                            <h2 class="text-secondary text-uppercase mb-0" id="movie_name"><spring:message code="review.success"/></h2>
                        <div class="col-lg-6 mx-auto mb-5">
                            <hr class="star-dark mb-5">
                        </div>
                        <h1 id="purchase_code" class="col-lg-8 mx-auto text-uppercase mb-0 purchase_code mt-5">

                        </h1>
                    </div>
                </div>
            </div>
        </div>

        <!-- User profile -->
        <div class="portfolio-modal mfp-hide" id="userProfile">
            <div class="portfolio-modal-dialog bg-white">
                <a id="back-button" href="#" onclick="wipeData()">
                    <i class="fa fa-3x fa-arrow-left"></i>
                </a>
                <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="#">
                    <i class="fa fa-3x fa-times"></i>
                </a>
                <div class="container text-center">
                    <div class="row">
                        <div class="col-lg-8 mx-auto text-secondary text-uppercase mb-0">
                            <h2 class="text-secondary text-uppercase mb-0"><spring:message code="profile.myProfile"/></h2>
                            <hr class="star-dark mb-5">
                            <p class="p-5"></p>
                            <h1 id="user_dni"></h1><h1 class="movie_info"></h1>
                            <h1 id="user_name"></h1><h1 class="movie_info"></h1>
                            <h1 id="user_surname"></h1><h1 class="movie_info"></h1>
                            <h1 id="user_mobile"></h1><h1 class="movie_info"></h1>
                            <h1 id="user_email"></h1><h1 class="movie_info"></h1>
                        </div>
                        <div class="col-lg-4">
                            <a class="btn btn-primary btn-lg rounded-pill" href="<c:url value="/logout"/>" onclick="logout()">
                                <i class="fa"></i>
                                <spring:message code="profile.logout"/>
                            </a>
                            <h5 id="user_purchases" class="text-secondary text-uppercase">

                            </h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</t:main_page>