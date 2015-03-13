<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${currentLocale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
  <title><fmt:message key="registration_page_title"/></title>
  <script type="text/javascript" src="js/script.js"></script>
</head>
<body>
<c:import url="header.jsp"/>
<form method="POST" action="${pageContext.request.contextPath}/registration">
  <input type="hidden" name="command" value="prepare_registration"/>
  <input type="hidden" name="partValue" value="3"/>
  <label for="abilities"><fmt:message key="abilities"/></label>
  <input type="textarea" name="abilities" id="abilities"/>
  <%--wishes--%>
  <input type="text" name="wish" class="hidden"/>
  <input type="text" name="wish" class="hidden"/>
  <input type="text" name="wish" class="hidden"/>
  <input type="text" name="wish" class="hidden"/>
  <input type="text" name="wish" class ="hidden"/>
  <button id = "button">One more wish</button>
  <input type="submit" name="submitAction" value="<fmt:message key="continue"/>"/>
  <input type="submit" name="submitAction" value="<fmt:message key="back"/>">
</form>
${registrationError}
</body>
</html>
