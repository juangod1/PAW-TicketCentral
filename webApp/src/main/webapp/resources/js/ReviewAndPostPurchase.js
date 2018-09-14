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
        if (this.readyState == 4 && this.status == 200) {
            var transaction = JSON.parse(this.responseText);
            mainPostPurchase(transaction);
        }
    };
    xhttp.open("POST", "/json/transaction/confirmCheckout", true);
    xhttp.setRequestHeader("Content-type", "application/json");

    var seatNames = [];
    for(var seat in seatsArray){
        seatNames.push(seat.name);
    }

    var foodNames = [];
    // TODO: FOOD DETAILS

    var trans = {userDNI:usr.dni, screeningID:screeningIDnum,seatNames:seatNames,foodDetails:foodNames};
    xhttp.send(JSON.stringify(trans));
}

function mainPostPurchase(transactionID){
    // TODO: mandar mail
}

function setUpData(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var xhttp = new XMLHttpRequest();
            usr = JSON.parse(this.responseText);
        }
    };
    xhttp.open("GET", "/json/user/getCurrentUser", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
}