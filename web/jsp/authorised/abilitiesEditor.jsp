<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head>
        <title><fmt:message key="abilities_editing"/></title>
    </head>
    <body>
        <c:import url="../partial/header.jsp"/>
        <c:import url="../partial/menu.jsp"/>
        <div class="registration_form abilities_edit_form">
            <form method="POST" action="${pageContext.request.contextPath}/saveEditedUserAbilities">
                <label for="abilities" class="label"><fmt:message key="abilities"/></label>
                <textarea class="form-control abilities_textarea" name="abilities" id="abilities">${abilities}</textarea>
                <input type="submit" class="saveAbilitiesChangesButton" name="submitAction" value="<fmt:message key="save"/>"/>
            </form>
            <button onclick="window.location.href = 'userPage'" class="cancelAbilitiesEditButton">
                <fmt:message key="cancel"/>
            </button>
        </div>
    </body>
</html>