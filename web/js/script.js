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
                document.getElementById("unreadMessagesAmount").innerHTML = json.unreadMessagesAmount;
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
                if (wishes.length == 0) {
                    return;
                }
                var table = document.getElementById("myWishes");
                table.replaceChild(document.createElement('TBODY'), table.tBodies[0]);
                var nameRow = table.insertRow(0);
                var columnName = document.createElement('th');
                columnName.innerHTML = msg.wishText;
                nameRow.appendChild(columnName);
                columnName = document.createElement('th');
                columnName.innerHTML = msg.ownerText;
                nameRow.appendChild(columnName);
                columnName = document.createElement('th');
                columnName.innerHTML = msg.dateText;
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
                    if (wish.date != null) {
                        column.innerHTML = json.dateValues[i];
                    }
                    column = row.insertCell(3);
                    if (wish.isFulfilled == true) {
                        if (wish.date != null) {
                            column.innerHTML = msg.wishFulfilledText;
                        } else {
                            column.innerHTML = msg.labelPerformed;
                        }
                    }
                    else {
                        column.innerHTML = "<p>" + msg.applicationMadeText + "</p>";
                        if (json.isPageOwner == true) {
                            column.innerHTML += "<form action=cancelApplication method=post>" +
                                "<input type=hidden name=pageCode value=1>" +
                                "<input type=hidden name=wishId value=" + wish.wishId + ">" +
                                "<input type=hidden name=userId value=" + currentUserId + ">" +
                                "<input type=submit value=" + msg.buttonCancel + ">" +
                                "</form>";
                        }
                    }

                }
            }
        }
        xmlhttp.open("GET", "updateMyWishes?userId=" + userId, true);
        xmlhttp.send();
    }, 1000);
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
                    column.innerHTML = chat.amountOfUnreadMessages;
                }
                }
                }
                xmlhttp.open("GET", "updateChats?page=" + currentPage, true);
                xmlhttp.send();
    }, 1000);
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
    }, 1000);
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
                        var formAction = "";
                        var candidate = wish.candidate;

                        if (listContainsUser(wish.candidates, currentUserId) == true) {
                            formAction = "<form action=cancelApplication method=post>" +
                                "<input type=hidden name=wishId value=" + wish.wishId + "><input type=hidden name=userId value=" + currentUserId + "><input type=submit value=" + msg.buttonCancel + "></form>";
                        } else {
                            if (candidate == null) {
                                formAction = "<form action=makeApplication method=post><input type=hidden name=wishId value=" + wish.wishId
                                    + "><input type=submit value="
                                    + "make application" + "></form>";
                            }
                        }
                        column.innerHTML = formAction;
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
                        }
                        var candidates = wish.candidates;
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
                                }

                            }
                        }
                    }

                }
            }
        }
        xmlhttp.open("GET", "updateWishes?userId=" + userId, true);
        xmlhttp.send();
    }, 1000);
}

function displayWishesTable(currentUserId) {
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
                        if(candidate == null) {
                            formAction = "<form action=deleteWish method=post><input type=hidden name=wishId value="
                                + wish.wishId + "><input type=submit value=" + msg.buttonDelete + "></form>";
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
                                + wish.wishId + "><input type=submit value=" + msg.buttonCancel + "></form>";
                            var columnActions = rowActions.insertCell(2);
                            columnActions.innerHTML = "<form action=wishMade method=post>" +
                                "<input type=hidden name=wishId value=" + wish.wishId + "><input type=submit value=" + "isDone" + "></form>";

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
                                        + "><input type=hidden name=userId value=" + candidate.userId + "><input type=submit value="
                                        + msg.buttonTake + "></form>";
                                    var columnActions = rowActions.insertCell(2);
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
        xmlhttp.open("GET", "updateWishes?userId=" + currentUserId, true);
        xmlhttp.send();
    }, 1000);
}








