<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <title></title><!--
    <meta http-equiv="Cache-Control" content="no-cache">    -->
    <meta http-equiv="Cache-Control" content="private">
    <script type="text/javascript" src="js/script.js"></script>
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/style.css" media="screen"/>

</head>
<body onload="displayButtonAddWish()">
<c:import url="header.jsp"/>
<button onclick="window.location.href = '${pageContext.request.contextPath}/jsp/changePassword.jsp'">
    <fmt:message key="change_password"/>
</button>
<h4>${userInfo.name}</h4>
<h4>${userInfo.surname}</h4>
<h5>${userInfo.country}</h5>
<h5>${userInfo.city}</h5>
<h5><fmt:formatDate value="${userInfo.dateOfBirth}"/></h5>
<a href="/editUserInfo"><fmt:message key="edit"/></a>
<h4><fmt:message key="abilities"/></h4>
<h5>${userInfo.abilities}</h5>
<c:choose>
    <c:when test="${not empty userInfo.abilities}">
        <a href="/editUserAbilities"><fmt:message key="edit"/></a>
    </c:when>
    <c:otherwise>
        <a href="/editUserAbilities"><fmt:message key="add_abilities"/></a>
    </c:otherwise>
</c:choose>
<h3>${status}</h3>

<div id="userPhoto">
    <c:choose>
        <c:when test="${not empty userInfo.photoName}">
            <img src="../img/userPhoto/${userInfo.photoName}" class="userPhoto"/>
        </c:when>
        <c:otherwise>
            <img src="../img/userPhoto/default.png" class="userPhoto"/>
        </c:otherwise>
    </c:choose>
    <button onclick="window.location.href = '${pageContext.request.contextPath}/jsp/changingData/changePhoto.jsp'">
        <fmt:message key="change_photo"/>
    </button>
</div>
<div>
    <form class="formWishes" action="fulfilledWishes" method="get">
        <input type="hidden" name="userId" value="${currentUserId}">
        <input type="submit" value="The wishes I've fulfilled"/>
    </form>
    <form class="formWishes" action="myFulfilledWishes" method="get">
        <input type="hidden" name="userId" value="${currentUserId}">
        <input type="submit" value="My fulfilled wishes"/>
    </form>
</div>
<div id="wishes_block">

</div>
<ctg:addingWishBlock count="${wishesCount}"/>
<script>
    setInterval(displayWishes, 1000);

    function createWishTable(json, currentUserId) {
        var wishes = json.wishes;
        var size = wishes.length;
        var msg = {
            wishesText: "<fmt:message key="wishes" />",
            buttonDelete: "<fmt:message key="delete" />",
            buttonTake: "<fmt:message key="take_application" />",
            buttonCancel: "<fmt:message key="cancel" />",
            labelPerformed: "<fmt:message key="wish_performed" />",
            labelApplications: "<fmt:message key="applications" />"
        };

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

                formAction = "<form action=deleteWish method=post><input type=hidden name=wishId value="
                        + wish.wishId + "><input type=submit value=" + msg.buttonDelete + "></form>";
                column.innerHTML = formAction;
                column = row.insertCell(1);
                column.innerHTML = wish["wish"];
                column = row.insertCell(2);
                var label = document.createElement('h5');

                if (candidate != null) {
                    label.innerText = msg.labelPerformed;
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
                var candidates = wish.candidates;
                if (candidates != null) {
                    var candidatesAmount = candidates.length;
                    if (candidatesAmount != 0) {
                        label.innerText = msg.labelApplications;
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
    function displayWishes() {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var answer = xmlhttp.responseText;
                //alert(answer);
                var json = JSON.parse(answer);
                var currentUserId = "${currentUserId }";
                createWishTable(json, currentUserId);

            }
        }
        xmlhttp.open("GET", "updateWishes?userId=" + "${currentUserId}", true);
        xmlhttp.send();
    }
</script>
</body>
</html>
