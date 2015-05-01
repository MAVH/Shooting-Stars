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
                labelApplications: "<fmt:message key="applications" />"
            };
            displayUserWishesTable(${userId},${currentUserId}, msg)
        </script>
    </head>
    <body>
        <c:import url="../partial/header.jsp"/>
        <c:import url="../partial/menu.jsp"/>
        <h4>${userInfo.name}</h4>
        <h4>${userInfo.surname}</h4>
        <h5>${userInfo.country}</h5>
        <h5>${userInfo.city}</h5>
        <h5><fmt:formatDate value="${userInfo.dateOfBirth}"/></h5>
        <c:if test="${not empty userInfo.abilities}">
            <h4><fmt:message key="abilities"/></h4>
            <h5>${userInfo.abilities}</h5>
        </c:if>
        <h3>${status}</h3>
        <div id="userPhoto">
            <c:choose>
                <c:when test="${not empty userInfo.photoName}">
                    <img src="../../img/userPhoto/${userInfo.photoName}" class="userPhoto"/>
                </c:when>
                <c:otherwise>
                    <img src="../../img/userPhoto/default.png" class="userPhoto"/>
                </c:otherwise>
            </c:choose>
        </div>
        <form action="toChat" method="post">
            <input type="hidden" name="userId" value="${userId}">
            <input type="submit" value="Send message"/>
        </form>
        <div>
            <c:url value="${pageContext.request.contextPath}/jsp/authorised/myWishes.jsp" var="myWishesURL">
                <c:param name="userId" value="${userId}"/>
            </c:url>
            <button onclick="window.location.href = '${myWishesURL}'">
                <fmt:message key="wishes_fulfilled"/>
            </button>
            <form action="myFulfilledWishes" method="get">
                <input type="hidden" name="userId" value="${userId}">
                <input type="submit" value="His fulfilled wishes"/>
            </form>
        </div>
        <div id="wishes_block">
        </div>
    </body>
</html>
