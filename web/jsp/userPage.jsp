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
            <img src="../img/userPhoto/${userInfo.photoName}" class="userPhoto"/>
        </c:when>
        <c:otherwise>
            <img src="../img/userPhoto/default.png" class="userPhoto"/>
        </c:otherwise>
    </c:choose>
</div>

<form action="toChat"  method="post">
    <input type="hidden" name="userId" value="${userId}">
    <input type="submit" value="Send message"/>
</form>
<div>
    <form action="fulfilledWishes"  method="get">
        <input type="hidden" name="userId" value="${userId}">
        <input type="submit" value="The wishes this user has fulfilled"/>
    </form>
    <form action="myFulfilledWishes"  method="get">
        <input type="hidden" name="userId" value="${userId}">
        <input type="submit" value="His fulfilled wishes"/>
    </form>
</div>
<ctg:wishList list="${wishes}" isProfilePage="${false}"/>

</body>
</html>
