$("#document").ready(function(){
    mainMovie();
});

var urlParams;

function mainMovie(){
    urlParams = getUrlVars();
    $("#purchase").on("click", function(event) {
        var time = $("#dropdownSchedule");
        var amount = $("#ticketsAmount");
        location.href="/seatPicker?movieID=" + urlParams.movieID + "&time=" + 1200 + "&amount=" + amount.val();
    });
}