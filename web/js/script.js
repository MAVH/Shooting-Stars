document.addEventListener("DOMContentLoaded", function(event) {
    
});

var interval = 20000;

function displayHiddenForm() {
    document.getElementsByTagName('form').classList.remove("hidden");
}

function getEmail() {
    document.getElementById("buttonChangeEmail").onclick = function() {
        document.getElementById("formChangeEmail").classList.remove("hidden");
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var answer = xmlhttp.responseText;
                var json = JSON.parse(answer);
                document.getElementById("email").setAttribute('value',json.email);
            }
        }
        xmlhttp.open("GET", "getEmail", true);
        xmlhttp.send();
        document.getElementById("buttonChangeEmail").classList.add("hidden");
    }
}
function displayButtonAddWish() {
    if (document.getElementById("button_add_wish") != null) {
        document.getElementById("button_add_wish").classList.remove("hidden");
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
            if (document.getElementById("add_wish_form") != null) {
                document.getElementById("add_wish_form").classList.remove("hidden");
            }
        }
    }
}

function getEmptyWishesTable() {
    var table;
    if(document.getElementById("wishes_table") == null) {
        table = document.createElement('table');
        table.setAttribute('id','wishes_table');
        //table.className = "table";
        table.classList.add("wishesTable");
        table.classList.add("table");

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
    link.innerHTML = "<img src=../../img/userPhoto/" + photoName + " class=iconPhoto />" +
        user.name + " " + user.surname;
    return link;
}

function displayAmount() {
    //alert('in');
    setInterval(function () {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var answer = xmlhttp.responseText;
                var json = JSON.parse(answer);
                var amount = json.unreadMessagesAmount;
                if(amount != 0) {
                    document.getElementById("unreadMessagesAmount").classList.add("messages_amount");
                    document.getElementById("unreadMessagesAmount").innerHTML = amount;
                } else {
                    document.getElementById("unreadMessagesAmount").classList.remove("messages_amount");
                }
            }
        }
        xmlhttp.open("GET", "getUnreadMessagesAmount", true);
        xmlhttp.send();
    }, 1000);

}

function displayFulfilledWishes(userId,msg) {
    setInterval(function() {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var answer = xmlhttp.responseText;
                var json = JSON.parse(answer);
                var wishes = json.wishes;
                if (wishes.length == 0) {
                    return;
                }
                var table = document.getElementById("fulfilledWishes");
                table.replaceChild(document.createElement('TBODY'), table.tBodies[0]);
                var nameRow = table.insertRow(0);
                var columnName = document.createElement('th');
                columnName.innerHTML = msg.wishText;
                nameRow.appendChild(columnName);
                columnName = document.createElement('th');
                columnName.innerHTML = msg.madeByText;
                nameRow.appendChild(columnName);
                columnName = document.createElement('th');
                columnName.innerHTML = msg.dateText;
                nameRow.appendChild(columnName);
                for (var i = 0; i < wishes.length; i++) {
                    var wish = wishes[i];
                    var row = table.insertRow(i + 1);
                    var column;
                    column = row.insertCell(0);
                    column.innerHTML = wish["wish"];
                    column = row.insertCell(1);
                    var link = createUserLink(wish.candidate);
                    column.appendChild(link);
                    column = row.insertCell(2);
                    column.innerHTML = json.dateValues[i];
                }
            }
        }
        xmlhttp.open("GET", "updateFulfilledWishes?userId=" + userId, true);
        xmlhttp.send();

    }, 10000);
}

function displayMyWishes(userId, currentUserId, msg) {
    setInterval(function() {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var answer = xmlhttp.responseText;
                var json = JSON.parse(answer);
                var wishes = json.wishes;
                var table = document.getElementById("myWishes");
                if(table.tBodies[0] != null) {
                    table.replaceChild(document.createElement('TBODY'), table.tBodies[0]);
                }
                if (wishes.length == 0) {
                    var row = table.insertRow(0);
                    row.insertCell(0).innerHTML = msg.notFoundMessage;
                    return;
                }
                var nameRow = table.insertRow(0);
                var columnName = document.createElement('th');
                columnName.innerHTML = msg.wishText;
                nameRow.appendChild(columnName);
                columnName = document.createElement('th');
                columnName.innerHTML = msg.ownerText;
                nameRow.appendChild(columnName);
                columnName = document.createElement('th');
                columnName.innerHTML = msg.statusText;
                nameRow.appendChild(columnName);
                for (var i = 0; i < wishes.length; i++) {
                    var wish = wishes[i];
                    var row = table.insertRow(i + 1);
                    var column;
                    column = row.insertCell(0);
                    column.innerHTML = wish["wish"];
                    column = row.insertCell(1);
                    var link = createUserLink(wish.owner);
                    column.appendChild(link);
                    column = row.insertCell(2);
                    if (wish.isFulfilled == true) {
                        if (wish.date != null) {
                            column.innerHTML = msg.wishFulfilledText + "<br/>";
                            column.innerHTML += json.dateValues[i];
                        } else {
                            column.innerHTML = msg.labelPerformed;
                            if(json.isPageOwner == true) {
                                var formAction = "<form action=cancelMakingWish method=post><input type=hidden name=wishId value="
                                    + wish.wishId + "><input type=hidden name=pageCode value=1><input type=hidden name=userId value="
                                    + currentUserId + "><input type=submit class=cancelButton value=></form>";
                                column.innerHTML += formAction;
                            }
                        }
                    }
                    else {
                        column.innerHTML = "<p>" + msg.applicationMadeText + "</p>";
                        if (json.isPageOwner == true) {
                            column.innerHTML += "<form action=cancelApplication method=post>" +
                                "<input type=hidden name=pageCode value=1>" +
                                "<input type=hidden name=wishId value=" + wish.wishId + ">" +
                                "<input type=hidden name=userId value=" + currentUserId + ">" +
                                "<input type=submit class=cancelButton value=>" +
                                "</form>";
                        }
                    }

                }
            }
        }
        xmlhttp.open("GET", "updateMyWishes?userId=" + userId, true);
        xmlhttp.send();
    }, interval);
}

