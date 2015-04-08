<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${currentLocale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <title></title>
    <meta http-equiv="Cache-Control" content="no-cache">
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/style.css" media="screen"/>
</head>
<body>
    <a class="lang" href="${pageContext.request.contextPath}/changeLocale?localeValue=EN">EN</a>
    <a class="lang" href="${pageContext.request.contextPath}/changeLocale?localeValue=RU">РУС</a>
    <c:if test="${not empty user}">
        <a href="${pageContext.request.contextPath}/logout" class="logout"><fmt:message key="logout"/></a>
        <form action="searchByLogin" method="get">
            <input type="search" name="login" class="searchField" placeholder="<fmt:message key="search"/>"/>
        </form>
    </c:if>
    <a href="${pageContext.request.contextPath}/index.jsp"><img class="logo" src="${pageContext.request.contextPath}/img/logo.png"></a>
    <h2 class="header_name">Shooting Stars</h2>
    <h5 class="motto"><fmt:message key="motto"/></h5>
</body>
</html>
