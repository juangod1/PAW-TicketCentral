$("#document").ready(function(){
    initializeData();
});

var ticketsDate;
var ticketsAmount;
var movies;
var movieSelected;
var movieIDtoNamesMap={};
var user=undefined;

function setupUser(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState !=4){
            return;
        }
        if (this.status == 200) {
            user = JSON.parse(this.responseText);
            showAdminButtonIfAdmin();
        }
    };
    xhttp.open("GET", "/json/user/getCurrentUser", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
}

function initializeData(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState !=4){
            return;
        }
        if (this.status == 200) {
            movies = JSON.parse(this.responseText);
            main();
        }
        else{
            noMoviesFound();
        }
    };
    //LE PEGA AL ENDPOINT /json/movie/getMovies. Si hay peliculas se ejecuta el servicio de busqueda de peliculas y devuelve el json.
    xhttp.open("GET", "/json/movie/getMovies", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
}

function noMoviesFound() {
    //TODO SHOW USER NO MOVIES WERE FOUND
}

function showAdminButtonIfAdmin(){
    console.log(user);
    console.log(user.admin);
    if(user===undefined)
        return;

    if(user.admin){
        $("#adminButton").removeClass("invisible");
    }
}

function main(){
    setupUser();
    setupImages();
    setupScreenings();
}

function setupScreenings(){
    for(var i=0; i< movies.length; i++)
    {
        var movie = movies[i];
        movieIDtoNamesMap[movie.id]=movie.name;
        setupScreeningsRequest(movie.id);
    }
}

function setupScreeningsRequest(id){
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if(this.readyState != 4){
            return
        }
        if (this.status == 200) {
            var screenings = JSON.parse(this.responseText);
            for(var j=0; j< screenings.length; j++) {
                var screening = screenings[j];
                var date = new Date(0);
                date.setUTCMilliseconds(screening.time);
                $("#date-movie-"+id).append("<option id= \'"
                +screening.id.toString()+"\'value=\"" + date
                + "\">" + dateFormat(date) + "</option>");
            }
        }
        else{
            noScreeningsWereFound();
        }
    };
    //LE PEGA AL ENDPOINT
    xhttp.open("GET", "/json/screening/getScreeningsByMovie/"+ id, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
}

function noScreeningsWereFound(){
    //TODO SHOW USER NO SCREENINGS WERE FOUND
}

function setupImages(){
    setupMovieImages();
    setupFoodImages();
}

function setupFoodImages() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState!=4){
            return
        }
        if (this.status == 200) {
            var objs = JSON.parse(this.responseText);
            for(var i=0; i< objs.length; i++)
            {
                var obj = objs[i];
                var imgElement = $("#food-"+obj.id).find( "img" )[0];
                //var popupElement = $("#popup-movie-"+obj.id).find( "img" )[0];
                imgElement.src = "data:image/png;base64," + obj.img;
                //popupElement.src = "data:image/png;base64," + obj.img;
            }
        }
        else{
            noFoodFound();
        }
    };

    xhttp.open("GET", "/json/food/getFood", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
}
function setupMovieImages() {
    for(var i=0; i< movies.length; i++)
    {
        var obj = movies[i];
        var imgElement = $("#movie-"+obj.id).find( "img" )[0];
        var popupElement = $("#popup-movie-"+obj.id).find( "img" )[0];
        imgElement.src = "data:image/png;base64," + obj.img;
        popupElement.src = "data:image/png;base64," + obj.img;
    }
}


function checkTriggerSeatPicker(movieID){
    var amount = $("#amount-movie-"+movieID).val();
    var select = document.getElementById("date-movie-"+movieID);
    var option = select.options[select.selectedIndex];
    var date = option.value;

    if(date !== "seleccionar" && amount != 0){ // chequeo
        ticketsAmount = amount;
        ticketsDate = option.textContent;

        openPopup("seatPicker");

        movieSelected = movieID;
        var screeningID = option.id;

        mainSeatPicker(screeningID);
    }
    else{
        alert("Incorrecta selección.");
    }
}

function wipeData(){
    ticketsAmount=null;
    ticketsDate=null;
    //TODO:@orma borrar seleccion de asientos
}