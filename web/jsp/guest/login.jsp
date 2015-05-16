<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head>
        <title><fmt:message key="auth_page_title"/></title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
    </head>
    <body>
        <c:import url="../partial/header.jsp"/>
        <section>
            <div class="login_form">
                <p class="errorMessage"><s:property value="loginOrPasswordErrorMessage"/></p>
                <s:form action="login">
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
                    <s:submit class="btn btn-primary sign_in_button">
                        <jsp:attribute name="value">
                            <fmt:message key="sign_in"/>
                        </jsp:attribute>
                    </s:submit>
                </s:form>
                <button class="btn btn-default sign_up_button" onclick="window.location.href = '${pageContext.request.contextPath}/jsp/guest/registration1.jsp'">
                    <fmt:message key="sign_up"/>
                </button>
            </div>
        </section>
    </body>
</html>