function displayChats(currentPage) {
    setInterval(function() {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var answer = xmlhttp.responseText;
                var json = JSON.parse(answer);
                var chats = json.chats;
                if (chats.length == 0) {
                    return;
                }
                var table = document.getElementById("chats");
                table.replaceChild(document.createElement('TBODY'), table.tBodies[0])
                for (var i = 0; i < chats.length; i++) {
                    var chat = chats[i];
                    var row = table.insertRow(i);
                    var column;
                    column = row.insertCell(0);
                    var link = document.createElement('a');
                    link.setAttribute('href', "getMessages?chatId=" + chat.chatId);
                    var photoName = chat.otherParticipant.photoName;
                    if (photoName == null) {
                        photoName = "default.png";
                    }
                    link.innerHTML = "<img src=../../img/userPhoto/" + photoName + " class=iconPhoto />" +
                        chat.otherParticipant.name + " " + chat.otherParticipant.surname;
                    column.appendChild(link);
                    column = row.insertCell(1);
                    if(chat.amountOfUnreadMessages != 0) {
                        column.innerHTML = chat.amountOfUnreadMessages;
                    }
                }
                }
                }
                xmlhttp.open("GET", "updateChats?page=" + currentPage, true);
                xmlhttp.send();
    }, interval);
}

function updateMessages(chatId,page) {
    setInterval(function() {
        if (page != 1) {
        return;
    }
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var answer = xmlhttp.responseText;
            //console.log(answer);
            var json = JSON.parse(answer);
            var messages = json.newMessages;
            var messagesAmount = messages.length;
            if (messagesAmount == 0) {
                return;
            }
            var table = document.getElementById("messages");
            for (var i = 0; i < messagesAmount; i++) {
                table.deleteRow(json.maxAmount - i - 1);
            }
            for (var i = 0; i < messages.length; i++) {
                var message = messages[i];
                var row = table.insertRow(i);
                var column;
                column = row.insertCell(0);
                var link = createUserLink(message.sender);
                column.appendChild(link);
                var p = document.createElement('p');
                p.innerHTML = message["message"];
                column.appendChild(p);
                column = row.insertCell(1);
                column.innerHTML = json.dateValues[i] + "<br/>" + json.timeValues[i];
            }
        }
    }
    xmlhttp.open("GET", "newMessages?chatId=" + chatId, true);
    xmlhttp.send();
    }, interval);
}

