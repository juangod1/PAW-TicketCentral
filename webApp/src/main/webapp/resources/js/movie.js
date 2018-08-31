$("#document").ready(function(){
    mainMovie();
});

function mainMovie(){
    console.log("main");
    $("#purchase").on("click", function(event) {
        console.log("clicky");
        alert("comprado");
    });
}