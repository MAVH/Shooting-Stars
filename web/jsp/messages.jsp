
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<fmt:setLocale value="${currentLocale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <title></title>
    <meta http-equiv="Cache-Control" content="private">

</head>
<body>

<c:import url="header.jsp"/>

<form action="sendMessage" method = "POST">
    <input type="hidden" name="chatId" value="${chatId}"/>
    <input type="textarea" name = "message"/>
    <input type="submit" value="<fmt:message key="send"/> "/>
</form>
<span id="info"></span>
<c:choose>
    <c:when test="${not empty messages}">
        <table class="table" id="messages">
            <c:forEach var="message" items="${messages}">
                <tr>
                    <td>

                        <a href="userPage?userId=${message.sender.userId}" class="<c:if test="${message.isLoggedInUser()}">
                                   userColor
                            </c:if>">
                            <ctg:userPhoto photoName="${message.sender.photoName}" photoClass="iconPhoto"/>
                            ${message.sender.name} ${message.sender.surname}
                        </a>

                        <p>
                            ${message.message}
                        </p>
                    </td>
                    <td>
                        <fmt:formatDate value="${message.date}"/>
                           <br/>
                          <fmt:formatDate value="${message.time}" type="time" timeStyle="short"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <ctg:messagesPager chatId="${chatId}" currentPage="${page}" generalAmount="${messagesAmount}"/>
    </c:when>
    <c:otherwise>
        <p>
            <fmt:message key="not_found"/>
        </p>
    </c:otherwise>
</c:choose>

<script type="text/javascript">
    /*
    window.onbeforeunload = function(e) {
        alert('!!!!');
        window.location.href="/getChats";
        return;
    };
    window.onunload = function() {
        alert('fagfg');
        window.location.href="/getChats";
    };
    function toChat() {
        alert('fagfg');
        window.location.href="/getChats";
    }
    window.addEventListener("unload", toChat);
    */
    setInterval(updateMessages, 10000);

    function updateMessages() {
        var page = "${page}";
        if(page != 1) {
            return;
        }
        var xmlhttp = new XMLHttpRequest();
        var chatId = "${chatId}";
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var answer = xmlhttp.responseText;
                //document.getElementById("info").innerHTML = answer;
                console.log(answer);
                var json = JSON.parse(answer);
                var messages = json.newMessages;
                var messagesAmount = messages.length;
                if(messagesAmount == 0) {
                    return;
                }
                var table = document.getElementById("messages");
                for(var i = 0; i < messagesAmount; i++) {
                    table.deleteRow(json.maxAmount - i - 1);
                }
                for(var i = 0; i < messages.length; i++) {
                    var message = messages[i];
                    var row = table.insertRow(i);
                    var column;
                    column = row.insertCell(0);
                    var link = document.createElement('a');
                    link.setAttribute('href', "userPage?userId=" + message.sender.userId);
                    var photoName = message.sender.photoName;
                    if(photoName == null) {
                        photoName = "default.png";
                    }
                    link.innerHTML = "<img src=../img/userPhoto/" + photoName + " class=iconPhoto />" +
                            message.sender.name + " " + message.sender.surname;
                    column.appendChild(link);
                    var p = document.createElement('p');
                    p.innerText = message["message"];
                    column.appendChild(p);
                    column = row.insertCell(1);
                    column.innerHTML = json.dateValues[i] + "<br/>" + json.timeValues[i];
                }
            }
        }
        xmlhttp.open("GET", "newMessages?chatId=" + chatId, true);
        xmlhttp.send();
    }
</script>
</body>
</html>
