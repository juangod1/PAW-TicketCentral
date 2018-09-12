var seatsArray=[];

function mainReviewPurchase(){
    var div = $("#purchaseReviewTextGoesHere");
    var seat;
    var newDiv;

    for(var i=0;i<wantedSeats;i++){
        console.log(wantedQueue);
        seat = wantedQueue.dequeue();
        seatsArray.push(seat);
        newDiv = $("" +
            "<h1 class=\"text-secondary text-uppercase mb-0\">- "
            + "Asiento " + seat.name + ", " + movieIDtoNamesMap[movieSelected] + " "
            + ticketsDate + "</h1>");
        div.append(newDiv);
    }
}

function confirmPurchase(){
    mainPostPurchase();
}

function mainPostPurchase(){

}