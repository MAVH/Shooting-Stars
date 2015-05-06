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
        <div class="registration_form">
            <div class="reg_part">
                <p>1</p>
                <p>..</p>
                <p>2</p>
                <p>..</p>
                <p class="curr">3</p>
            </div>
            <form method="POST" action="${pageContext.request.contextPath}/registration">
                <input type="hidden" name="part" value="3"/>
                <table class="reg_table">
                    <tr>
                        <td>
                            <label for="abilities" class="label"><fmt:message key="abilities"/></label>
                        </td>
                        <td>
                            <input type="textarea" name="abilities" id="abilities" class="form-control"/>
                        </td>
                    </tr>
                    <%--wishes--%>
                    <tr>
                        <td class="wish_label_td">
                            <label for="wish" class="label"><fmt:message key="wishes"/></label>
                        </td>
                        <td>
                            <input type="text" name="wish" id="wish" class="form-control"/>
                            <input type="text" name="wish" class="hidden form-control"/>
                            <input type="text" name="wish" class="hidden form-control"/>
                            <input type="text" name="wish" class="hidden form-control"/>
                            <input type="text" name="wish" class="hidden form-control"/>

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
            <button id="button_add_wish" class="add_wish_button"><fmt:message key="wish_add"/></button>
            <button onclick="window.location.href = '../../index.jsp'" class="quit_button">
                <fmt:message key="quit"/>
            </button>
            <p class="errorMessage">${registrationError}</p>
        </div>
        <script>
            displayButtonAddWish();
        </script>
    </body>
</html>
