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
<form method="POST" action="${pageContext.request.contextPath}/saveEditedUserAbilities">
  <label for="abilities"><fmt:message key="abilities"/></label>
  <input type="text" name="abilities" id="abilities"  value="${abilities}"/>
  <input type="submit" name="submitAction" value="<fmt:message key="save"/>"/>
</form>
</body>
</html>