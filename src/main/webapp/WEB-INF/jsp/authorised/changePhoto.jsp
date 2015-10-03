<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<fmt:setLocale value="${currentLocale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head>
        <title><fmt:message key="change_photo"/></title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
    </head>
    <body>
        <c:import url="../partial/header.jsp"/>
        <c:import url="../partial/menu.jsp"/>
        <div class="changePhotoBlock">
            <h3><fmt:message key="load_photo"/></h3>
            <form action="changePhoto" method="post" enctype="multipart/form-data" class="changePhotoForm">
                <span class="errorMessage">${errorMessage}</span>
                <div class="fileform">
                    <div id="fileformlabel"></div>
                    <div class="selectbutton"><fmt:message key="browse"/></div>
                    <s:file key="photo" accept="image/*" onchange="getUploadedPhotoName(this.value);"/>
                </div>
                <input type="submit" value="<fmt:message key="save"/> "/>
            </form>
            <button onclick="window.location.href = 'userPage'" class="cancelChangePhotoButton">
                <fmt:message key="cancel"/>
            </button>
        </div>
    </body>
</html>

