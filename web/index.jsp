<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <c:if test="${not empty currentUserId}">
        <c:redirect url="${pageContext.request.contextPath}/userPage"/>
    </c:if>
    <jsp:forward page="jsp/guest/login.jsp"/>
  </body>
</html>
