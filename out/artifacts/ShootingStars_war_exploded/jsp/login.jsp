<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
  <title><fmt:message key="auth_page_title"/></title>
</head>
<body>
  <c:import url="header.jsp"/>
  <form method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="LOGIN"/>
    <label for="login_name"><fmt:message key="login"/></label>
    <input type="text" name="login" id="login_name"/>
    <label for="password"><fmt:message key="password"/></label>
    <input type="password" name="password" id="password"/>
    <input type="submit" value="<fmt:message key="sign_in"/>"/>
  </form>
  <button><a href="jsp/registration1.jsp" ><fmt:message key="sign_up"/></a></button>
${loginOrPasswordErrorMessage}
</body>
</html>
