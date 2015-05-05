<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head></head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
    <body>
        <nav class="menu">
            <ul>
                <li>
                    <c:url value="${pageContext.request.contextPath}/jsp/authorised/myWishes.jsp" var="myWishesURL">
                        <c:param name="userId" value="${currentUserId}"/>
                    </c:url>
                    <a href = "${myWishesURL}" class = "myWishes">
                        <fmt:message key="my_fulfilled_wishes"/>
                    </a>
                </li>
                <li>
                    <a href = "${pageContext.request.contextPath}/myFulfilledWishes?userId=${currentUserId}" class = "wishesByMe">
                        <fmt:message key="wishes_I_fulfilled"/>
                    </a>
                </li>
                <li>
                    <a href = "${pageContext.request.contextPath}/getChats" class = "messages">
                        <fmt:message key="messages"/>
                    </a>
                    <span id="unreadMessagesAmount" class="messages_amount"></span>
                </li>
            </ul>
        </nav>
        <script>
            displayAmount();
        </script>
    </body>
</html>
