
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
        <table class="table">
            <c:forEach var="chat" items="${chats}">
                 <tr>
                     <td>
                         <a href="getMessages?chatId=${chat.chatId}">${chat.otherParticipant.name} ${chat.otherParticipant.surname}</a>
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
</body>
</html>
