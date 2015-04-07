<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<fmt:setLocale value="${currentLocale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="js/script.js"></script>
</head>
<body>
    <c:import url="header.jsp"/>
    Hello
    ${user.login}
    <h4>${userInfo.name}</h4>
    <h4>${userInfo.surname}</h4>
    <h5>${userInfo.country}</h5>
    <h5>${userInfo.city}</h5>
    <h5><fmt:formatDate value="${userInfo.dateOfBirth}" dateStyle="short"/> </h5>
    <a href = "/editUserInfo"><fmt:message key="edit"/></a>
    <h5>${userInfo.abilities}</h5>
    <a href = "/editUserAbilities"><fmt:message key="edit"/></a>
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
        <button onclick="window.location.href = '${pageContext.request.contextPath}/jsp/changingData/changePhoto.jsp'"><fmt:message key="change_photo"/>
        </button>
    </div>
    <div>
        <form class="formWishes" action="fulfilledWishes"  method="get">
            <input type="hidden" name="userId" value="${user.userId}">
            <input type="submit" value="The wishes I've fulfilled"/>
        </form>
        <form class="formWishes" action="myFulfilledWishes" method="get">
            <input type="hidden" name="userId" value="${user.userId}">
            <input type="submit" value="My fulfilled wishes"/>
        </form>
    </div>
    <ctg:wishList list="${wishes}" isProfilePage="${true}"/>
</body>
</html>
