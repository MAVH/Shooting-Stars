
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<fmt:setLocale value="${currentLocale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <title></title>
</head>
<body>
<c:import url="header.jsp"/>
<c:choose>
    <c:when test="${not empty chats}">
        <table class="table" id="chats">
            <c:forEach var="chat" items="${chats}">
                 <tr>
                     <td>
                         <a href="getMessages?chatId=${chat.chatId}">
                            <ctg:userPhoto photoName="${chat.otherParticipant.photoName}" photoClass="iconPhoto" />
                            ${chat.otherParticipant.name} ${chat.otherParticipant.surname}
                         </a>
                     </td>
                     <td>
                         ${chat.amountOfUnreadMessages}
                     </td>
                 </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <p>
            <fmt:message key="not_found"/>
        </p>
    </c:otherwise>
</c:choose>
<script>
    setInterval(displayChats, 10000);
    function displayChats() {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var answer = xmlhttp.responseText;
                var json = JSON.parse(answer);
                var chats = json.chats;
                var table = document.getElementById("chats");
                table.replaceChild(document.createElement('TBODY'), table.tBodies[0])
                console.log(chats);
                for(var i = 0; i < chats.length; i++) {
                    var chat = chats[i];
                    var row = table.insertRow(i);
                    var column;
                    column = row.insertCell(0);
                    var link = document.createElement('a');
                    link.setAttribute('href', "getMessages?chatId=" + chat.chatId );
                    var photoName = chat.otherParticipant.photoName;
                    if(photoName == null) {
                        photoName = "default.png";
                    }
                    link.innerHTML = "<img src=../img/userPhoto/" + photoName + " class=iconPhoto />" +
                            chat.otherParticipant.name + " " + chat.otherParticipant.surname;
                    column.appendChild(link);
                    column = row.insertCell(1);
                    column.innerHTML = chat.amountOfUnreadMessages;
                }
            }
        }
        xmlhttp.open("GET", "updateChats", true);
        xmlhttp.send();
    }
</script>
</body>
</html>
