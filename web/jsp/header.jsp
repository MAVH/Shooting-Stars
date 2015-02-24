<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <title></title>
</head>
<body>
  <a href="${pageContext.request.contextPath}/controller?command=form_main_page"><img src="${pageContext.request.contextPath}/img/logo.png"></a>
  <a href="${pageContext.request.contextPath}/controller?command=change_locale&locale=EN">EN</a>
  <a href="${pageContext.request.contextPath}/controller?command=change_locale&locale=RU">РУС</a>
    <c:if test="${not empty user}">
        <a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="logout"/></a>
    </c:if>
</body>
</html>
