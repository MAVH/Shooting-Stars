<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title></title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/x-icon"/>
    </head>
    <body>
        <%--<c:if test="${not empty currentUserId}">--%>
            <%--<c:redirect url="${pageContext.request.contextPath}/userPage"/>--%>
        <%--</c:if>--%>
        <h2>Hello</h2>
<jsp:forward page="${pageContext.request.contextPath}/WEB-INF/jsp/guest/login.jsp"/>

</body>
</html>
