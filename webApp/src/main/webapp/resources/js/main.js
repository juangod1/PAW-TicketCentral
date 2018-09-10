$("#document").ready(function(){
    main();
});

var ticketsDate;
var ticketsTime;
var ticketsAmount;

function main(){
    setupImages();
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
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var objs = JSON.parse(this.responseText);
            for(var i=0; i< objs.length; i++)
            {
                var obj = objs[i];
                var imgElement = $("#movie-"+obj.id).find( "img" )[0];
                var popupElement = $("#popup-movie-"+obj.id).find( "img" )[0];
                imgElement.src = "data:image/png;base64," + obj.img;
                popupElement.src = "data:image/png;base64," + obj.img;
            }
        }
    };
    //LE PEGA AL ENDPOINT /json/movie/getMovies. Si hay peliculas se ejecuta el servicio de busqueda de peliculas y devuelve el json.
    xhttp.open("GET", "/json/movie/getMovies", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
}


function checkTriggerSeatPicker(movieID){
    //Checkeos de completitud del form
    //sino salgo y tiro el alert
    //inicio del popup
    var amount = $("#amount-movie-"+movieID).val();
    var time = $("#time-movie-"+movieID).val();
    var date = $("#date-movie-"+movieID).val();

    if(time !== "seleccionar" && date !== "seleccionar" && amount != 0){ // chequeo
        ticketsAmount = amount;
        ticketsDate = date;
        ticketsTime = time;
        $.magnificPopup.open({
            items: {
                src: '#seatPicker'
            },
            type: 'inline'
        });

        var screeningID = 1;

        mainSeatPicker(screeningID);
    }
    else{
        alert("Incorrecta selección.");
    }
}