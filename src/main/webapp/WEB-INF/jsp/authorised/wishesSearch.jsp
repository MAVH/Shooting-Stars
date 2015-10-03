<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head>
        <title><fmt:message key="search"/></title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
    </head>
    <body>
        <c:import url="../partial/header.jsp"/>
        <c:import url="../partial/menu.jsp"/>
        <div class="searchTypes">
            <button class = "usersSearchButton" onclick="window.location.href = '${pageContext.request.contextPath}/jsp/authorised/search.jsp'">
                <fmt:message key="users_search"/>
            </button>
            <button class = "wishesSearchButton curr" onclick="window.location.href = '${pageContext.request.contextPath}/jsp/authorised/wishesSearch.jsp'">
                <fmt:message key="wishes_search"/>
            </button>
        </div>
        <form action="wishesSearch" method="get">
            <input type="text" name="wish" value="${wish}" class="searchField form-control" pattern="[A-Za-zА-Яа-я\s-]+"/>
            <br/>
            <input type="submit" value="<fmt:message key="find"/>" class="btn btn-default findButton">
        </form>
        <button class="recentWishesButton" onclick="window.location.href = '${pageContext.request.contextPath}/recentWishes'">
            <fmt:message key="recent_wishes"/>
        </button>
        <c:choose>
            <c:when test="${not empty foundUsers}">
                <table class="table table-striped table-hover searchResultsTable searchWishesResults">
                <c:forEach var="result" items="${foundUsers}">
                    <tr class="active">
                        <td>
                            <a href="userPage?userId=${result.owner.userId}">
                                <ctg:userPhoto photoName="${result.owner.photoName}" photoClass="iconPhoto"/>
                                    ${result.owner.name} ${result.owner.surname}
                            </a>
                            <table>
                                <c:forEach var="userWish" items="${result.wishes}">
                                    <tr>
                                        <td>${userWish.wish}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </c:forEach>
                </table>
                <div class="pager">
                    <ctg:wishesPager currentPage="${page}" generalAmount="${amount}" wish="${wish}"/>
                </div>
            </c:when>
            <c:otherwise>
                <p class="no_search_results">
                    <fmt:message key="not_found"/>
                </p>
            </c:otherwise>
        </c:choose>

    </body>
</html>
