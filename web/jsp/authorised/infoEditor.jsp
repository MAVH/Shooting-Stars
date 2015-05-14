<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head>
        <title><fmt:message key="editing"/></title>
        <script type="text/javascript" src="js/script.js"></script>
    </head>
    <body>
        <c:import url="../partial/header.jsp"/>
        <c:import url="../partial/menu.jsp"/>
        <div class="registration_form">
            <form method="POST" action="${pageContext.request.contextPath}/saveEditedUserInfo">
                <table class="info_editor_table">
                    <tr>
                        <td>
                            <label for="name" class="label">
                                <fmt:message key="name"/><span class="required">*</span>
                            </label>
                        <td>
                            <input type="text" name="name" id="name" class="form-control"
                                   value="${userInfo.name}" pattern="[A-Za-zА-Яа-я\s]+"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="surname" class="label"><fmt:message key="surname"/></label>
                        <td>
                            <input type="text" name="surname" id="surname" value="${userInfo.surname}" class="form-control"/>
                    </tr>
                    <tr>
                        <td>
                            <label for="country" class="label"><fmt:message key="country"/></label>
                        <td>
                            <input type="text" name="country" id="country" value="${userInfo.country}" class="form-control"/>
                    </tr>
                    <tr>
                        <td>
                            <label for="city" class="label"><fmt:message key="city"/></label>
                        </td>
                        <td>
                            <input type="text" name="city" id="city" value="${userInfo.city}" class="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="dateOfBirth" class="label"><fmt:message key="date_of_birth"/></label>
                        </td>
                        <td>
                            <input type="date" name="date" id="dateOfBirth" value="${userInfo.dateOfBirth}" class="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td class="continue_button_td">
                            <input type="submit" name="submitAction" value="<fmt:message key="save"/>"/>
                        </td>
                    </tr>
                </table>
            </form>
            <button onclick="window.location.href = 'userPage'" class="cancelEditButton">
                <fmt:message key="cancel"/>
            </button>
            <p class="errorMessage">${messageNameEmpty}</p>
            <p class="errorMessage">${messageDateIncorrect}</p>
        </div>
    </body>
</html>
