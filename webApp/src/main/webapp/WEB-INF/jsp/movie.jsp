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
                    <figcaption class="movie_title"> Cenicienta </figcaption>
                    <img class="movie_img" src="https://static.cinemarkhoyts.com.ar/Images/Posters/d6789ff05e374a7b80a0f15aaab5abc3.jpg">
                </figure>
            </div>
            <div class="movie_divs description">
                genero anio etc
            </div>
            <div class="movie_divs description">
                COMPRAR
            </div>
        </div>
        <div>
            <!-- trailer ?? -->
        </div>
    </div>

</div>
</body>
</html>