function displayUserWishesTable(userId, currentUserId, msg) {
    setInterval(function() {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var answer = xmlhttp.responseText;
                //alert(answer);
                var json = JSON.parse(answer);
                var wishes = json.wishes;
                var size = wishes.length;
                if (size != 0) {
                    var table = getEmptyWishesTable();
                    table.classList.add("wishesTable");
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
                    var row;
                    for (var i = 0; i < size; i++) {
                        var wish = wishes[i];
                        row = table.insertRow(i + 1);
                        column = row.insertCell(0);
                        column.innerHTML = wish["wish"];
                        column = row.insertCell(1);
                        var label = document.createElement('h5');
                        var candidate = wish.candidate;
                        if (candidate != null) {
                            label.innerHTML = msg.labelPerformed;
                            column.appendChild(label);
                            tableActions = document.createElement('table');
                            tableActions.setAttribute('id', 'candidate');
                            column.appendChild(tableActions);
                            var rowActions = tableActions.insertRow(0);
                            var columnActions = rowActions.insertCell(0);
                            columnActions.appendChild(createUserLink(candidate));
                            if (candidate.userId == currentUserId) {
                            columnActions = rowActions.insertCell(1);
                            columnActions.innerHTML = "<form action=cancelMakingWish method=post><input type=hidden name=wishId value="
                                    + wish.wishId + "><input type=hidden name=userId value=" + candidate.userId
                                    + "><input type=submit class=cancelButton value=></form>";
                            }
                        }
                        var candidates = wish.candidates;
                        var isApplicant = false;
                        if (candidates != null) {
                            var candidatesAmount = candidates.length;
                            if (candidatesAmount != 0) {
                                label.innerHTML = msg.labelApplications;
                                column.appendChild(label);
                                tableActions = document.createElement('table');
                                tableActions.setAttribute('id', 'candidates');
                                column.appendChild(tableActions);
                                for (var j = 0; j < candidatesAmount; j++) {
                                    var rowActions = tableActions.insertRow(j);
                                    var columnActions = rowActions.insertCell(0);
                                    candidate = candidates[j];
                                    columnActions.appendChild(createUserLink(candidate));
                                    if(candidate.userId == currentUserId) {
                                        isApplicant = true;
                                        columnActions = rowActions.insertCell(1);
                                        columnActions.innerHTML = "<form action=cancelApplication method=post>" +
                                            "<input type=hidden name=wishId value=" + wish.wishId
                                            + "><input type=hidden name=userId value=" + currentUserId +
                                            "><input type=submit class=cancelButton value=></form>";
                                    }
                                }

                            }
                        }
                        column = row.insertCell(2);
                        var formAction = "";
                        candidate = wish.candidate;
                        if (candidate == null && !isApplicant) {
                                formAction = "<form action=makeApplication method=post><input type=hidden name=wishId value=" + wish.wishId
                                    + "><input type=submit value="
                                    + "make application" + "></form>";
                                column.innerHTML = formAction;
                        }
                    }

                }
            }
        }
        xmlhttp.open("GET", "updateWishes?userId=" + userId, true);
        xmlhttp.send();
    }, interval);
}

function displayWishesTable(currentUserId) {
    var count = 0;
    setInterval(function() {

        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var answer = xmlhttp.responseText;
                //alert(answer);
                var json = JSON.parse(answer);
                var wishes = json.wishes;
                var size = wishes.length;
                if (size != 0) {
                    var table = getEmptyWishesTable();

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
                        var candidates = wish.candidates;
                        if (candidate == null) {
                            formAction = "<form action=deleteWish method=post><input type=hidden name=wishId value="
                                + wish.wishId + "><input type=submit class=deleteButton value= ></form>";
                            column.innerHTML = formAction;
                        }
                        column = row.insertCell(1);
                        column.innerHTML = wish["wish"];
                        column = row.insertCell(2);
                        var label = document.createElement('h5');

                        if (candidate != null) {
                            label.innerHTML = msg.labelPerformed;
                            column.appendChild(label);
                            tableActions = document.createElement('table');
                            tableActions.setAttribute('id', 'candidate');
                            column.appendChild(tableActions);
                            var rowActions = tableActions.insertRow(0);
                            var columnActions = rowActions.insertCell(0);
                            columnActions.appendChild(createUserLink(candidate));
                            columnActions = rowActions.insertCell(1);
                            columnActions.innerHTML = "<form action=cancelMakingWish method=post><input type=hidden name=wishId value="
                                + wish.wishId + "><input type=hidden name=userId value="
                                + candidate.userId + "><input type=submit class=cancelButton value= ></form>";
                            var columnActions = rowActions.insertCell(2);
                            columnActions.innerHTML = "<form action=wishMade method=post>" +
                                "<input type=hidden name=wishId value=" + wish.wishId + ">" +
                                "<input type=submit class=fulfilledButton value= ></form>";

                        }

                        if (candidates != null) {
                            var candidatesAmount = candidates.length;
                            if (candidatesAmount != 0) {
                                label.innerHTML = msg.labelApplications;
                                column.appendChild(label);
                                tableActions = document.createElement('table');
                                tableActions.setAttribute('id', 'candidates');
                                column.appendChild(tableActions);
                                for (var j = 0; j < candidatesAmount; j++) {
                                    var rowActions = tableActions.insertRow(j);
                                    var columnActions = rowActions.insertCell(0);
                                    candidate = candidates[j];
                                    columnActions.appendChild(createUserLink(candidate));
                                    columnActions = rowActions.insertCell(1);
                                    columnActions.innerHTML = "<form action=acceptApplication method=post> " +
                                        "<input type=hidden name=wishId value=" + wish.wishId
                                        + "><input type=hidden name=userId value=" + candidate.userId + "><input type=submit class=fulfilledButton value= ></form>";
                                    var columnActions = rowActions.insertCell(2);
                                    columnActions.innerHTML = "<form action=cancelApplication method=post>" +
                                        "<input type=hidden name=wishId value=" + wish.wishId
                                        + "><input type=hidden name=userId value=" + candidate.userId + "><input type=submit class='cancelButton' value= ></form>";

                                }
                            }
                        }
                    }
                }
                if(count == 0) {
                    displayButtonAddWish();
                }
                count = 1;

            }
        }

        xmlhttp.open("GET", "updateWishes?userId=" + currentUserId, true);
        xmlhttp.send();
    }, interval);

}








