$("#document").ready(function(){
    main();
});

function main(){
    setupImages();
}

function setupImages() {
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

//Codigo SeatSelection
function Queue(){var a=[],b=0;this.getLength=function(){return a.length-b};this.isEmpty=function(){return 0==a.length};this.enqueue=function(b){a.push(b)};this.dequeue=function(){if(0!=a.length){var c=a[b];2*++b>=a.length&&(a=a.slice(b),b=0);return c}};this.peek=function(){return 0<a.length?a[b]:void 0}};
var wantedSeats;
var wantedQueue;
var obj;
var objsize;

//ToDo: onclick al boton continuar y que si no esta completo el formulario, que no cambie de popup
//y que no ejecute nada.
function checkTrigger(){
    //Checkeos de completitud del form
    //sino salgo y tiro el alert
    //inicio del popup
    trigger();
}

function trigger(){
    var optionAmount = document.getElementById("amount");
    wantedSeats = optionAmount.options[optionAmount.selectedIndex].value;
    wantedQueue = new Queue();
    getSeats();
}

function getSeats() {
    var scDivId = ($("div[class='portfolio-modal']")[0].id);
    var screeningId = scDivId.substr(12);

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            obj = JSON.parse(this.responseText);
            objsize = obj.length;
            drawSeats();
            //alert(obj[0].name); //imprime el nombre de la primer pelicula
        }
    };
    //LE PEGA AL ENDPOINT
    xhttp.open("GET", "/json/transaction/getSeatsByScreening/"+ screeningId, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
}

function drawSeats(){
    var tableDiv = document.getElementById("seat-div");
    var tableBuild = '<table align="center"><tr>';

    var i =0;
    var auxCoordX = -1;
    var auxCoordY = -1;

    for(var j=0; j< objsize; j++){
        i++;
        //Manejo de Rows
        if(obj[j].coordy == auxCoordY + 1){
            auxCoordX = -1;
            auxCoordY++;
        }

        //Manejo de Columns
        if(obj[j].coordx == auxCoordX + 1){
            if(obj[j].coordx == 0){
                tableBuild += "</tr>"
                if(objsize != i){
                    tableBuild += "<tr>";
                }
            }
            if(!obj[j].occupied) {
                tableBuild += "<td id='s_" + obj[j].coordx + "-" + obj[j].coordy + "' height='50px' width='50px'><img style='display:block;' width='100%' height='100%' src='resources/images/availableSeat.png' onclick='onSelectedSeat(" + obj[j].coordx + "," + obj[j].coordy + ");'></td>";
            }else{
                tableBuild += "<td id='s_" + obj[j].coordx + "-" + obj[j].coordy + "' height='50px' width='50px'><img style='display:block;' width='100%' height='100%' src='resources/images/unavailableSeat.png' onclick='onSelectedSeat(" + obj[j].coordx + "," + obj[j].coordy + ");'></td>";
            }
            auxCoordX++;
        }else{
            while(auxCoordX<obj[j].coordx) {
                tableBuild += "<td height='50px' width='50px'></td>";
                auxCoordX++;
            }
        }
    }

    tableBuild += '</table>';
    tableDiv.innerHTML = tableBuild;
}

function onSelectedSeat(x, y){
    //dado el asiento que clickeo, que tiene un link, se ejecuta esta funcion
    var seatName = "s_" + x + "-" + y;
    var seatSelected = document.getElementById(seatName);
    var status = seatSelected.getElementsByTagName("img")[0].src;

    if(status.includes("selected") || status.includes("unavailable")){
        return;
    } else {
        if(wantedQueue.getLength() == wantedSeats){
            var nameDequeueSeat = wantedQueue.dequeue();
            var dequeueSeat = document.getElementById(nameDequeueSeat.id);
            dequeueSeat.getElementsByTagName("img")[0].src = "resources/images/availableSeat.png";
        }
        wantedQueue.enqueue(seatSelected);
        seatSelected.getElementsByTagName("img")[0].src = "resources/images/selectedSeat.png";
    }
}