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
  <form method="POST" action="${pageContext.request.contextPath}/saveEditedUserInfo">
    <label for="name"><fmt:message key="name"/></label>
    <input type="text" name="name" id="name"  value="${userInfo.name}"/>
    <label for="surname"><fmt:message key="surname"/></label>
    <input type="text" name="surname" id="surname"  value="${userInfo.surname}"/>
    <label for="country"><fmt:message key="country"/></label>
    <input type="text" name="country" id="country"  value="${userInfo.country}"/>
    <label for="city"><fmt:message key="city"/></label>
    <input type="text" name="city" id="city" value="${userInfo.city}"/>
    <label for="dateOfBirth"><fmt:message key="date_of_birth"/></label>
    <input type="date" name="date" id="dateOfBirth" value="${userInfo.dateOfBirth}"/>
    <label for="email"><fmt:message key="e-mail"/></label>
    <input type="email" name="email" id="email"  value="${userInfo.email}"/>
    <input type="submit" name="submitAction" value="<fmt:message key="save"/>"/>
  </form>
</body>
</html>
