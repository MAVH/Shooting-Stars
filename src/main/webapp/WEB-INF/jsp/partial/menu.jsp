<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
    </head>
    <body>
        <nav class="menu">
            <ul>
                <li>
                    <a href = "${pageContext.request.contextPath}/myFulfilledWishes?userId=${currentUserId}" class = "myWishes">
                        <fmt:message key="my_fulfilled_wishes"/>
                    </a>
                </li>
                <li>
                    <c:url value="${pageContext.request.contextPath}/jsp/authorised/myWishes.jsp" var="myWishesURL">
                        <c:param name="userId" value="${currentUserId}"/>
                    </c:url>
                    <a href = "${myWishesURL}" class = "wishesByMe">
                        <fmt:message key="wishes_I_fulfilled"/>
                    </a>
                </li>
                <li>
                    <a href = "${pageContext.request.contextPath}/chats" class = "messages">
                        <fmt:message key="messages"/>
                    </a>
                    <span id="unreadMessagesAmount" class="hidden"></span>
                </li>
            </ul>
        </nav>
        <script>
            displayAmount();
            updateTime();
        </script>
    </body>
</html>
