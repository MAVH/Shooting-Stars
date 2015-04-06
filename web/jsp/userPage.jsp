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
<img src="../img/userPhoto/${userId}.jpg" class="userPhoto"/>
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
