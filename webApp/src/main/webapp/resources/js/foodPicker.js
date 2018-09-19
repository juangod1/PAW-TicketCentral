function mainFoodPicker(){
    getFood();
}

function checkConfirmFood(){

    if(true){// TODO: @orma chequear que se seleccionaron bien etc
        openPopup("reviewPurchase");
        mainReviewPurchase();
    }
    else{

    }
}


function displayFood(food){
    var divFood = document.getElementById("foodOptions");
    var htmlString = "<div class='card-deck'>";
    for(var i=0; i<food.length;i++){
        var foodAux = food[i];
        var foodHtmlLi = "<div class='card' style='width: 18rem;'>" +
            "<img class='card-img-top' alt='" + foodAux.id + "' id='" + foodAux.id + "-" + foodAux.name + "' " +
            "src='data:image/png;base64," + foodAux.img + "'/>" +
            "<div class='casd-body'>"+
            "<h5 class='card-title'>" +
            foodAux.name +
            "</h5>" +
            "<p class='card-text'>No hay descripcion! Aca puedo agregar las cantidades</p>"+
            "</div>" +
            "</div>";
        htmlString += foodHtmlLi;
    }
    htmlString += "</div>";
    divFood.innerHTML = htmlString;
}

function getFood() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState!=4){
            return;
        }
        if (this.status == 200) {
            var food = JSON.parse(this.responseText);
            displayFood(food);
        }
        else{
            noFoodFound();
        }
    };
    //LE PEGA AL ENDPOINT /json/movie/getMovies. Si hay peliculas se ejecuta el servicio de busqueda de peliculas y devuelve el json.
    xhttp.open("GET", "/json/food/getFood", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
}

function noFoodFound() {
    //TODO SHOW USER NO FOOD WAS FOUND
}