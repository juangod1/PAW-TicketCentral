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
        if (this.readyState == 4 && this.status == 200) {
            var transactions = JSON.parse(this.responseText);
            buildTransactions(transactions);
        }
        else{
            nothingFoundMessage();
        }
    };
    xhttp.open("GET", "/json/transaction/getTransactionsByUser/"+adminInput, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
}

function buildTransactions(transactions){
    destroyTransactions();
    var transactionDiv = document.getElementById("transaction-div");
    for(var i=0; i<transactions.length; i++)
    {
        var transaction = transactions[i];
        var transactionElement = document.createElement("div");
        transactionElement.setAttribute("class","col-xs-12 col-sm-6 col-md-4 col-lg-3");

        var codeSpan = document.createElement("span");
        codeSpan.innerText="Codigo: "+transaction.id;
        transactionElement.appendChild(codeSpan);

        var paidSpan = document.createElement("span");
        paidSpan.innerText="Pago? "+((transaction.paid)?"Si":"No");
        transactionElement.appendChild(paidSpan);

        var priceSpan = document.createElement("span");
        priceSpan.innerText = "Precio: "+ transaction.price;
        transactionElement.appendChild(priceSpan);

        var seatSpan = document.createElement("span");
        seatSpan.innerText = "Asientos: "+ transaction.seat;
        transactionElement.appendChild(seatSpan);

        var dniSpan = document.createElement("span");
        dniSpan.innerText = "DNI: "+transaction.id;
        transactionElement.appendChild(dniSpan);

        if(undefined!=transaction.food && transaction.food !=null)
        {
            var foodSpan = document.createElement("span");
            foodSpan.innerText = "Comida: "+transaction.food;
            transactionElement.appendChild(foodSpan);
        }


        transactionDiv.appendChild(transactionElement);
    }
}

function destroyTransactions(){
    var transactionDiv = document.getElementById("transaction-div");
    transactionDiv.innerHTML="";
}

function nothingFoundMessage()
{
    var transactionDiv = document.getElementById("transaction-div");
    destroyTransactions()
    transactionDiv.innerText="No se encontraron transacciones para ese usuario!";

}