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


    mainPostPurchase();
    // TODO: mandar mail
}

function mainPostPurchase(){

}