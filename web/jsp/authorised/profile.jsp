<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head>
        <title><fmt:message key="profile"/></title><!--
        <meta http-equiv="Cache-Control" content="no-cache">    -->
        <meta http-equiv="Cache-Control" content="private">
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/style.css" media="screen"/>
        <script>
            var msg = {
                wishesText: "<fmt:message key="wishes" />",
                buttonDelete: "<fmt:message key="delete" />",
                buttonTake: "<fmt:message key="take_application" />",
                buttonCancel: "<fmt:message key="cancel" />",
                labelPerformed: "<fmt:message key="wish_performed" />",
                labelApplications: "<fmt:message key="applications" />"
            };
            displayWishesTable(${currentUserId},msg);
        </script>
    </head>
    <body>
        <c:import url="../partial/header.jsp"/>
        <c:import url="../partial/menu.jsp"/>
        <div id="userPhoto" class="userPhotoBlock">
            <ctg:userPhoto photoName="${userInfo.photoName}" photoClass="userPhoto"/>
            <button class="changePhotoButton" onclick="window.location.href = '${ pageContext.request.contextPath }/jsp/authorised/changePhoto.jsp'">
                <fmt:message key="change_photo"/>
            </button>
        </div>
        <div class = "infoBlock">
            <h2 class="userName">${userInfo.name} ${userInfo.surname}</h2>
            <h3 class="userStatus">${status}</h3>
            <h3 class="userAddress">${userInfo.country} ${userInfo.city}</h3>
            <c:if test="${not empty userInfo.dateOfBirth}">
                <h3 class="userBirthdate"><fmt:message key="date_of_birth"/>:
                    <fmt:formatDate value="${userInfo.dateOfBirth}"/></h3>
            </c:if>
            <a class="editInfoLink hidden" href="/editUserInfo"><fmt:message key="edit"/></a>
        </div>
        <div class="abilitiesBlock">
            <h2 class="abilitiesLabel"><fmt:message key="abilities"/></h2>
            <h3 class="abilities">${userInfo.abilities}</h3>
            <c:choose>
                <c:when test="${not empty userInfo.abilities}">
                    <a href="/editUserAbilities"  class="editAbilitiesLink hidden"><fmt:message key="edit"/></a>
                </c:when>
                <c:otherwise>
                    <a href="/editUserAbilities"  class="editAbilitiesLink hidden"><fmt:message key="add_abilities"/></a>
                </c:otherwise>
            </c:choose>
        </div>
        <div id="wishes_block" class="wishes_block">
        </div>
        <ctg:addingWishBlock count="${wishesCount}"/>
    </body>
</html>
