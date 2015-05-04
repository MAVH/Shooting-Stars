<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head>
        <title><fmt:message key="registration_page_title"/></title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>

    </head>
    <body>
        <c:import url="../partial/header.jsp"/>
        <form method="POST" action="${pageContext.request.contextPath}/registration">
            <input type="hidden" name="part" value="3"/>
            <table>
                <tr>
                    <td>
                        <label for="abilities"><fmt:message key="abilities"/></label>
                    </td>
                    <td>
                        <input type="textarea" name="abilities" id="abilities"/>
                    </td>
                </tr>
                <%--wishes--%>
                <tr>
                    <td>
                        <label for="wish"><fmt:message key="wishes"/></label>
                    </td>
                    <td>
                        <input type="text" name="wish" id="wish"/>
                        <input type="text" name="wish" class="hidden"/>
                        <input type="text" name="wish" class="hidden"/>
                        <input type="text" name="wish" class="hidden"/>
                        <input type="text" name="wish" class="hidden"/>

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
        <button id="button_add_wish"><fmt:message key="wish_add"/></button>
        ${registrationError}
        <button onclick="window.location.href = '../../index.jsp'">
            <fmt:message key="quit"/>
        </button>
        <script>
            displayButtonAddWish();
        </script>
    </body>
</html>
