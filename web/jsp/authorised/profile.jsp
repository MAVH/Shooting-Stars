<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html id="doc">
    <head>
        <title><fmt:message key="profile"/></title><!--
        <meta http-equiv="Cache-Control" content="no-cache">    -->
        <meta http-equiv="Cache-Control" content="private">
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
        <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/style.css" media="screen"/>
        <style>
            .b-popup{
                width:100%;
                min-height:100%;
                background-color: rgba(0,0,0,0.5);
                overflow:hidden;
                position:fixed;
                top:0px;
                left: 0px;
            }
            .b-popup .b-popup-content{
                margin:40px auto 0px auto;
                width:460px;
                height: 150px;
                padding:10px;
                background-color: #e8e8e8;
                border-radius:5px;
                box-shadow: 0px 0px 10px #000;
            }
            .closeWindow {
                float: right;
                width: 20px;
                height: 20px;
                background: transparent url("../../img/icons/delete.png") top center no-repeat;
                background-size: 20px 20px;
            }
            .closeWindow:hover {
                float: right;
                width: 22px;
                height: 22px;
                background: transparent url("../../img/icons/delete.png") top center no-repeat;
                background-size: 22px 22px;
            }
            .changePhotoForm {
                margin: 20px;
                text-align: center;
            }
            .changePhotoForm input[type="file"] {
                 display: block;
                background-color: #fbeed5;

            }
            .changePhotoForm .btn-primary {
                display: block;
                background-color: #fbeed5;

            }
            .changePhotoForm input[type="submit"] {
                margin-top: 10px;
            }
        </style>
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
            function showChangePhotoBlock() {
                document.getElementById("changePhotoBlock").classList.remove("hidden");
                //document.getElementById("changePhotoBlock").classList.add("popup");

            }
            function hiddenChangePhotoBlock() {
                document.getElementById("changePhotoBlock").classList.add("hidden");
            }
        </script>

    </head>
    <body>
        <c:import url="../partial/header.jsp"/>
        <c:import url="../partial/menu.jsp"/>
        <div id="userPhoto" class="userPhotoBlock">
            <ctg:userPhoto photoName="${userInfo.photoName}" photoClass="userPhoto"/>
            <button class="changePhotoButton" onclick="showChangePhotoBlock()"
                    <%--onclick="window.location.href = '${ pageContext.request.contextPath }/jsp/authorised/changePhoto.jsp'"--%>>
                <fmt:message key="change_photo"/>
            </button>
            <div id="changePhotoBlock" class="b-popup hidden">
                <div class="b-popup-content">
                    <button id="closeWindow" onclick="hiddenChangePhotoBlock()" class="closeWindow"></button>
                    <p><fmt:message key="load_photo"/></p>
                    <form action="changePhoto" method="post" enctype="multipart/form-data" class="changePhotoForm">
                        <input type="file" id="photo" name="photo" accept="image/*" data-input="false" />
                        <input type="submit" value="<fmt:message key="save"/> "/>
                    </form>
                </div>
            </div>
        </div>
        <div class = "infoBlock">
            <h2 class="userName">${userInfo.name} ${userInfo.surname}</h2>
            <h3 class="userStatus">${status}</h3>
            <c:choose>
                <c:when test="${not empty userInfo.country && not empty userInfo.city}">
                    <h3 class="userAddress">${userInfo.country}, ${userInfo.city}</h3>
                </c:when>
                <c:otherwise>
                    <h3 class="userAddress">${userInfo.country} ${userInfo.city}</h3>
                </c:otherwise>
            </c:choose>
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
            <h2 class="wishesLabel"><fmt:message key="wishes"/></h2>
        </div>
        <ctg:addingWishBlock count="${wishesCount}"/>
    </body>
</html>
