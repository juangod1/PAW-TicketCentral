var maxRows = 12;
var maxCols = 16;
var urlParams;
var chosenSeats=[];

$("#document").ready(function(){
    mainSeatPicker();
});

function mainSeatPicker(){
    urlParams = getUrlVars();
    $("#purchase").on("click", function(event) {
        location.href="/purchaseReview?movieID=" + urlParams.movieID + "&time=" + urlParams.time + "&amount=" + urlParams.amount + "&seat=" + 10;
    });

    var seat;
    for(var i=1;i<=maxRows;i++){
        for(var j=1;j<=maxCols;j++){
            seat=$("#"+i+"-"+j);
            seat.on("click", function(event) {chosenSeats.push(this.id);});
        }
    }
}