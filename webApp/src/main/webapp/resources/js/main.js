$("#document").ready(function(){
    initializeData();
});

$(window).on('resize orientationchange', function (e) {
    if ($.datepicker._datepickerShowing) {
        var datepicker = $.datepicker._curInst;
        var dpInput = datepicker.input;
        var dpElem = datepicker.dpDiv;
        dpElem.position({
            my: 'left top',
            of: dpInput,
            at: 'left bottom'
        });
    }
});


var ticketsDate;
var ticketsAmount;
var global_movieId;
var movies;
var movieSelected;
var movieIDtoNamesMap={};
var dayToScreeningsMap={}
var user=undefined;
var availableDates = [];

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


function main(){
    $('#datepicker').datepicker({
        beforeShowDay: available,
        onSelect: function(dateText) {
            updateHours(dateText);
        },
        dateFormat: 'dd-mm-yy'
    });
    setupUser();
    setupImages();
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

function setPopupImage(movie){
    var img = document.getElementById("popup-image");
    img.src ="data:image/png;base64," + movie.img;
}

function setupMovieImages() {
    for(var i=0; i< movies.length; i++)
    {
        var obj = movies[i];
        var imgElement = $("#movie-"+obj.id).find( "img" )[0];
        imgElement.src = "data:image/png;base64," + obj.img;
    }
}

function setupUser(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState !=4){
            return;
        }
        if (this.status == 200) {
            user = JSON.parse(this.responseText);
            showAdminButtonIfAdmin();
            setLoggedUserButton();
        }
    };
    xhttp.open("GET", "/json/user/getCurrentUser", true);
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

function cleanHourOptions(){
    document.getElementById("hourpicker").innerHTML="";
}

function destroyMoviePopup() {
    var moviename =document.getElementById("movie_name");
    moviename.innerText="";
    setDisplayNone(moviename);

    var moviegenre =document.getElementById("movie_genre");
    moviegenre.innerText="";
    setDisplayNone(moviegenre);

    var movierelease =document.getElementById("movie_release");
    movierelease.innerText="";
    setDisplayNone(movierelease);

    var movielength =document.getElementById("movie_length");
    movielength.innerText="";
    setDisplayNone(movielength);

    var movierating =document.getElementById("movie_rating");
    movierating.innerText="";
    setDisplayNone(movierating);

    var datepicker =document.getElementById("datepicker");
    datepicker.value="";
    setDisplayNone(datepicker);

    var amount =document.getElementById("amount-movie");
    amount.innerHTML="";
    setDisplayNone(amount);

    var continuebutton =document.getElementById("continue_button");
    continuebutton.innerHTML="";
    setDisplayNone(continuebutton);

    var theatrepicker =document.getElementById("theatrepicker");
    theatrepicker.innerHTML="";
    setDisplayNone(theatrepicker);

    var hourpicker =document.getElementById("hourpicker");
    hourpicker.innerHTML="";
    setDisplayNone(hourpicker);

    var select = document.getElementById("amount-movie");
    select.innerHTML="";
    setDisplayNone(select);

    var img = document.getElementById("popup-image");
    img.innerHTML="";

    $("#theatrepicker").off();

    cleanHourOptions();
    availableDates=[];
    dayToScreeningsMap={};
}

function noMovieFound() {
    destroyMoviePopup();
    var moviename=document.getElementById("movie_name");
    moviename.innerText="404 Not Found";
    setDisplayBlock(moviename);
}



function available(date) {
    dmy = normalizeDate(date);
    if ($.inArray(dmy, availableDates) != -1) {
        return [true, "","Available"];
    } else {
        return [false,"","unAvailable"];
    }
}


function noTheatresFound(){
    var option = document.createElement("option");
    option.innerText="No se encontraron Cines para esta pelicula";
    var picker = document.getElementById("theatrepicker");
    picker.append(option);
    setDisplayBlock(picker);
}

function noScreeningsFound() {
    availableDates=[];

}

function setupMoviePopup(movieId)
{
    global_movieId=movieId;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState !=4){
            return;
        }
        if (this.status == 200) {
            movie = JSON.parse(this.responseText);
            drawMoviePopup(movie);
        }
        else
        {
            noMovieFound();
        }
    };
    xhttp.open("GET", "/json/movie/getMovieById/"+movieId, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
}

