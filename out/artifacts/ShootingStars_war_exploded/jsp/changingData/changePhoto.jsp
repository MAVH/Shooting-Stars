<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 04.03.2015
  Time: 8:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/changePhoto" method="post" enctype="multipart/form-data">
    <%--<input type="hidden" name="command" value="get_photo"/>   --%>
    <input type="file" name="photo"/>
    <input type="submit" value="submit"/>
</form>
</body>
</html>
