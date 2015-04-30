<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head></head>
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
            setInterval(displayAmount, 1000);
            function displayAmount() {
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function () {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                        var answer = xmlhttp.responseText;
                        var json = JSON.parse(answer);
                        document.getElementById("unreadMessagesAmount").innerHTML = json.unreadMessagesAmount;
                    }
                }
                xmlhttp.open("GET", "getUnreadMessagesAmount", true);
                xmlhttp.send();
            }
        </script>
    </body>
</html>
