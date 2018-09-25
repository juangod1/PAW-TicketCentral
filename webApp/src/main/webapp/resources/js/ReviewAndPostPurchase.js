var seatsArray=[];
var usr;

function mainReviewPurchase(){
    seatsArray = [];
    setUpData();
    var div = $("#purchaseReviewTextGoesHere");
    div.empty();
    var newDiv;

    drawTicketsPurchased();

    for(var i=0;i<foodSelection.length;i++){
        var foodSelected = foodSelection[i];
        newDiv = $("" +
        "<h5 class=\"text-secondary text-uppercase\">  $" + foodSelected.food.price * foodSelected.qty  +" - "+ foodSelected.food.name + " x" + foodSelected.qty +"</h5>");
        div.append(newDiv);
    }

}

function drawTicketsPurchased(){
    var seat;
    var div = $("#purchaseReviewTextGoesHere");

    var newDiv = $("<h5 class=\"text-secondary text-uppercase\"></h5>");

    var seatString = "$";
    seatString += wantedSeats*100; // TODO: QUE AGARRE EL PRECIO DE ALGUN LADO Y ponga el subtotal
    seatString +=  " - Asiento";

    if(wantedQueueObj.getLength()>1)
        seatString += "s";

    seatString += " ";

    for(var i=0;i<wantedSeats;i++) {
        if(i!==0) seatString += ", ";
        seat = wantedQueueObj.dequeue();
        seatsArray.push(seat);
        seatString += seat.name;
    }

    seatString += " " + movieIDtoNamesMap[global_movieId] +" "+ ticketsDate;

    newDiv.text(seatString);
    div.append(newDiv);
}

function confirmPurchase(){
    var loading = getLoading();

    var purchaseCode = $("#purchase_code");
    purchaseCode.empty();
    purchaseCode.append(loading);

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState!=4){
            return;
        }
        if (this.status == 200) {
            var transaction = JSON.parse(this.responseText);
            purchaseCode.empty();
            mainPostPurchase(transaction);
        }
        else{
            transactionFailed();
        }
    };
    xhttp.open("POST", "/json/transaction/confirmCheckout", true);
    xhttp.setRequestHeader("Content-type", "application/json");

    var seatNames = [];
    for(var i=0;i<seatsArray.length;i++){
        seatNames.push(seatsArray[i].name);
    }

    var foodDetails= [];
    for(i=0;i<foodSelection.length;i++){
        foodDetails.push(foodSelection[i].food.id + "," + foodSelection[i].qty);
    }

    var trans = {userId:usr.id, screeningID:screeningIDnum,seatNames:seatNames,foodDetails:foodDetails,sendMail:true};
    xhttp.send(JSON.stringify(trans));
}

function transactionFailed(){
    //TODO: TRANSACTION FAILED SHOW IT TO USER
}

function mainPostPurchase(transactionID){
    $("#purchase_code").text("Código de Reserva: " + transactionID);
}

function setUpData(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState!=4) {
            return;
        }
        if (this.status == 200) {
            var xhttp = new XMLHttpRequest();
            usr = JSON.parse(this.responseText);
        }
        else{
            noUserFound();
        }
    };
    xhttp.open("GET", "/json/user/getCurrentUser", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
}

function noUserFound() {
    //TODO: NO USER FOUND DO SOMETHING
}