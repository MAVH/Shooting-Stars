<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <!--
    <meta http-equiv="Cache-Control" content="no-cache">    -->
    <meta http-equiv="Cache-Control" content="private">
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/style.css"/>
</head>
<body>
<a class="lang" href="${pageContext.request.contextPath}/changeLocale?localeValue=EN">EN</a>
<a class="lang" href="${pageContext.request.contextPath}/changeLocale?localeValue=RU">РУС</a>
<c:if test="${not empty currentUserId}">
    <a href="${pageContext.request.contextPath}/logout" class="logout"><fmt:message key="logout"/> </a>
    <div class="buttons_block">
        <a href="${pageContext.request.contextPath}/index.jsp" class="profile_button"><%--<fmt:message key="profile"/>--%></a>
        <a href = "${pageContext.request.contextPath}/jsp/authorised/search.jsp" class="search_button">
            <%--<fmt:message key="search"/>--%>
        </a>
        <a href = "${ pageContext.request.contextPath }/jsp/authorised/changePassword.jsp" class="settings_button">
            <%--<fmt:message key="settings"/>--%>
        </a>
    </div>
</c:if>
<a class="logo_link" href="${pageContext.request.contextPath}/index.jsp"><img class="logo"
                                                            src="${pageContext.request.contextPath}/img/logo.png"></a>

<h2 class="header_name">Shooting Stars</h2>
<h5 class="motto"><fmt:message key="motto"/></h5>
</body>
</html>
