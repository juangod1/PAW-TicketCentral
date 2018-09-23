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

function normalizeDate(MyDate){
    var MyDateString = ('0' + MyDate.getDate()).slice(-2) + '-'
        + ('0' + (MyDate.getMonth()+1)).slice(-2) + '-'
        + MyDate.getFullYear();
    return MyDateString
}

function dateFormat(screening){
    var MyDate = new Date(0);
    MyDate.setUTCMilliseconds(screening.time);

    return normalizeDate(MyDate);
}

function hourFormat(screening) {
    var date = new Date(0);
    date.setUTCMilliseconds(screening.time);
    return date.getHours()+ ":"+(date.getMinutes()<10?"0"+date.getMinutes():date.getMinutes())+"hs";
}