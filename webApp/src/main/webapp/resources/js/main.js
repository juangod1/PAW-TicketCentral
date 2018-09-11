$("#document").ready(function(){
    initializeData();
});

var ticketsDate;
var ticketsTime;
var ticketsAmount;
var movies;
var dateScreeningIDMap = {};

function initializeData(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            movies = JSON.parse(this.responseText);
            main();
        }
    };
    //LE PEGA AL ENDPOINT /json/movie/getMovies. Si hay peliculas se ejecuta el servicio de busqueda de peliculas y devuelve el json.
    xhttp.open("GET", "/json/movie/getMovies", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
}

function main(){
    setupImages();
    setupScreenings();
}

function setupScreenings(){
    for(var i=0; i< movies.length; i++)
    {
        var movie = movies[i];
        setupScreeningsRequest(movie.id);
    }
}

function setupScreeningsRequest(id){
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var screenings = JSON.parse(this.responseText);
            for(var j=0; j< screenings.length; j++) {
                var screening = screenings[j];
                var date = new Date(0);
                date.setUTCMilliseconds(screening.time);
                $("#date-movie-"+id).append("<option value=\"" + date + "\">" + date + "</option>");
                dateScreeningIDMap[date.toString()]=screening.id;
            }
        }
    };
    //LE PEGA AL ENDPOINT
    xhttp.open("GET", "/json/screening/getScreeningsByMovie/"+ id, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
}

function setupImages(){
    setupMovieImages();
    setupFoodImages();
}

function setupFoodImages() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
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
    };
    //LE PEGA AL ENDPOINT /json/movie/getMovies. Si hay peliculas se ejecuta el servicio de busqueda de peliculas y devuelve el json.
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
    var date = $("#date-movie-"+movieID).val();

    if(date !== "seleccionar" && amount != 0){ // chequeo
        ticketsAmount = amount;
        ticketsDate = date;
        $.magnificPopup.open({
            items: {
                src: '#seatPicker'
            },
            type: 'inline'
        });

        var screeningID = dateScreeningIDMap[date];

        mainSeatPicker(screeningID);
    }
    else{
        alert("Incorrecta selección.");
    }
}