/* source: http://papermashup.com/read-url-get-variables-withjavascript/ */
function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function checkIfSeatIsValid(row, column){
    return (row > 0) && (row <= seatRows) && (column > 0) && (column <= seatColumns);
}