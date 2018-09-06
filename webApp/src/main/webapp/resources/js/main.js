$("#document").ready(function(){
    main();
});

function main(){

 testAction();
}

function testAction() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var obj = JSON.parse(this.responseText);
            alert(obj[0].name); //imprime el nombre de la primer pelicula
        }
    };
    //LE PEGA AL ENDPOINT ../json/theatre/{id} con parametro {id} lol. Si hay un teatro con nombre lol se ejecuta el servicio de busqueda de peliculas por teatro y devuelve el json.
    xhttp.open("GET", "/json/movie/lol", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("");
}