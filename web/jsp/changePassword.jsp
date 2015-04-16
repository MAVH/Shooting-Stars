
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
</head>
<body>

<c:import url="header.jsp"/>
<s:form method="POST" action="changePassword">
    <fieldset>
        <s:password key="oldPassword">
              <jsp:attribute name="label">
                     <fmt:message key="old_password"/>
              </jsp:attribute>
        </s:password>
        <s:password key="newPassword">
              <jsp:attribute name="label">
                     <fmt:message key="new_password"/>
              </jsp:attribute>
        </s:password>
        <s:password key="repeatPassword">
              <jsp:attribute name="label">
                     <fmt:message key="password_repeat"/>
              </jsp:attribute>
        </s:password>
        <s:submit name="submitAction" >
              <jsp:attribute name="value">
                     <fmt:message key="save"/>
              </jsp:attribute>
        </s:submit>
    </fieldset>
</s:form>
<p>${messageOperationInfo}</p>
</body>
</html>
