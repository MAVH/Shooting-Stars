
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${currentLocale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <title><fmt:message key="search"/> </title>
</head>
<body>
<c:import url="header.jsp"/>
<form action="searchByLogin" method="get">
    <input type="search" name="login" class="searchField" />
    <input type="submit" value="<fmt:message key="find"/>" class="btn btn-default">
</form>
     <c:choose>
         <c:when test="${not empty foundUsers}">
               <table class="table table-striped table-hover ">
                   <c:forEach var="foundUser" items="${foundUsers}">
                       <tr class="active">
                           <td>
                               <a href="userPage?userId=${foundUser.userId}">${foundUser.login}</a>
                           </td>
                       </tr>
                   </c:forEach>
               </table>
         </c:when>
         <c:otherwise>
             <fmt:message key="not_found"/>
         </c:otherwise>
     </c:choose>
</body>
</html>
