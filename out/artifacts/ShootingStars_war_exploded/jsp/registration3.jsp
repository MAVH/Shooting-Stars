<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${currentLocale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
  <title><fmt:message key="registration_page_title"/></title>
  <%--<script type="text/javascript" src="js/script.js"></script> --%>
    <script language="JavaScript" type="text/javascript">
        function addWish() {
            var fields = (document).getElementsByName("wish");
            for(var i=0;i<fields.length;i++) {
                if(fields[i].classList.contains("hidden")) {
                    fields[i].classList.remove("hidden");
                    break;
                }
            }
        }
    </script>
</head>
<body>
<c:import url="header.jsp"/>
<form method="POST" action="${pageContext.request.contextPath}/registration">
  <input type="hidden" name="command" value="prepare_registration"/>
  <input type="hidden" name="partValue" value="3"/>
  <label for="abilities"><fmt:message key="abilities"/></label>
  <input type="textarea" name="abilities" id="abilities"/>
  <%--wishes--%>
  <input type="text" name="wish" class="hidden"/>
  <input type="text" name="wish" class="hidden"/>
  <input type="text" name="wish" class="hidden"/>
  <input type="text" name="wish" class="hidden"/>
  <input type="text" name="wish" class ="hidden"/>
  <input type="submit" name="submitAction" value="<fmt:message key="continue"/>"/>
  <input type="submit" name="submitAction" value="<fmt:message key="back"/>">
</form>
<button id = "button" onclick="addWish()">One more wish</button>
${registrationError}
</body>
</html>
