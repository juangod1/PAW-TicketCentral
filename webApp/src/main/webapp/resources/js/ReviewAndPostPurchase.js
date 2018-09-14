var seatsArray=[];

function mainReviewPurchase(){
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
    /*
    private String userDNI;
    private int screeningID;
    private List<String> seatNames;
    private List<String> foodDetails;*/

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            movies = JSON.parse(this.responseText);
            mainPostPurchase();
        }
    };
    xhttp.open("POST", "/json/transaction/confirmCheckout", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify());
    xhttp.send("");
}

function mainPostPurchase(transactionID){
    // TODO: mandar mail
}