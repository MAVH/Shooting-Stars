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
  <a href="${pageContext.request.contextPath}/userPage.action?userId=${user.userId}"><img src="${pageContext.request.contextPath}/img/logo.png"></a>
  <a href="${pageContext.request.contextPath}/controller?command=change_locale&locale=EN">EN</a>
  <a href="${pageContext.request.contextPath}/controller?command=change_locale&locale=RU">РУС</a>
    <c:if test="${not empty user}">
        <a href="${pageContext.request.contextPath}/logout"><fmt:message key="logout"/></a>
    </c:if>
</body>
</html>
