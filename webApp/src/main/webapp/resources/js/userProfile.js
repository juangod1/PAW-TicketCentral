function mainUserProfile(){
    $("#user_dni").text("DNI: "+user.dni);
    $("#user_name").text("Nombre: "+user.name);
    $("#user_surname").text("Apellido: "+user.surname);
    $("#user_mobile").text("Celular: "+user.mobile);
    $("#user_email").text("E-Mail: "+user.email);
}

function logout(){
    closePopup();
}