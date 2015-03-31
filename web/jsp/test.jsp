<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 31.03.2015
  Time: 6:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:if test="${not empty photoURL}">
    <img src="../${photoURL}" />
</c:if>
</body>
</html>
