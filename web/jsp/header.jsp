<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/style.css" media="screen"/>
</head>
<body>
<%--
<c:choose>

    <c:when test="${not empty user}">
        <a href="${pageContext.request.contextPath}/userPage?userId=${user.userId}"><img src="${pageContext.request.contextPath}/img/logo.png"></a>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/img/logo.png"></a>
    </c:otherwise>
</c:choose>  --%>
<a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/img/logo.png"></a>

  <a href="${pageContext.request.contextPath}/changeLocale?localeValue=EN">EN</a>
  <a href="${pageContext.request.contextPath}/changeLocale?localeValue=RU">РУС</a>
    <c:if test="${not empty user}">
        <a href="${pageContext.request.contextPath}/logout"><fmt:message key="logout"/></a>
    </c:if>
</body>
</html>
