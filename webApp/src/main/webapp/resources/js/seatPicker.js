var urlParams;

$("#document").ready(function(){
    mainSeatPicker();
});

function mainSeatPicker(){
    urlParams = getUrlVars();
    $("#purchase").on("click", function(event) {
        location.href="/purchaseReview?movieID=" + urlParams.movieID + "&time=" + urlParams.time + "&amount=" + urlParams.amount + "&seat=" + 10;
    });
}