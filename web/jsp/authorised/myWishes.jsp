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
        <table class="table" id="myWishes">
            <tr>
                <td>
                    <fmt:message key="not_found"/>
                </td>
            </tr>
        </table>
        <script>
            setInterval(displayMyWishes, 1000);
            function displayMyWishes() {
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
                            ownerText: "<fmt:message key="wish_owner" />",
                            statusText: "<fmt:message key="status" />",
                            dateText: "<fmt:message key="date" />",
                            labelPerformed: "<fmt:message key="wish_performed" />",
                            wishFulfilledText: "<fmt:message key="wish_fulfilled" />",
                            applicationMadeText: "<fmt:message key="application_made" />",
                            buttonCancel: "<fmt:message key="cancel" />"
                        };
                        var table = document.getElementById("myWishes");
                        table.replaceChild(document.createElement('TBODY'), table.tBodies[0]);
                        var nameRow = table.insertRow(0);
                        var columnName = document.createElement('th');
                        columnName.innerHTML = msg.wishText;
                        nameRow.appendChild(columnName);
                        columnName = document.createElement('th');
                        columnName.innerHTML = msg.ownerText;
                        nameRow.appendChild(columnName);
                        columnName = document.createElement('th');
                        columnName.innerHTML = msg.dateText;
                        nameRow.appendChild(columnName);
                        columnName = document.createElement('th');
                        columnName.innerHTML = msg.statusText;
                        nameRow.appendChild(columnName);
                        for (var i = 0; i < wishes.length; i++) {
                            var wish = wishes[i];
                            var row = table.insertRow(i + 1);
                            var column;
                            column = row.insertCell(0);
                            column.innerText = wish["wish"];
                            column = row.insertCell(1);
                            var link = createUserLink(wish.owner);
                            column.appendChild(link);
                            column = row.insertCell(2);
                            if (wish.date != null) {
                                column.innerText = json.dateValues[i];
                            }
                            column = row.insertCell(3);
                            if (wish.isFulfilled == true) {
                                if (wish.date != null) {
                                    column.innerText = msg.wishFulfilledText;
                                } else {
                                    column.innerText = msg.labelPerformed;
                                }
                            }
                            else {
                                column.innerHTML = "<p>" + msg.applicationMadeText + "</p>";
                                if (json.isPageOwner == true) {
                                    column.innerHTML += "<form action=cancelApplication method=post>" +
                                    "<input type=hidden name=pageCode value=1>" +
                                    "<input type=hidden name=wishId value=" + wish.wishId + ">" +
                                    "<input type=hidden name=userId value=" + "${currentUserId}" + ">" +
                                    "<input type=submit value=" + msg.buttonCancel + ">" +
                                    "</form>";
                                }
                            }

                        }
                    }
                }
                xmlhttp.open("GET", "updateMyWishes?userId=" + "${param.userId}", true);
                xmlhttp.send();
            }
        </script>
    </body>
</html>
