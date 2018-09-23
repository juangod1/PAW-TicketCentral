function mainFoodPicker(){
    foodSelection = [];
    getFood();
}

var foods;
var foodSelection;

function checkConfirmFood(){
    for(var curFood in foods){
        var foodSelected = document.getElementById("quantity-food-" + foods[curFood].id);
        if(foodSelected.selectedIndex != 0) {
            foodSelection.push({
                food: foods[curFood],
                qty: foodSelected.selectedIndex
            })
        }
    }

    openPopup("reviewPurchase");
    mainReviewPurchase();
}

function displayFood(food){
    var divFood = document.getElementById("foodOptions");
    var htmlString = "<div class='row' id='foodDeck'>";
    for(var i=0; i<food.length;i++){
        var foodAux = food[i];
        var foodHtmlLi = "<div class='col-lg-3 mt-3 mb-3' style='width: 18rem;'>" +
            "<img class='card-img-top row' style='height:50%' alt='" + foodAux.id + "' id='" + foodAux.id + "-" + foodAux.name + "' " +
            "src='data:image/png;base64," + foodAux.img + "'/>" +
            "<div class='card-body row' style='height:40%'>"+
            "<div class='col'>" +
            "<div class='row'>" +
            "<h5 class='card-title'> " +
            foodAux.name +
            "</h5> </div>" +
            "<div class='row'> <p>Descripcion!</p> </div>" + "</div>" +
            "</div><div class='card-footer row' style='height:10%'>" +
            "<select id='quantity-food-" + foodAux.id + "' title='Quantity'>";
            for(var j=0; j < 10 || j <= foodAux.stock; j++)
                foodHtmlLi += "<option value='"+j+"'>"+j+"</option>";
            foodHtmlLi += "</select>"+
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
            foods = JSON.parse(this.responseText);
            displayFood(foods);
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