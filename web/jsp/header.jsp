<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${currentLocale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <!--
    <meta http-equiv="Cache-Control" content="no-cache">    -->
    <meta http-equiv="Cache-Control" content="private">
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/style.css" media="screen"/>
</head>
<body>
    <a class="lang" href="${pageContext.request.contextPath}/changeLocale?localeValue=EN">EN</a>
    <a class="lang" href="${pageContext.request.contextPath}/changeLocale?localeValue=RU">РУС</a>
    <c:if test="${not empty currentUserId}">
        <a href="${pageContext.request.contextPath}/logout" class="logout"><fmt:message key="logout"/></a>
        <button onclick="window.location.href = '${pageContext.request.contextPath}/jsp/search.jsp'">
            <fmt:message key="search"/>
        </button>
        <div>
            <form action="getChats">
                <input type="submit" value="<fmt:message key="messages"/> "/>
            </form>
            <span id="unreadMessagesAmount"></span>
        </div>
    </c:if>
    <a href="${pageContext.request.contextPath}/index.jsp"><img class="logo" src="${pageContext.request.contextPath}/img/logo.png"></a>
    <h2 class="header_name">Shooting Stars</h2>
    <h5 class="motto"><fmt:message key="motto"/></h5>
    <script>
        setInterval(displayAmount, 1000);
        function displayAmount() {
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                        var answer = xmlhttp.responseText;
                        var json = JSON.parse(answer);
                        document.getElementById("unreadMessagesAmount").innerHTML =json.unreadMessagesAmount ;
                    }
                }
                xmlhttp.open("GET", "getUnreadMessagesAmount", true);
                xmlhttp.send();
        }
    </script>
</body>
</html>
