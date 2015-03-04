<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <c:if test="${not empty user}">
        <jsp:forward page="jsp/main.jsp"/>
    </c:if>
    <jsp:forward page="jsp/login.jsp"/>
  </body>
</html>
