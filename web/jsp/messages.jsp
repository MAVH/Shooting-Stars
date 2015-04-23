
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
<body onunload="toChat()">

<c:import url="header.jsp"/>

<form action="sendMessage" method = "POST">
    <input type="hidden" name="chatId" value="${chatId}"/>
    <input type="textarea" name = "message"/>
    <input type="submit" value="<fmt:message key="send"/> "/>
</form>

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
</script>
</body>
</html>
