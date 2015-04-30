<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head>
        <title><fmt:message key="wishes"/></title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
    </head>
    <body>
        <c:import url="../partial/header.jsp"/>
        <c:import url="../partial/menu.jsp"/>
        <table class="table" id="fulfilledWishes">
            <c:choose>
                <c:when test="${not empty wishes}">
                    <tr>
                        <th><fmt:message key="wish"/></th>
                        <th><fmt:message key="made_by"/></th>
                        <th><fmt:message key="date"/></th>
                    </tr>
                    <c:forEach var="wish" items="${wishes}">
                        <tr>
                            <td>${wish.wish}</td>
                            <td>
                                <a href="userPage?userId=${wish.candidate.userId}">
                                    <ctg:userPhoto photoName="${wish.candidate.photoName}" photoClass="iconPhoto"/>
                                        ${wish.candidate.name} ${wish.candidate.surname}
                                </a>
                            </td>
                            <td><fmt:formatDate value="${wish.date}"/></td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td>
                            <fmt:message key="not_found"/>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>
        <script>
            setInterval(displayFulfilledWishes, 10000);
            function displayFulfilledWishes() {
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function () {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                        var answer = xmlhttp.responseText;
                        var json = JSON.parse(answer);
                        var wishes = json.wishes;
                        if (wishes.length == 0) {
                            return;
                        }
                        var msg = {
                            wishText: "<fmt:message key="wish" />",
                            madeByText: "<fmt:message key="made_by" />",
                            dateText: "<fmt:message key="date" />"
                        };
                        var table = document.getElementById("fulfilledWishes");
                        table.replaceChild(document.createElement('TBODY'), table.tBodies[0]);
                        var nameRow = table.insertRow(0);
                        var columnName = document.createElement('th');
                        columnName.innerHTML = msg.wishText;
                        nameRow.appendChild(columnName);
                        columnName = document.createElement('th');
                        columnName.innerHTML = msg.madeByText;
                        nameRow.appendChild(columnName);
                        columnName = document.createElement('th');
                        columnName.innerHTML = msg.dateText;
                        nameRow.appendChild(columnName);
                        for (var i = 0; i < wishes.length; i++) {
                            var wish = wishes[i];
                            var row = table.insertRow(i + 1);
                            var column;
                            column = row.insertCell(0);
                            column.innerText = wish["wish"];
                            column = row.insertCell(1);
                            var link = createUserLink(wish.candidate);
                            column.appendChild(link);
                            column = row.insertCell(2);
                            column.innerText = json.dateValues[i];
                        }
                    }
                }
                xmlhttp.open("GET", "updateFulfilledWishes?userId=" + "${userId}", true);
                xmlhttp.send();
            }
        </script>
    </body>
</html>
