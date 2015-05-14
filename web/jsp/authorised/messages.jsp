<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head>
        <title><fmt:message key="messages"/></title>
        <meta http-equiv="Cache-Control" content="private">
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
        <script>
            updateMessages(${chatId}, "${page}");
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
        </script>
    </head>
    <body>
        <c:import url="../partial/header.jsp"/>
        <c:import url="../partial/menu.jsp"/>
        <form action="sendMessage" method="POST" class = "sendMessageBlock">
            <input type="hidden" name="chatId" value="${chatId}"/>
            <textarea name="message" class="form-control"></textarea>
            <input type="submit" class = "sendRealMessageButton" value="<fmt:message key="send"/> "/>
        </form>
        <span id="info"></span>
        <table class="table messageTable" id="messages">
        <c:choose>
            <c:when test="${not empty messages}">

                    <c:forEach var="message" items="${messages}">
                        <tr class="<c:if test="${!message.isLoggedInUser()}">userColor </c:if>">
                            <td>
                                <a class="messageSender" href="userPage?userId=${message.sender.userId}">
                                    <ctg:userPhoto photoName="${message.sender.photoName}" photoClass="iconPhoto"/>
                                        ${message.sender.name} ${message.sender.surname}
                                </a>
                                <p class="messageText">${message.message}</p>
                            </td>
                            <td class="messageTime">
                                <fmt:formatDate value="${message.date}"/>
                                <br/>
                                <fmt:formatDate value="${message.time}" type="time" timeStyle="short"/>
                            </td>
                        </tr>
                    </c:forEach>
            </c:when>
            <c:otherwise>
                <td>
                    <fmt:message key="not_found"/>
                </td>
            </c:otherwise>
        </c:choose>
        </table>
        <div class="pager">
            <ctg:messagesPager chatId="${chatId}" currentPage="${page}" generalAmount="${messagesAmount}"/>
        </div>
    </body>
</html>
