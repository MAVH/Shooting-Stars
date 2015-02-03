<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
  <title><fmt:message key="registration_page_title"/></title>
</head>
<body>
<form method="POST" action="${pageContext.request.contextPath}/controller">
  <input type="hidden" name="command" value="register"/>
  <input type="hidden" name="part" value="3"/>
  <label for="abilities"><fmt:message key="abilities"/></label>
  <input type="text" name="abilities" id="abilities"/>
  <%--wishes--%>
  <input type="submit" value="<fmt:message key="continue"/>"/>
</form>
<a href="${pageContext.request.contextPath}/jsp/registration2.jsp"><fmt:message key="back"/></a>
${registrationError}
</body>
</html>
