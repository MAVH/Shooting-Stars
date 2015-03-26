<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 26.03.2015
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<fmt:setLocale value="${currentLocale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <title></title>
</head>
<body>
     <c:import url="header.jsp"/>
     <table class="table">
    <c:forEach var="wish" items="${wishes}">
         <tr>
             <td>${wish.wish}</td>
             <td><a href="userPage?userId=${wish.candidate.userId}">${wish.candidate.login}</a></td>
             <td><fmt:formatDate value="${wish.date}"/></td>
         </tr>
    </c:forEach>
     </table>
</body>
</html>
