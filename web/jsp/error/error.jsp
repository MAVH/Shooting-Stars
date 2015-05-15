<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>Error</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
        <style>
            .errorBlock {
                width: 100%;
                text-align: center;
                font-size: 35px;
                color: firebrick;
                margin-top: 50px;
            }
        </style>
    </head>
    <body>
        <div class="errorBlock">
            <%--Request from ${pageContext.errorData.requestURI} is failed--%>
            <%--<br/>--%>
            <%--Servlet name or type: ${pageContext.errorData.servletName}--%>
            <%--<br/>--%>
            <p class="errorStatus">Error ${pageContext.errorData.statusCode}</p>
        </div>
    </body>
</html>
