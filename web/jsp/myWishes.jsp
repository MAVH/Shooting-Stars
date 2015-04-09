<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 26.03.2015
  Time: 10:11
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
            <td><a href="userPage?userId=${wish.owner.userId}">${wish.owner.name} ${wish.owner.surname}</a></td>
            <td><fmt:formatDate value="${wish.date}"/></td>
            <td>
                 <c:choose>
                    <c:when test="${wish.isFulfilled}">
                          <c:choose>
                              <c:when test="${empty wish.date}">
                                   <fmt:message key="wish_performed"/>
                              </c:when>
                              <c:otherwise>
                                   <fmt:message key="wish_fulfilled"/>
                              </c:otherwise>
                          </c:choose>
                    </c:when>
                     <c:otherwise>
                         <c:choose>
                         <c:when test="${isPageOwner}">
                             <form action="cancelApplication" method="post">
                                 <input type="hidden" name="pageCode" value="1">
                                 <input type="hidden" name="wishId" value="${wish.wishId}"/>
                                 <input type=hidden name=userId value="${user.userId}">
                                 <input type=submit value="<fmt:message key="cancel"/>">
                             </form>
                         </c:when>
                             <c:otherwise>
                                 <fmt:message key="application_made"/>
                             </c:otherwise>
                         </c:choose>
                     </c:otherwise>
                 </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
