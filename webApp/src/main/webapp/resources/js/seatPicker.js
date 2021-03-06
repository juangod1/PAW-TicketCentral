//Codigo SeatSelection
function Queue(){var a=[],b=0;this.getLength=function(){return a.length-b};this.isEmpty=function(){return 0==a.length};this.enqueue=function(b){a.push(b)};this.dequeue=function(){if(0!=a.length){var c=a[b];2*++b>=a.length&&(a=a.slice(b),b=0);return c}};this.peek=function(){return 0<a.length?a[b]:void 0}};
var wantedSeats;
var wantedQueue;
var wantedObjQueue;
var obj;
var objsize;
var screeningIDnum;

function checkConfirmSeats(){
    wantedObjQueue = new Queue();
    if(wantedQueue.getLength() == wantedSeats){// TODO: @orma chequear que se seleccionaron bien etc
        for(var i=0; i < wantedSeats;i++){
            var seatHtml = wantedQueue.dequeue();
            var j=0;
            while("s_" + obj[j].coordx + "-" + obj[j++].coordy != seatHtml.id);
            wantedQueue.enqueue(seatHtml);
            wantedObjQueue.enqueue(obj[--j]);
        }
        openPopup("buyFood");
        mainFoodPicker();
    }
    else{
        window.alert("Please select all the seats!");
    }
}

function mainSeatPicker(screeningID){
    wantedSeats = ticketsAmount;
    wantedQueue = new Queue();
    getSeats(screeningID);
}

function getSeats(screeningID) {
    var xhttp = new XMLHttpRequest();
    screeningIDnum = screeningID;
    xhttp.onreadystatechange = function() {
        if(this.readyState!=4){
            return;
        }
        if (this.status == 200) {
            obj = JSON.parse(this.responseText);
            objsize = obj.length;
            drawSeats();
            //alert(obj[0].name); //imprime el nombre de la primer pelicula
        }
        else{
            noSeatsFound();
        }
    };
    //LE PEGA AL ENDPOINT
    xhttp.open("GET", "/json/transaction/getSeatsByScreening/"+ screeningID, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
}
function noSeatsFound() {
    //TODO NO SEATS FOUND. DO SOMETHING
}

function drawSeats(){
    var tableDiv = document.getElementById("seat-div");
    var tableBuild = '<table align="center"><tr>';

    var i =0;
    var auxCoordX = 0;
    var auxCoordY = 0;

    for(var j=0; j< objsize; j++){
        i++;
        //Manejo de Rows
        if(obj[j].coordy > auxCoordY){
            while(obj[j].coordy != auxCoordY) {
                tableBuild += "</tr><tr>";
                auxCoordY++;
            }
            auxCoordX = 0;
        }

        //Manejo de Columns
        if(obj[j].coordx > auxCoordX){
            while(obj[j].coordx > auxCoordX) {
                tableBuild += "<td height='50px' width='50px'></td>";
                auxCoordX++;
            }
        }
        if(!obj[j].occupied) {
            tableBuild += "<td id='s_" + obj[j].coordx + "-" + obj[j].coordy + "' height='50px' width='50px'><img style='display:block;' width='100%' height='100%' src='resources/images/availableSeat.png' onclick='onSelectedSeat(" + obj[j].coordx + "," + obj[j].coordy + ");'></td>";
        }else{
            tableBuild += "<td id='s_" + obj[j].coordx + "-" + obj[j].coordy + "' height='50px' width='50px'><img style='display:block;' width='100%' height='100%' src='resources/images/unavailableSeat.png' onclick='onSelectedSeat(" + obj[j].coordx + "," + obj[j].coordy + ");'></td>";
        }
        auxCoordX++;

    }

    tableBuild += '</tr></table>';
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