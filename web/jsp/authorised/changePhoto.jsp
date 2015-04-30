<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<fmt:setLocale value="${currentLocale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head>
        <title></title>
    </head>
    <body>
        <c:import url="../partial/header.jsp"/>
        <c:import url="../partial/menu.jsp"/>
        <s:form action="changePhoto" method="post" enctype="multipart/form-data">
            <s:file key="photo" accept="image/*">
                <jsp:attribute name="label">
                      <fmt:message key="load_photo"/>
                </jsp:attribute>
            </s:file>
            <s:submit value="submit"/>
        </s:form>
    </body>
</html>

