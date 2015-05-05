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
        <div class="registration_form">
            <div class="reg_part">
                <p class="curr">1</p>
                <p>..</p>
                <p>2</p>
                <p>..</p>
                <p>3</p>
            </div>
            <form method="POST" action="${pageContext.request.contextPath}/registration">
                <input type="hidden" name="part" value="1"/>
                <table class="reg_table">
                    <tr>
                        <td>
                            <label for="login_name" class="label"><fmt:message key="login"/></label>
                        </td>
                        <td>
                            <input type="text" name="login" id="login_name" class="form-control"
                                   value="${user_registry.login}" pattern="[\w@!.]+"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="password" class="label"><fmt:message key="password"/></label>
                        </td>
                        <td>
                            <input type="password" name="password" id="password" class="form-control"
                                   value="${user_registry.password}" pattern="[\S]+"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="password_repeat" class="label"><fmt:message key="password_repeat"/></label>
                        </td>
                        <td>
                            <input type="password" name="password_repeat" id="password_repeat" class="form-control"
                                   value="${user_registry.password}" pattern="[\S]+"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="email" class="label"><fmt:message key="e-mail"/></label>
                        </td>
                        <td>
                            <input type="email" name="email" id="email" class="form-control"
                                   value="${user_registry.email}"/>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td class="continue_button_td">
                            <input type="submit" class="continue_button" value="<fmt:message key="continue"/>"/>
                        </td>
                    </tr>
                </table>
            </form>
            <button onclick="window.location.href = '../../index.jsp'" class="quit_button reg1_quit_button">
                <fmt:message key="quit"/>
            </button>
            <p class="errorMessage">${registrationError}</p>
            <p class="errorMessage">${registrationLoginError}</p>
            <p class="errorMessage">${registrationPasswordError}</p>
            <p class="errorMessage">${registrationInvalidPasswordError}</p>
        </div>
    </body>
</html>
