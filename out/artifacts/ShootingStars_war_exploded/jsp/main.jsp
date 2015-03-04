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

<jsp:forward page="${pageContext.request.contextPath}/controller?command=form_user_page&userId=${user.userId}"/>

<c:import url="header.jsp"/>

Hello
${user.login}
<%--если эта страница является страницей текущего пользователя(сравниваем id) --%>
    <a href="${pageContext.request.contextPath}/jsp/changingData/changePhoto.jsp"></a>
    <c:if test="${not empty photoURL}">
        <img src="${photoURL}" />
    </c:if>

</body>
</html>
