<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head>
        <title><fmt:message key="search"/></title>
    </head>
    <body>
        <c:import url="../partial/header.jsp"/>
        <c:import url="../partial/menu.jsp"/>
        <div>
            <button onclick="window.location.href = '${pageContext.request.contextPath}/jsp/authorised/search.jsp'">
                <fmt:message key="users_search"/>
            </button>
        </div>
        <form action="wishesSearch" method="get">
            <input type="textarea" name="wish" value="${wish}" class="searchField form-control" pattern="[A-Za-zА-Яа-я\s-]+"/>
            <input type="submit" value="<fmt:message key="find"/>" class="btn btn-default">
        </form>
        <c:choose>
            <c:when test="${not empty foundUsers}">
                <table class="table table-striped table-hover ">
                <c:forEach var="result" items="${foundUsers}">
                    <tr class="active">
                        <td>
                            <a href="userPage?userId=${result.owner.userId}">
                                <ctg:userPhoto photoName="${result.owner.photoName}" photoClass="iconPhoto"/>
                                    ${result.owner.name} ${result.owner.surname}
                            </a>
                            <table>
                                <c:forEach var="wish" items="${result.wishes}">
                                    <tr>
                                        <td>${wish.wish}</td>
                                    </tr>
                                </c:forEach>
                            </table>
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
