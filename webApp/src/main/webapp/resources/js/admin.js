$("#document").ready(function(){
    setUpAdminPage();
});

function setUpAdminPage() {
    var transactionButton = document.getElementById("transaction-button");
    transactionButton.addEventListener('click',executeQuery,false);

}

function executeQuery() {
    var transactionInput = document.getElementById("transaction-input");
    var adminInput = transactionInput.value;

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState!=4){
            return;
        }
        if (this.status == 200) {
            var transaction = JSON.parse(this.responseText);
            var transactions =[];
            transactions.push(transaction)
            buildTransactions(transactions);
        }
        else{
            nothingFoundMessage();
        }
    };
    xhttp.open("GET", "/json/transaction/getTransactionById/"+adminInput, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
}

function buildTransactions(transactions){
    destroyTransactions();
    var transactionDiv = $("#transaction-div");
    for(var i=0; i<transactions.length; i++)
    {
        var transaction = transactions[i];
        var transactionElement = $("<div class='row'></div>");

        var codeSpan = $("<div class='col-lg-2'></div>");
        codeSpan.text("Codigo: "+transaction.id);
        transactionElement.append(codeSpan);

        var paidSpan = $("<div class='col-lg-2'></div>");
        paidSpan.text("Pago? "+((transaction.paid)?"Si":"No"));
        transactionElement.append(paidSpan);

        var priceSpan = $("<div class='col-lg-2'></div>");
        priceSpan.text("Precio: "+ transaction.price);
        transactionElement.append(priceSpan);

        var seatSpan = $("<div class='col-lg-2'></div>");
        seatSpan.text("Asientos: "+ transaction.seat);
        transactionElement.append(seatSpan);

        var idSpan = $("<div class='col-lg-2'></div>");
        idSpan.text("Id: "+transaction.userId);
        transactionElement.append(idSpan);

        var dniSpan = $("<div class='col-lg-2'></div>");
        dniSpan.text("Dni: "+getUserDni(transaction.userId));
        transactionElement.append(dniSpan);

        if(undefined!=transaction.food && transaction.food !=null)
        {
            var foodSpan = $("<div class='col-lg-2'></div>");
            foodSpan.text("Comida: "+transaction.food);
            transactionElement.append(foodSpan);
        }


        transactionDiv.append(transactionElement);
    }
}

function getUserDni(userid){
    var retvalue;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState!=4){
            return;
        }
        if (this.status == 200) {
            var user = JSON.parse(this.responseText);
            retvalue=user.dni;
        }
        else{
            retvalue="No encontrado";
        }
    };
    xhttp.open("GET", "/json/user/getUserById/"+userid, false);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
    return retvalue;
}

function destroyTransactions(){
    var transactionDiv = document.getElementById("transaction-div");
    transactionDiv.innerHTML="";
}

function nothingFoundMessage()
{
    var transactionDiv = document.getElementById("transaction-div");
    destroyTransactions();
    transactionDiv.innerText="No se encontraron transacciones para ese usuario!";

}