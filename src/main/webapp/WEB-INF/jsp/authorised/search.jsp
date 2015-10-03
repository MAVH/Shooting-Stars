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
            <button class = "usersSearchButton curr" onclick="window.location.href = '${pageContext.request.contextPath}/jsp/authorised/search.jsp'">
                <fmt:message key="users_search"/>
            </button>
            <button class = "wishesSearchButton" onclick="window.location.href = '${pageContext.request.contextPath}/jsp/authorised/wishesSearch.jsp'">
                 <fmt:message key="wishes_search"/>
            </button>
        </div>
        <form action="usersSearch" method="get">
            <div class="searchFilters">
                <label for="country" class="label"><fmt:message key="country"/></label>
                <br/>
                <input type="text" name="country" id="country" value="${country}" class="searchField form-control" pattern="[A-Za-zА-Яа-я\s-]+"/>
                <br/>
                <label for="city" class="label"><fmt:message key="city"/></label>
                <br/>
                <input type="text" name="city" id="city" value="${city}" class="searchField form-control" pattern="[A-Za-zА-Яа-я\s-]+"/>
                <br/>
                <label for="dateOfBirthMin" class="label"><fmt:message key="date_of_birth"/></label>
                <br/>
                <label for="dateOfBirthMin" class="label dateLabel"><fmt:message key="from"/></label>
                <br/>
                <input type="date" name="dateOfBirthMin" id="dateOfBirthMin" value="${dateOfBirthMin}" class="searchField form-control"/>
                <br/>
                <label for="dateOfBirthMax" class="label dateLabel"><fmt:message key="to"/></label>
                <br/>
                <input type="date" name="dateOfBirthMax" id="dateOfBirthMax" value="${dateOfBirthMax}" class="searchField form-control"/>
            </div>
            <input type="text" name="name" id="name" value="${name}" class="searchField form-control"
                   pattern="[A-Za-zА-Яа-я\s-]+" placeholder="<fmt:message key="name"/>"/>
            <input type="text" name="surname" id="surname" value="${surname}" class="searchField form-control"
                   pattern="[A-Za-zА-Яа-я\s-]+" placeholder="<fmt:message key="surname"/>"/>
            <br/>
            <input type="submit" value="<fmt:message key="find"/>" class="btn btn-default findButton">
        </form>
        <button class="birthdayMenButton" onclick="window.location.href = '${pageContext.request.contextPath}/birthdayMen'">
            <fmt:message key="birthday_men"/>
        </button>
        <c:choose>
            <c:when test="${not empty foundUsers}">
                <table class="table table-striped table-hover searchResultsTable">
                    <c:forEach var="foundUser" items="${foundUsers}">
                        <tr class="active">
                            <td>
                                <a href="userPage?userId=${foundUser.userId}">
                                    <ctg:userPhoto photoName="${foundUser.photoName}" photoClass="iconPhoto"/>
                                    ${foundUser.name} ${foundUser.surname}
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="pager">
                    <ctg:usersPager currentPage="${page}" generalAmount="${usersAmount}" name="${name}"
                                surname="${surname}" country="${country}" city="${city}"
                                dateOfBirthMin="${dateOfBirthMin}" dateOfBirthMax="${dateOfBirthMax}"/>
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
