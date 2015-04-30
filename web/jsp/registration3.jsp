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
  <input type="hidden" name="part" value="3"/>
  <label for="abilities"><fmt:message key="abilities"/></label>
  <input type="textarea" name="abilities" id="abilities"/>
  <%--wishes--%>
  <input type="text" name="wish"/>
  <input type="text" name="wish" class="hidden"/>
  <input type="text" name="wish" class="hidden"/>
  <input type="text" name="wish" class="hidden"/>
  <input type="text" name="wish" class ="hidden"/>
  <input type="submit" name="submitAction" value="<fmt:message key="continue"/>"/>
  <input type="submit" name="submitAction" value="<fmt:message key="back"/>">
</form>
<button id = "button_add_wish"><fmt:message key="wish_add"/> </button>
${registrationError}
<button onclick="window.location.href = '${pageContext.request.contextPath}/index.jsp'">
  <fmt:message key="quit"/>
</button>
</body>
</html>
