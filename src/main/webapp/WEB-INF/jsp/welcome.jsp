<%-- 
    Document   : welcome
    Created on : Jun 29, 2017, 2:16:17 PM
    Author     : alexey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
<!--        <script type="text/javascript"
                src="<c:url value='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'/>">    
        </script>-->
        <title>Welcome</title>
        <script type="text/javascript"
                src="<c:url value='/js/main.js'/>">
        </script>
        <script type="text/javascript"
                src="<c:url value='/js/lib/jquery-3.2.1.js'/>">
        </script>
    </head>
    <body>
        <h1>Hello Master!</h1>
        <input class="inp" type="text" value="gggggggggg">
        <input class="test" type="button" value="Тык!" onclick="hello()">
        <input id="us"type="text" placeholder="username" name="username">
        <input id="pa"type="password" placeholder="password" name="password">
        <input type="button" value="SEND" name="send" onclick="sendData()">
    </body>
</html>
