<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap-4.1.3-dist/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/movie.css"/>"/>
    <script src="<c:url value="/resources/js/jquery-3.3.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/lib.js" />"></script>
    <script src="<c:url value="/resources/js/seatPicker.js" />"></script>
</head>
<body>
<div class="background">
    <!-- Title and logo -->
    <div class="banner" >
        <a class="atlas_title" href="<c:url value="/"/>">Atlas</a>
    </div>

    <!-- Seats -->
    <div class="divisor">
        <div class="movie_divs description">
        </div>
        <div class="movie_divs description">
            <div class = "movie_buy">
                <button type="button" class="btn btn-secondary btn-lg" id="purchase">Revision de compra</button>
            </div>
        </div>
    </div >

    <!-- Food -->
    <div class="divisor">
        <div class="title">
            COMIDA
        </div>
        <div>
            <a> aca van la morfi</a>
        </div>
    </div>
</div>
</body>
</html>