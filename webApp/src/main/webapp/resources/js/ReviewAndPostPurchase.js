var seatsArray=[];
var usr;

function mainReviewPurchase(){
    setUpData();
    var div = $("#purchaseReviewTextGoesHere");
    var seat;
    var newDiv;

    for(var i=0;i<wantedSeats;i++){
        seat = wantedQueue.dequeue();
        seatsArray.push(seat);
        newDiv = $("" +
            "<h1 class=\"text-secondary text-uppercase mb-0\">$100 - "
            + "Asiento " + seat.name + "<br>" + movieIDtoNamesMap[movieSelected] + "<br>"
            + ticketsDate + "<br></h1>");
        div.append(newDiv);
    }

    //TODO: agregar comidas tambien
    /*
    for(var i=0;i<foodAmount;i++){
        var food = fods[i];
        newDiv = $("" +
        "<h1 class=\"text-secondary text-uppercase mb-0\"> - " + food.name + "</h1>");
        div.append(newDiv);
    }
     */
}

function confirmPurchase(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState!=4){
            return;
        }
        if (this.status == 200) {
            var transaction = JSON.parse(this.responseText);
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

    var foodNames = [];
    // TODO: FOOD DETAILS

    var trans = {userDNI:usr.dni, screeningID:screeningIDnum,seatNames:seatNames,foodDetails:foodNames};
    xhttp.send(JSON.stringify(trans));
}

function transactionFailed(){
    //TODO: TRANSACTION FAILED SHOW IT TO USER
}

function mainPostPurchase(transactionID){
    // TODO: mandar mail
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