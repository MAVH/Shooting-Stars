<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head>
        <title><fmt:message key="wishes"/></title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
        <script>
            var msg = {
                wishText: "<fmt:message key="wish" />",
                madeByText: "<fmt:message key="made_by" />",
                dateText: "<fmt:message key="date" />"
            };
            displayFulfilledWishes("${userId}",msg);
        </script>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
    </head>
    <body>
        <c:import url="../partial/header.jsp"/>
        <c:import url="../partial/menu.jsp"/>
        <table class="table wishesTable" id="fulfilledWishes">
            <c:choose>
                <c:when test="${not empty wishes}">
                    <tr>
                        <th><fmt:message key="wish"/></th>
                        <th><fmt:message key="made_by"/></th>
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
                </c:when>
                <c:otherwise>
                    <tr>
                        <td>
                            <fmt:message key="not_found"/>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>
    </body>
</html>
