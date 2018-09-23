<%--
  Created by IntelliJ IDEA.
  User: juangod
  Date: 06/09/18
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="premieres" fragment="true" %>
<%@attribute name="movies" fragment="true" %>
<%@attribute name="food" fragment="true" %>
<%@attribute name="popups" fragment="true" %>
<%@attribute name="head" fragment="true" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title> Bienvenido a Atlas Cinema!</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css"/>"/>

    <!-- Custom fonts for this template -->
    <link rel="stylesheet" href="<c:url value="/resources/vendor/font-awesome/css/font-awesome.min.css"/>"/>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- Plugin CSS -->
    <link href="<c:url value="/resources/vendor/magnific-popup/magnific-popup.css"/>" rel="stylesheet" type="text/css">

    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/freelancer.css"/>" rel="stylesheet">
    <!-- Bootstrap core JavaScript -->
    <script src="<c:url value="/resources/vendor/jquery/jquery.min.js"/>"></script>
    <script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
    <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js" integrity="sha256-eGE6blurk5sHj+rmkfsGYeKyZx3M4bG+ZlFyA7Kns7E=" crossorigin="anonymous"></script>
    <jsp:invoke fragment="head"/>
</head>

<body id="page-top">

<!-- Navigation -->
<nav class="navbar navbar-expand-lg bg-secondary fixed-top text-uppercase" id="mainNav">
    <div class="container">
        <a class="atlas_title navbar-brand js-scroll-trigger" href="#page-top">Atlas</a>
        <a class="atlas_title navbar-brand js-scroll-trigger invisible" id="adminButton" href="<c:url value="/admin"/>">Admin</a>
        <button class="navbar-toggler navbar-toggler-right text-uppercase bg-primary text-white rounded" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fa fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item mx-0 mx-lg-1">
                    <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="#premieres">Estrenos</a>
                </li>
                <li class="nav-item mx-0 mx-lg-1">
                    <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="#movies">Peliculas</a>
                </li>
                <li class="nav-item mx-0 mx-lg-1">
                    <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="#food">Comida</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="logged_as btn" id="loggedAs" href=""></div>
</nav>

<!-- Header -->
<header class="masthead bg-primary text-white text-center">
    <div class="container">
        <img class="img-fluid mb-5 d-block mx-auto" src="../../resources/img/profile.png" alt="">
        <h1 class="text-uppercase mb-0">Atlas Cinema</h1>
        <hr class="star-light">
        <h2 class="font-weight-light mb-0">Una experiencia única.</h2>
    </div>
</header>

<!-- Premieres -->
<section class="portfolio" id="premieres">
    <div class="container">
        <h2 class="text-center text-uppercase text-secondary mb-0">Estrenos</h2>
        <hr class="star-dark mb-5">
        <div class="row">
            <jsp:invoke fragment="premieres"/>
        </div>
    </div>
</section>

<!-- Movies -->
<section class="portfolio" id="movies">
    <div class="container">
        <h2 class="text-center text-uppercase text-secondary mb-0">Peliculas</h2>
        <hr class="star-dark mb-5">
        <div class="row">
            <jsp:invoke fragment="movies"/>
        </div>
    </div>
</section>

<!-- Food -->
<section class="portfolio" id="food">
    <div class="container">
        <h2 class="text-center text-uppercase text-secondary mb-0">Tentate con nuestro menú</h2>
        <hr class="star-dark mb-5">
        <div class="row">
            <jsp:invoke fragment="food"/>
        </div>
    </div>
</section>

<!-- Footer -->
<footer class="footer text-center">
    <div class="container">
        <div class="row">
            <div class="col-md-4 mb-5 mb-lg-0">
                <h4 class="text-uppercase mb-4">Ubicacion</h4>
                <p class="lead mb-0">Av. Eduardo Madero 399
                    <br>Puerto Madero, CABA</p>
            </div>
            <div class="col-md-4 mb-5 mb-lg-0">
                <h4 class="text-uppercase mb-4">Encontranos en las redes</h4>
                <ul class="list-inline mb-0">
                    <li class="list-inline-item">
                        <a class="btn btn-outline-light btn-social text-center rounded-circle" href="#">
                            <i class="fa fa-fw fa-facebook"></i>
                        </a>
                    </li>
                    <li class="list-inline-item">
                        <a class="btn btn-outline-light btn-social text-center rounded-circle" href="#">
                            <i class="fa fa-fw fa-google-plus"></i>
                        </a>
                    </li>
                    <li class="list-inline-item">
                        <a class="btn btn-outline-light btn-social text-center rounded-circle" href="#">
                            <i class="fa fa-fw fa-twitter"></i>
                        </a>
                    </li>
                    <li class="list-inline-item">
                        <a class="btn btn-outline-light btn-social text-center rounded-circle" href="#">
                            <i class="fa fa-fw fa-linkedin"></i>
                        </a>
                    </li>
                    <li class="list-inline-item">
                        <a class="btn btn-outline-light btn-social text-center rounded-circle" href="#">
                            <i class="fa fa-fw fa-dribbble"></i>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="col-md-4">
                <h4 class="text-uppercase mb-4">Acerca de nosotros</h4>
                <p class="lead mb-0"> Somos un cine distinto, creado para el proyecto de PAW en el año 2018.
            </div>
        </div>
    </div>
</footer>

<div class="copyright py-4 text-center text-white">
    <div class="container">
        <small>Copyright &copy; ATLAS CINEMA 2018</small>
    </div>
</div>

<!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
<div class="scroll-to-top d-lg-none position-fixed ">
    <a class="js-scroll-trigger d-block text-center text-white rounded" href="#page-top">
        <i class="fa fa-chevron-up"></i>
    </a>
</div>

<jsp:invoke fragment="popups"/>


<!-- Plugin JavaScript -->
<script src="<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js"/>"></script>
<script src="<c:url value="/resources/vendor/magnific-popup/jquery.magnific-popup.min.js"/>"></script>

<!-- Contact Form JavaScript -->
<script src="<c:url value="/resources/js/jqBootstrapValidation.js"/>"></script>

<!-- Custom scripts for this template -->
<script src="<c:url value="/resources/js/freelancer.min.js"/>"></script>

</body>

</html>
