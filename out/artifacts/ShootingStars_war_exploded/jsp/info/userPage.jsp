<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 04.03.2015
  Time: 9:06
  To change this template use File | Settings | File Templates.
--%>
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
<c:import url="../header.jsp"/>

Hello
<%-- если эта страница является страницей текущего пользователя(сравниваем id) or boolean field --%>
<a href="${pageContext.request.contextPath}/jsp/changingData/changePhoto.jsp">change photo</a>
<c:if test="${not empty photoURL}">
    <img src="${photoURL}" />
</c:if>
</body>
</html>
