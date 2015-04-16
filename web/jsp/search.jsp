
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
<form action="usersSearch" method="get">
    <input type="text" name="name" value="${name}" class="searchField" />
    <input type="text" name="surname" value="${surname}" class="searchField" />
    <input type="text" name="country" value="${country}" class="searchField"/>
    <input type="text" name="city" value="${city}" class="searchField"/>
    <input type="date" name="dateOfBirthMin" value="${dateOfBirthMin}" class="searchField"/>
    <input type="date" name="dateOfBirthMax" value="${dateOfBirthMax}" class="searchField"/>
    <input type="submit" value="<fmt:message key="find"/>" class="btn btn-default">
</form>
     <c:choose>
         <c:when test="${not empty foundUsers}">
               <table class="table table-striped table-hover ">
                   <c:forEach var="foundUser" items="${foundUsers}">
                       <tr class="active">
                           <td>
                               <a href="userPage?userId=${foundUser.userId}">${foundUser.name} ${foundUser.surname}</a>
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
