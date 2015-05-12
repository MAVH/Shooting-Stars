<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head>
        <title><fmt:message key="wishes"/></title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
        <script>
            var msg = {
                wishText: "<fmt:message key="wish" />",
                ownerText: "<fmt:message key="wish_owner" />",
                statusText: "<fmt:message key="status" />",
                dateText: "<fmt:message key="date" />",
                labelPerformed: "<fmt:message key="wish_performed" />",
                wishFulfilledText: "<fmt:message key="wish_fulfilled" />",
                applicationMadeText: "<fmt:message key="application_made" />",
                buttonCancel: "<fmt:message key="cancel" />",
                notFoundMessage: "<fmt:message key="not_found"/>"

            };
            displayMyWishes("${param.userId}", "${currentUserId}",msg)
        </script>
    </head>
    <body>
        <c:import url="../partial/header.jsp"/>
        <c:import url="../partial/menu.jsp"/>
        <table class="table wishesTable" id="myWishes">
        </table>
    </body>
</html>
