<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head>
        <title><fmt:message key="settings"/></title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
    </head>
    <body>
    <c:import url="../partial/header.jsp"/>
    <c:import url="../partial/menu.jsp"/>
    <button id="buttonChangePassword" class="changePasswordButton"><fmt:message key="password.change"/></button>
    <div id="formChangePassword" class="hidden changePasswordBlock">
        <s:form method="POST" action="changePassword">
            <s:password key="oldPassword" class="form-control" pattern="[\S]+">
                <jsp:attribute name="label">
                    <fmt:message key="old_password"/>
                </jsp:attribute>
            </s:password>
            <s:password key="newPassword" class="form-control" pattern="[\S]+">
                <jsp:attribute name="label">
                     <fmt:message key="new_password"/>
                </jsp:attribute>
            </s:password>
            <s:password key="repeatPassword" class="form-control" pattern="[\S]+">
                 <jsp:attribute name="label">
                      <fmt:message key="password_repeat"/>
                 </jsp:attribute>
            </s:password>
            <s:submit name="submitAction" class="saveChangedPasswordButton">
                 <jsp:attribute name="value">
                       <fmt:message key="save"/>
                 </jsp:attribute>
            </s:submit>
        </s:form>
        <p>${messageOperationInfo}</p>
    </div>
    <hr/>
    <button id="buttonChangeEmail" class="changeEmailButton"><fmt:message key="email.change"/></button>
    <div id="formChangeEmail" class="hidden changeEmailBlock">
        <form action="changeEmail">
            <input type="email" name="email" class="form-control changeEmail" id="email"/>
            <input type="submit" value="<fmt:message key="save"/> ">
        </form>
        <p>${operationInfo}</p>
    </div>
    <script>
        document.getElementById("buttonChangeEmail").onclick = function() {
            document.getElementById("formChangeEmail").classList.remove("hidden");
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    var answer = xmlhttp.responseText;
                    var json = JSON.parse(answer);
                    document.getElementById("email").setAttribute('value',json.email);
                }
            }
            xmlhttp.open("GET", "getEmail", true);
            xmlhttp.send();
            document.getElementById("buttonChangeEmail").classList.add("hidden");
        }
        document.getElementById("buttonChangePassword").onclick = function() {
        document.getElementById("formChangePassword").classList.remove("hidden");
        document.getElementById("buttonChangePassword").classList.add("hidden");
        }
    </script>
    </body>
</html>
