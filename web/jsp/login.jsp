<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<fmt:setLocale value="${currentLocale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <title><fmt:message key="auth_page_title"/></title>
</head>
<body>
<c:import url="header.jsp"/>
<section>
<div class="well bs-component">
    <s:form action="login" class="form-horizontal">
        <fieldset>
            <div class="form-group">
                <div class="col-lg-10">
                    <s:textfield key="login" class="form-control">
            <jsp:attribute name="label">
                      <fmt:message key="login"/>
            </jsp:attribute>
                    </s:textfield>
                </div>
            </div>
            <s:password key="password" class="form-control">
        <jsp:attribute name="label">
                      <fmt:message key="password"/>
        </jsp:attribute>
            </s:password>
            <s:submit class="btn btn-primary">
          <jsp:attribute name="value" >
                 <fmt:message key="sign_in"/>
          </jsp:attribute>
            </s:submit>
        </fieldset>
    </s:form>
</div>
<%--
<form method="POST" action="${pageContext.request.contextPath}/login">
  <input type="hidden" name="command" value="LOGIN"/>
  <label for="login_name"><fmt:message key="login"/></label>
  <input type="text" name="login" id="login_name"/>
  <label for="password"><fmt:message key="password"/></label>
  <input type="password" name="password" id="password"/>
  <input type="submit" value="<fmt:message key="sign_in"/>"/>
</form>  --%>
<button class="btn btn-default" onclick="window.location.href = '${pageContext.request.contextPath}/jsp/registration1.jsp'"><fmt:message key="sign_up"/></button>
<s:property value="loginOrPasswordErrorMessage"/>
</section>
</body>
</html>
