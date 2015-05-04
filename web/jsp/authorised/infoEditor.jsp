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
        <form method="POST" action="${pageContext.request.contextPath}/saveEditedUserInfo">
            <table>
                <tr>
                    <s:textfield key="name">
                        <jsp:attribute name="value">
                            ${userInfo.name}
                        </jsp:attribute>
                        <jsp:attribute name="label">
                            <fmt:message key="name"/>
                        </jsp:attribute>
                    </s:textfield>
                </tr>
                <%--
              <label for="name"><fmt:message key="name"/></label>
              <input type="text" name="name" id="name"  value="${userInfo.name}"/>
              --%>
                <tr>
                    <td>
                        <label for="surname"><fmt:message key="surname"/></label>
                    <td>
                        <input type="text" name="surname" id="surname" value="${userInfo.surname}"/>
                </tr>
                <tr>
                    <td>
                        <label for="country"><fmt:message key="country"/></label>
                    <td>
                        <input type="text" name="country" id="country" value="${userInfo.country}"/>
                </tr>
                <tr>
                    <td>
                        <label for="city"><fmt:message key="city"/></label>
                    </td>
                    <td>
                        <input type="text" name="city" id="city" value="${userInfo.city}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="dateOfBirth"><fmt:message key="date_of_birth"/></label>
                    </td>
                    <td>
                        <input type="date" name="date" id="dateOfBirth" value="${userInfo.dateOfBirth}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="email"><fmt:message key="e-mail"/></label>
                    </td>
                    <td>
                        <input type="email" name="email" id="email" value="${userInfo.email}"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" name="submitAction" value="<fmt:message key="save"/>"/>
                    </td>
                </tr>
            </table>
        </form>
        <p>${messageEmailEmpty}</p>

        <p>${messageDateIncorrect}</p>
    </body>
</html>
