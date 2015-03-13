<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${currentLocale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
  <title><fmt:message key="registration_page_title"/></title>
</head>
<body>
<c:import url="header.jsp"/>
<form method="POST" action="${pageContext.request.contextPath}/registration">
  <input type="hidden" name="command" value="prepare_registration"/>
  <input type="hidden" name="partValue" value="2"/>
  <label for="name"><fmt:message key="name"/></label>
  <input type="text" name="name" id="name"  value="${user_registry.name}"/>
  <label for="surname"><fmt:message key="surname"/></label>
  <input type="text" name="surname" id="surname"  value="${user_registry.surname}"/>
  <label for="country"><fmt:message key="country"/></label>
  <input type="text" name="country" id="country"  value="${user_registry.country}"/>
  <label for="city"><fmt:message key="city"/></label>
  <input type="text" name="city" id="city" value="${user_registry.city}"/>
  <label for="dateOfBirth"><fmt:message key="date_of_birth"/></label>
  <input type="date" name="dateOfBirth" id="dateOfBirth" value="${user_registry.dateOfBirth}"/>
  <input type="submit" name="submitAction" value="<fmt:message key="continue"/>"/>
  <input type="submit" name="submitAction" value="<fmt:message key="back"/>">
</form>
${registrationError}
</body>
</html>