function drawMoviePopup(movie) {
    destroyMoviePopup();
    setPopupImage(movie);
    putTheatres(movie.id);
    var moviename = document.getElementById("movie_name");
    moviename.innerText=movie.name;
    setDisplayBlock(moviename);
    var moviegenres = document.getElementById("movie_genre");
    moviegenres.innerText=movie.genres;
    setDisplayBlock(moviegenres);
    var movierelease = document.getElementById("movie_release");
    movierelease.innerText=movie.releaseDate;
    setDisplayBlock(movierelease);
    var movielength = document.getElementById("movie_length");
    movielength.innerText=movie.runtime;
    setDisplayBlock(movielength);
    var movierating = document.getElementById("movie_rating");
    movierating.innerText=movie.rating;
    setDisplayBlock(movierating);

    var select = document.getElementById("amount-movie");
    for(var i=0; i<10; i++){
        var elem = document.createElement("option");
        elem.innerText=i;
        select.append(elem);
    }
    setDisplayBlock(select);
    document.getElementById("continue_button").innerHTML="<a class='btn btn-primary btn-lg rounded-pill d-block mx-auto' onclick='checkTriggerSeatPicker()'><i class='fa'></i>Continuar</a>";
}


function putTheatres(id){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState!=4){
            return
        }
        if (this.status == 200) {
            var theatres= JSON.parse(this.responseText);
            var picker = document.getElementById("theatrepicker");

            for(var i=0; i< theatres.length; i++)
            {
                var theatre = theatres[i];
                var option = document.createElement("option");
                option.innerText=theatre.name;
                option.id=theatre.name;
                picker.append(option);
            }
            picker.onchange = updateDates;
            setDisplayBlock(picker)
        }
        else{
            noTheatresFound();
        }
    };

    xhttp.open("GET", "/json/theatre/getTheatresByMovie/"+id, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
}



function updateDates()
{
    var movieid=global_movieId;
    var picker = document.getElementById("theatrepicker");
    var theatreName =picker.options[picker.selectedIndex].id;

    var datepicker=document.getElementById("datepicker");
    datepicker.value="";
    setDisplayBlock(datepicker);

    availableDates =[];

    var hourpicker = document.getElementById("hourpicker");
    hourpicker.innerHTML="";
    setDisplayNone(hourpicker);

    dayToScreeningsMap={};


    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState!=4){
            return;
        }
        if (this.status == 200) {
            var screenings= JSON.parse(this.responseText);

            for(var i=0; i< screenings.length; i++)
            {
                var datepicker = document.getElementById("datepicker");
                setDisplayBlock(datepicker);
                var screening = screenings[i];
                var date = dateFormat(screening);
                availableDates.push(date);
                if(dayToScreeningsMap[date]==null || dayToScreeningsMap[date]==undefined ){
                    dayToScreeningsMap[date]=[];
                }
                dayToScreeningsMap[date].push(screening);
            }
        }
        else{
            noScreeningsFound();
        }
    };

    xhttp.open("GET", "json/screening/getScreeningsByTheatreMovie/" +theatreName+"/"+movieid, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
}


function updateHours(date){
    cleanHourOptions();
    var array = dayToScreeningsMap[date];
    var picker = document.getElementById("hourpicker");
    setDisplayBlock(picker);
    for(var i=0; i<array.length; i++){
        var screening=array[i];
        var option = document.createElement("option");
        option.innerText=hourFormat(screening);
        option.id=screening.id;
        picker.append(option);
    }
    setDisplayBlock(document.getElementById("continue_button"));
}



function checkTriggerSeatPicker(movieID){
    if (user === undefined){
        location.href = "/login";
        return;
    }

    var amount = $("#amount-movie").val();
    var select = document.getElementById("hourpicker");
    var option = select.options[select.selectedIndex];
    var date = option.value;

    if(date!=null && date != undefined && amount!=undefined && amount!=null && amount != 0){ // chequeo
        ticketsAmount = amount;
        ticketsDate = document.getElementById("datepicker").value + option.textContent;

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


function setDisplayBlock(htmlElement){
    htmlElement.style.display = "block";
}

function setDisplayNone(htmlElement){
    htmlElement.style.display = 'none';

}