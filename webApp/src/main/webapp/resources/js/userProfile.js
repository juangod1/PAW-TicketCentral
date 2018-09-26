function mainUserProfile(){
    $("#user_dni").text("DNI: "+user.dni);
    $("#user_name").text("Nombre: "+user.name);
    $("#user_surname").text("Apellido: "+user.surname);
    $("#user_mobile").text("Celular: "+user.mobile);
    $("#user_email").text("E-Mail: "+user.email);
    addTransactions();
}

function addTransactions(){
    var purchases = $("#user_purchases");
    purchases.append(getLoading());
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState!=4){
            return;
        }
        if (this.status == 200) {
            var objs = JSON.parse(this.responseText);
            purchases.empty();
            var b = $("<a></a>");
            b.html("<br/><br/>" + "Compras realizadas" +"<br/><br/>");
            purchases.append(b);
            for(var i = 0 ; i<objs.length;i++){
                var obj = objs[i];
                console.log(obj);
                var str = "$"+obj.price+" "+dateFormatdate(obj.date)+" Código: "+obj.id;
                var a = $("<a></a>");
                a.html(str+"<br/>");
                purchases.append(a);
            }
        }
        else{
        }
    };

    xhttp.open("GET", "/json/transaction/getMyTransactions/", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
}

function logout(){
    closePopup();
}