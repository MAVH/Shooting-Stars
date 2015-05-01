<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head></head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
    <body>
        <nav>
            <ul>
                <li>
                    <c:url value="${pageContext.request.contextPath}/jsp/authorised/myWishes.jsp" var="myWishesURL">
                        <c:param name="userId" value="${currentUserId}"/>
                    </c:url>
                    <button onclick="window.location.href = '${myWishesURL}'">
                        The wishes I've fulfilled
                    </button>
                </li>
                <li>
                    <form class="formWishes" action="myFulfilledWishes" method="get">
                        <input type="hidden" name="userId" value="${currentUserId}">
                        <input type="submit" value="My fulfilled wishes"/>
                    </form>
                </li>
                <li>
                    <form action="getChats">
                        <input type="submit" value="<fmt:message key="messages"/> "/>
                    </form>
                    <span id="unreadMessagesAmount"></span>
                </li>
            </ul>
        </nav>
        <script>
            displayAmount();
        </script>
    </body>
</html>
