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
    <a href="${pageContext.request.contextPath}/jsp/changingData/changePhoto.jsp">change photo</a>
    <c:if test="${not empty photoURL}">
        <img src="${photoURL}" />
    </c:if>
    <form action="fulfilledWishes"  method="get">
        <input type="hidden" name="userId" value="${user.userId}">
        <input type="submit" value="The wishes I've fulfilled"/>
    </form>
    <form action="myFulfilledWishes" method="get">
        <input type="hidden" name="userId" value="${user.userId}">
        <input type="submit" value="My fulfilled wishes"/>
    </form>
    <ctg:wishList list="${wishes}" isProfilePage="${true}"/>
</body>
</html>
