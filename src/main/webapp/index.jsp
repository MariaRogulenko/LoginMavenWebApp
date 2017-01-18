<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<%@ page session="false" %>
<span> ${messageMap.login} </span>
<span> ${messageMap.passwd} </span>
<span> ${messageMap.success} </span>
<form action="/login" method="post">
    Login: <input type="text" name="uname"/>
    Password: <input type="text" name="pass"/>
    <input type="submit" value="submit"/>

</form>
<a href="http://localhost:8080/register">Register</a>

</body>
</html>