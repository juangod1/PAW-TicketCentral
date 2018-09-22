function closePopup(){
    $.magnificPopup.close();
}

function openPopup(id){
    $.magnificPopup.open({
        items: {
            src: '#'+id
        },
        type: 'inline'
    });
}

function setLoggedUserButton(){
    var div = $("#loggedAs");
    div.removeAttr("href");
    div.empty();
    div.text(user.name);
    div.click(function() {
        openPopup("userProfile");
        mainUserProfile();
    });
}

function dateFormat(date){
    return date.getDate()+"/"+(date.getMonth()+1)+
        "/"+date.getFullYear()+"  "+date.getHours()+
        ":"+(date.getMinutes()<10?"0"+date.getMinutes():date.getMinutes())+"hs"}