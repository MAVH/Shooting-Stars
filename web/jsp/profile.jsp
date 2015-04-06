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
    <div id="userPhoto">
    <%--следующая строчка временная, удалить когда будет информация о пользователе --%>
    <img src="../img/userPhoto/${user.userId}.jpg" class="userPhoto"/>
        <c:choose>
            <c:when test="${not empty photoURL}">
                <img src="../img/userPhoto/${photoURL}" class="userPhoto"/>
            </c:when>
            <c:otherwise>
                <img src="../img/userPhoto/default.png" class="userPhoto"/>
            </c:otherwise>
        </c:choose>
        <button>
            <a href="${pageContext.request.contextPath}/jsp/changingData/changePhoto.jsp">change photo</a>
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
