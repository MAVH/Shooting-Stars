<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 14.03.2015
  Time: 7:08
  To change this template use File | Settings | File Templates.
--%>
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
        <ctg:wishList list="${wishes}"/>
</body>
</html>
