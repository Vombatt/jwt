<%-- 
    Document   : login
    Created on : Jun 29, 2017, 3:22:47 PM
    Author     : alexey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <title>Login</title>
    </head>
    <body>
        <input type="text" placeholder="username" name="username">
        <input type="password" placeholder="password" name="password">
        <input type="button" value="SEND" name="send" onclick="sendData()">
    </body>
</html>
