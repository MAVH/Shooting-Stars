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
function createWishTable(json, isProfilePage, currentUserId,msg) {
            var wishes = json.wishes;
            var size = wishes.length;

            if (size != 0) {
                /*
                 var tableWishes = document.createElement('table');
                 tableWishes.setAttribute('id','wishes_table');
                 tableWishes.className = "table";
                 document.getElementById("wishes_block").appendChild(tableWishes);
                 */
                var table = document.getElementById("wishes_table");
                table.replaceChild(document.createElement('TBODY'), table.tBodies[0])
                var nameRow = table.insertRow(0);
                var columnName = document.createElement('th');
                nameRow.appendChild(columnName);
                columnName = document.createElement('th');
                columnName.innerHTML = msg.wishesText;
                nameRow.appendChild(columnName);
                columnName = document.createElement('th');
                nameRow.appendChild(columnName);
                var column;
                var tableActions;
                for (var i = 0; i < size; i++) {
                    var wish = wishes[i];
                    row = table.insertRow(i + 1);
                    column = row.insertCell(0);
                    var formAction;
                    var candidate = wish.candidate;
                    if (isProfilePage) {
                        formAction = "<form action=deleteWish method=post><input type=hidden name=wishId value="
                                + wish.wishId + "><input type=submit value=" + msg.buttonDelete + "></form>";
                    }
                    else {
                        if (listContainsUser(wish.candidates, currentUserId)) {
                            formAction = "<form action=cancelApplication method=post><input type=hidden name=pageCode value=0>" +
                                    "<input type=hidden name=wishId value=" + wish.wishId;
                            +"><input type=hidden name=userId value=" + currentUserId + "><input type=submit value="
                            + "Cancel my application" + "></form>";
                        } else {
                            if (candidate == null) {
                                formAction = "<form action=makeApplication method=post><input type=hidden name=wishId value=" + wish.wishId
                                        + "><input type=submit value="
                                        + "make application" + "></form>";
                            }
                        }
                    }
                    column.innerHTML = formAction;
                    column = row.insertCell(1);
                    column.innerHTML = wish["wish"];
                    column = row.insertCell(2);
                    var label = document.createElement('h5');

                    if (candidate != null) {
                        label.innerText = msg.labelPerformed;
                        column.appendChild(label);
                        column.appendChild(createUserLink(candidate));
                        if (isProfilePage) {
                            tableActions = document.createElement('table');
                            tableActions.setAttribute('id', 'candidate_actions');
                            column.appendChild(tableActions);
                            var rowActions = tableActions.insertRow(0);
                            var columnActions = rowActions.insertCell(0);
                            columnActions.innerHTML = "<form action=cancelMakingWish method=post><input type=hidden name=wishId value="
                                    + wish.wishId + "><input type=submit value=" + msg.buttonCancel + "></form>";
                            var columnActions = rowActions.insertCell(1);
                            columnActions.innerHTML = "<form action=wishMade method=post>" +
                                    "<input type=hidden name=wishId value=" + wish.wishId + "><input type=submit value=" + "isDone" + "></form>";
                        }

                    }
                    var candidates = wish.candidates;
                    if (candidates != null) {
                        var candidatesAmount = candidates.length;
                        if (candidatesAmount != 0) {
                            label.innerText = msg.labelApplications;
                            column.appendChild(label);
                            for (var j = 0; j < candidatesAmount; j++) {
                                candidate = candidates[j];
                                column.appendChild(createUserLink(candidate));

                                if (isProfilePage) {
                                    tableActions = document.createElement('table');
                                    tableActions.setAttribute('id', 'candidate_actions');
                                    column.appendChild(tableActions);
                                    var rowActions = tableActions.insertRow(0);
                                    var columnActions = rowActions.insertCell(0);
                                    columnActions.innerHTML = "<form action=acceptApplication method=post> " +
                                            "<input type=hidden name=wishId value=" + wish.wishId
                                            + "><input type=hidden name=userId value=" + candidate.userId + "><input type=submit value="
                                            + msg.buttonTake + "></form>";
                                    var columnActions = rowActions.insertCell(1);
                                    columnActions.innerHTML = "<form action=cancelApplication method=post>" +
                                            "<input type=hidden name=wishId value=" + wish.wishId
                                            + "><input type=hidden name=userId value=" + candidate.userId + "><input type=submit value="
                                            + msg.buttonCancel + "></form>";
                                }
                            }

                        }
                    }
                }

            }
        }







