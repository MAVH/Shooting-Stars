<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head>
        <title><fmt:message key="user_page"/> </title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
        <script>
            var msg = {
                wishesText: "<fmt:message key="wishes" />",
                buttonCancel: "<fmt:message key="cancel" />",
                labelPerformed: "<fmt:message key="wish_performed" />",
                labelApplications: "<fmt:message key="applications" />",
                makeApplication: "<fmt:message key="make_application"/> "
            };
            displayUserWishesTable(${userId},${currentUserId}, msg)
        </script>
    </head>
    <body>
        <c:import url="../partial/header.jsp"/>
        <c:import url="../partial/menu.jsp"/>
        <div id="userPhoto" class="userPhotoBlock">
            <ctg:userPhoto photoName="${userInfo.photoName}" photoClass="userPhoto"/>
        </div>
        <div class = "infoBlock">
            <h2 class="userName">${userInfo.name} ${userInfo.surname}</h2>
            <h3 class="userStatus">${status}</h3>
            <c:choose>
                <c:when test="${not empty userInfo.country && not empty userInfo.city}">
                    <h3 class="userAddress">${userInfo.country}, ${userInfo.city}</h3>
                </c:when>
                <c:otherwise>
                    <h3 class="userAddress">${userInfo.country} ${userInfo.city}</h3>
                </c:otherwise>
            </c:choose>
            <c:if test="${not empty userInfo.dateOfBirth}">
                <h3 class="userBirthdate"><fmt:message key="date_of_birth"/>:
                    <fmt:formatDate value="${userInfo.dateOfBirth}"/></h3>
            </c:if>
            <div>
                <a href = "${pageContext.request.contextPath}/myFulfilledWishes?userId=${userId}" class = "usersWishes">
                </a>
                <c:url value="${pageContext.request.contextPath}/jsp/authorised/myWishes.jsp" var="myWishesURL">
                    <c:param name="userId" value="${userId}"/>
                </c:url>
                <a href = "${myWishesURL}" class = "wishesByUser">
                </a>
                <form action="toChat" method="post">
                    <input type="hidden" name="userId" value="${userId}">
                    <input type="submit" class="sendMessageButton" value="Send message"/>
                </form>
            </div>
        </div>
        <c:if test="${not empty userInfo.abilities}">
            <div class="abilitiesBlock">
                <h2 class="abilitiesLabel"><fmt:message key="abilities"/></h2>
                <h3 class="abilities">${userInfo.abilities}</h3>
            </div>
        </c:if>
        <div id="wishes_block" class="wishes_block">
        </div>
    </body>
</html>
