
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
     <c:choose>
         <c:when test="${not empty wishes}">
             <table class="table">
                 <tr>
                     <th><fmt:message key="wish"/> </th>
                     <th><fmt:message key="made_by"/> </th>
                     <th><fmt:message key="date"/></th>
                 </tr>
            <c:forEach var="wish" items="${wishes}">
                 <tr>
                     <td>${wish.wish}</td>
                     <td>
                         <a href="userPage?userId=${wish.candidate.userId}">
                             <ctg:userPhoto photoName="${wish.candidate.photoName}" photoClass="iconPhoto"/>
                            ${wish.candidate.name} ${wish.candidate.surname}
                        </a>
                     </td>
                     <td><fmt:formatDate value="${wish.date}"/></td>
                 </tr>
            </c:forEach>
             </table>
         </c:when>
         <c:otherwise>
             <p>
                 <fmt:message key="not_found"/>
             </p>
         </c:otherwise>
     </c:choose>
</body>
</html>
