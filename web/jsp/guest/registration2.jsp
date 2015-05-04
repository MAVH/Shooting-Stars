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
            <input type="hidden" name="part" value="2"/>
            <table>
                <tr>
                    <td>
                        <label for="name"><fmt:message key="name"/></label>
                    <td>
                        <input type="text" name="name" id="name" value="${user_registry.name}" pattern="[A-Za-zА-Яа-я\s]+"/>
                </tr>
                <tr>
                    <td>
                        <label for="surname"><fmt:message key="surname"/></label>
                    </td>
                    <td>
                        <input type="text" name="surname" id="surname" value="${user_registry.surname}"
                               pattern="[A-Za-zА-Яа-я\s]+"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="country"><fmt:message key="country"/></label>
                    </td>
                    <td>
                        <input type="text" name="country" id="country" value="${user_registry.country}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="city"><fmt:message key="city"/></label>
                    </td>
                    <td>
                        <input type="text" name="city" id="city" value="${user_registry.city}" pattern="[A-Za-zА-Яа-я\s]+"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="dateOfBirth"><fmt:message key="date_of_birth"/></label>
                    </td>
                    <td>
                        <input type="date" name="dateOfBirth" id="dateOfBirth" value="${user_registry.dateOfBirth}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="submitAction" value="<fmt:message key="back"/>">
                    </td>
                    <td>
                        <input type="submit" name="submitAction" value="<fmt:message key="continue"/>"/>
                    </td>

                </tr>
            </table>
        </form>
        ${registrationError}
        ${registrationDateError}
        <button onclick="window.location.href = '../../index.jsp'">
            <fmt:message key="quit"/>
        </button>
    </body>
</html>

