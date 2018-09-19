function closePopup(id){
    $("#"+id).magnificPopup('close');
}

function openPopup(id){
    $.magnificPopup.open({
        items: {
            src: '#'+id
        },
        type: 'inline'
    });
}

function dateFormat(date){
    return date.getDate()+"/"+(date.getMonth()+1)+
        "/"+date.getFullYear()+"  "+date.getHours()+
        ":"+(date.getMinutes()<10?"0"+date.getMinutes():date.getMinutes())+"hs"}