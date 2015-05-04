<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head>
        <title><fmt:message key="registration_page_title"/></title>
    </head>
    <body>
        <c:import url="../partial/header.jsp"/>
        <form method="POST" action="${pageContext.request.contextPath}/registration">
            <input type="hidden" name="part" value="1"/>
            <table>
                <tr>
                    <td>
                        <label for="login_name"><fmt:message key="login"/></label>
                    </td>
                    <td>
                        <input type="text" name="login" id="login_name" value="${user_registry.login}" pattern="[\w@!.]+"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="password"><fmt:message key="password"/></label>
                    </td>
                    <td>
                        <input type="password" name="password" id="password" value="${user_registry.password}" pattern="[\S]+"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="password_repeat"><fmt:message key="password_repeat"/></label>
                    </td>
                    <td>
                        <input type="password" name="password_repeat" id="password_repeat" value="${user_registry.password}"
                               pattern="[\S]+"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="email"><fmt:message key="e-mail"/></label>
                    </td>
                    <td>
                        <input type="email" name="email" id="email" value="${user_registry.email}"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="<fmt:message key="continue"/>"/>
                    </td>
                </tr>
            </table>
        </form>
        ${registrationError}
        ${registrationLoginError}
        ${registrationPasswordError}
        ${registrationInvalidPasswordError}
        <button onclick="window.location.href = '../../index.jsp'">
            <fmt:message key="quit"/>
        </button>
    </body>
</html>
