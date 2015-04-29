document.addEventListener("DOMContentLoaded", function(event) {
    
});

function addPropertiesOnUserPage() {	
    document.getElementById("wishes_table").classList.add("table-striped");
    document.getElementById("wishes_table").classList.add("table-hover");
}
    function displayButtonAddWish() {
        document.getElementById("button_add_wish").classList.add("btn-default");
        document.getElementById("button_add_wish").onclick = function () {
            var fields = (document).getElementsByName("wish");
            for (var i = 0; i < fields.length; i++) {
                if (fields[i].classList.contains("hidden")) {
                    fields[i].classList.remove("hidden");
                    if (i == fields.length - 1) {
                        document.getElementById("button_add_wish").classList.add("hidden");
                    }
                    break;
                }
            }
            document.getElementById("add_wish_form").classList.remove("hidden");
        }
    }
function getEmptyWishesTable() {
    var table;
    if(document.getElementById("wishes_table") == null) {
        table = document.createElement('table');
        table.setAttribute('id','wishes_table');
        table.className = "table";
        document.getElementById("wishes_block").appendChild(table);
    } else {
        table = document.getElementById("wishes_table");
        table.replaceChild(document.createElement('TBODY'), table.tBodies[0]);
    }
    return table;
}

function listContainsUser(usersList,userId) {
    //alert(usersList.length);
    if(usersList.length == 0) {
        return false;
    }
    for(var i = 0; i < usersList.length; i++) {
        if(usersList[i].userId == userId) {
            return true;
        }
    }
    return false;
}
function getPhotoName(photoName) {
    if (photoName == null) {
        photoName = "default.png";
    }
    return photoName;
}
function createUserLink(user) {
    var link = document.createElement('a');
    link.setAttribute('href', "userPage?userId=" +user.userId);
    var photoName = user.photoName;
    if (photoName == null) {
        photoName = "default.png";
    }
    link.innerHTML = "<img src=../img/userPhoto/" + photoName + " class=iconPhoto />" +
        user.name + " " + user.surname;
    return link;
}








