<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <title><fmt:message key="auth_page_title"/></title>
</head>
<body>
<c:import url="../partial/header.jsp"/>
<section>
    <div class="well bs-component">
        <s:form action="login" class="form-horizontal">
            <s:textfield key="login" class="form-control">
                <jsp:attribute name="label">
                    <fmt:message key="login"/>
                </jsp:attribute>
            </s:textfield>
            <s:password key="password" class="form-control">
                <jsp:attribute name="label">
                    <fmt:message key="password"/>
                </jsp:attribute>
            </s:password>
            <s:submit class="btn btn-primary">
                <jsp:attribute name="value">
                    <fmt:message key="sign_in"/>
                </jsp:attribute>
            </s:submit>
        </s:form>
    </div>
    <button class="btn btn-default" onclick="window.location.href = 'registration1.jsp'">
        <fmt:message key="sign_up"/>
    </button>
    <s:property value="loginOrPasswordErrorMessage"/>
</section>
</body>
</html>
