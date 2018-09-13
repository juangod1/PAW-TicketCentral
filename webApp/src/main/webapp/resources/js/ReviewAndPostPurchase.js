var seatsArray=[];

function mainReviewPurchase(){
    var div = $("#purchaseReviewTextGoesHere");
    var seat;
    var newDiv;

    for(var i=0;i<wantedSeats;i++){
        seat = wantedQueue.dequeue();
        seatsArray.push(seat);
        newDiv = $("" +
            "<h1 class=\"text-secondary text-uppercase mb-0\">- "
            + "Asiento " + seat.name + "<br>" + movieIDtoNamesMap[movieSelected] + "<br>"
            + ticketsDate + "</h1>");
        div.append(newDiv);
    }
}

function confirmPurchase(){
    mainPostPurchase();
}

function mainPostPurchase(){

}