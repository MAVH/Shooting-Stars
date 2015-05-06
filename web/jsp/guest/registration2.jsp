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
                <p>1</p>
                <p>..</p>
                <p class="curr">2</p>
                <p>..</p>
                <p>3</p>
            </div>
            <form method="POST" action="${pageContext.request.contextPath}/registration">
                <input type="hidden" name="part" value="2"/>
                <table class="reg_table">
                    <tr>
                        <td>
                            <label for="name" class="label"><fmt:message key="name"/></label>
                        <td>
                            <input type="text" name="name" id="name" class="form-control"
                                   value="${user_registry.name}" pattern="[A-Za-zА-Яа-я\s]+"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="surname" class="label"><fmt:message key="surname"/></label>
                        </td>
                        <td>
                            <input type="text" name="surname" id="surname" class="form-control"
                                   value="${user_registry.surname}" pattern="[A-Za-zА-Яа-я\s]+"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="country" class="label"><fmt:message key="country"/></label>
                        </td>
                        <td>
                            <input type="text" name="country" id="country" class="form-control"
                                   value="${user_registry.country}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="city" class="label"><fmt:message key="city"/></label>
                        </td>
                        <td>
                            <input type="text" name="city" id="city" class="form-control"
                                   value="${user_registry.city}" pattern="[A-Za-zА-Яа-я\s]+"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="dateOfBirth" class="label"><fmt:message key="date_of_birth"/></label>
                        </td>
                        <td>
                            <input type="date" name="dateOfBirth" id="dateOfBirth" class="form-control"
                                   value="${user_registry.dateOfBirth}"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" name="submitAction" class="continue_button" value="<fmt:message key="continue"/>"/>
                            <input type="submit" name="submitAction" class="back_button" value="<fmt:message key="back"/>">
                        <%--</td>
                        <td class="continue_button_td">--%>

                        </td>
                    </tr>
                </table>
            </form>
            <button onclick="window.location.href = '../../index.jsp'" class="quit_button">
                <fmt:message key="quit"/>
            </button>
            <p class="errorMessage">${registrationError}</p>
            <p class="errorMessage">${registrationDateError}</p>
        </div>
    </body>
</html>

