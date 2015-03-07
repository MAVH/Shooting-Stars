<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
  <title><fmt:message key="auth_page_title"/></title>
</head>
<body>
  <c:import url="header.jsp"/>
  <s:form action="login">
       <s:textfield key="login">
            <jsp:attribute name="label">
                      <fmt:message key="login"/>
            </jsp:attribute>
       </s:textfield>
      <s:password key="password">
        <jsp:attribute name="label">
                      <fmt:message key="password"/>
        </jsp:attribute>
      </s:password>
      <s:submit>
          <jsp:attribute name="value" >
                 <fmt:message key="sign_in"/>
          </jsp:attribute>
      </s:submit>
  </s:form>
  <%--
  <form method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="LOGIN"/>
    <label for="login_name"><fmt:message key="login"/></label>
    <input type="text" name="login" id="login_name"/>
    <label for="password"><fmt:message key="password"/></label>
    <input type="password" name="password" id="password"/>
    <input type="submit" value="<fmt:message key="sign_in"/>"/>
  </form>  --%>
<button><a href="jsp/registration1.jsp" ><fmt:message key="sign_up"/></a></button>
<s:property value="loginOrPasswordErrorMessage"/>
</body>
</html>
