<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head>
        <title><fmt:message key="chats"/></title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
        <script>
            displayChats("${page}");
        </script>
    </head>
    <body>
        <c:import url="../partial/header.jsp"/>
        <c:import url="../partial/menu.jsp"/>
        <table class="table chatsTable" id="chats">
            <c:choose>
                <c:when test="${not empty chats}">
                    <c:forEach var="chat" items="${chats}">
                        <tr>
                            <td>
                                <a href="getMessages?chatId=${chat.chatId}">
                                    <ctg:userPhoto photoName="${chat.otherParticipant.photoName}" photoClass="iconPhoto"/>
                                        ${chat.otherParticipant.name} ${chat.otherParticipant.surname}
                                </a>
                            </td>
                            <td>
                                <c:if test="${chat.amountOfUnreadMessages != 0}">
                                    ${chat.amountOfUnreadMessages}
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td>
                            <fmt:message key="not_found"/>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>
        <ctg:chatsPager currentPage="${page}" generalAmount="${chatsAmount}"/>
    </body>
</html>
