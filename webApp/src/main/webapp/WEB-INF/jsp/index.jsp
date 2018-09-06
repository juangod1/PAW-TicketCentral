<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main_page>
    <jsp:attribute name="premieres">
        <c:forEach var="premiere" items="${premieres}">
                <figure class="image_container">
                    <a href="<c:url value="/movie?movieID=${premiere.id}"/>">

                    </a>
                    <figcaption> ${premiere.name} </figcaption>
                </figure>
            <div class="col-md-6 col-lg-4">
                <a class="portfolio-item d-block mx-auto" href="#portfolio-modal-1">
                    <div class="portfolio-item-caption d-flex position-absolute h-100 w-100">
                        <div class="portfolio-item-caption-content my-auto w-100 text-center text-white">
                            <i class="fa fa-search-plus fa-3x"></i>
                        </div>
                    </div>
                    <img class="img-fluid" src="https://m.media-amazon.com/images/M/MV5BMTYxNDMyOTAxN15BMl5BanBnXkFtZTgwMDg1ODYzNTM@._V1_SY1000_CR0,0,674,1000_AL_.jpg" alt="">
                </a>
            </div>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="movies">
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
                        <div class="col-lg-8 mx-auto">
                            <h2 class="text-secondary text-uppercase mb-0">Project Name</h2>
                            <hr class="star-dark mb-5">
                            <img class="img-fluid mb-5" src="../../resources/img/portfolio/cabin.png" alt="">
                            <p class="mb-5">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Mollitia neque assumenda ipsam nihil, molestias magnam, recusandae quos quis inventore quisquam velit asperiores, vitae? Reprehenderit soluta, eos quod consequuntur itaque. Nam.</p>
                            <a class="btn btn-primary btn-lg rounded-pill portfolio-modal-dismiss" href="#">
                                <i class="fa fa-close"></i>
                                Close Project</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</t:main_page>