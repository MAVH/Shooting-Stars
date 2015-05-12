<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<fmt:setLocale value="${currentLocale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
    <head>
        <title></title>
        <style>
            .changePhotoBlock{
                margin:40px auto 0px auto;
                width:460px;
                height: 180px;
                padding: 10px;
                background-color: #FEFFDF;
                text-align: center;
                border: solid 2px firebrick;
                border-radius: 5px;
                -webkit-box-shadow: 0 5px 18px #3f241d;
                box-shadow: 0 5px 18px #3f241d;
            }
            .changePhotoForm input[type="file"] {
                display: block;
                background-color: #fbeed5;
                margin-top: 10px;
                margin-left: auto;
                margin-right: auto;
            }
            .changePhotoForm input[type="submit"] {
                margin-top: 10px;
                float: right;
            }
            .cancelChangePhotoButton {
                float: left;
                margin-top: -5px;
            }
            .changePhotoBlock h3 {
                color: #953b39;
            }
            .changePhotoForm .errorLabel, .changePhotoForm .label {
                display: none;
            }
            .fileform {
                background-color: #FFFFFF;
                border: 1px solid #CCCCCC;
                border-radius: 4px;
                cursor: pointer;
                height: 26px;
                overflow: hidden;
                padding: 2px;
                position: relative;
                text-align: left;
                vertical-align: middle;
                width: 230px;
                margin-top: 10px;
                margin-left: auto;
                margin-right: auto;
            }

            .fileform .selectbutton {
                background-color: #ffa61b;
                border: 1px solid #939494;
                border-radius: 2px;
                color: #FFFFFF;
                float: right;
                font-size: 16px;
                height: 20px;
                line-height: 20px;
                overflow: hidden;
                padding: 2px 6px;
                text-align: center;
                vertical-align: middle;
                width: 50px;
            }
            .fileform #photo {
                position:absolute;
                top:0;
                left:0;
                width:100%;
                -moz-opacity: 0;
                filter: alpha(opacity=0);
                opacity: 0;
                font-size: 150px;
                height: 30px;
                z-index:20;
            }
            .fileform #fileformlabel {
                background-color: #FFFFFF;
                float: left;
                height: 22px;
                line-height: 22px;
                overflow: hidden;
                padding: 2px;
                text-align: left;
                vertical-align: middle;
                width:160px;
            }
        </style>
        <script>
            function getUploadedPhotoName (str){
                if (str.lastIndexOf('\\')){
                    var i = str.lastIndexOf('\\')+1;
                }
                else{
                    var i = str.lastIndexOf('/')+1;
                }
                var filename = str.slice(i);
                var uploaded = document.getElementById("fileformlabel");
                uploaded.innerHTML = filename;
            }
        </script>
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

