<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Register form</title>
</head>
<body>
<%@ page session="false" %>
REGISTRATION
<p></p>
<span> ${messageMap.register} </span>
<span> ${messageMap.pass} </span>
<span> ${messageMap.login} </span>
<span> ${messageMap.passwd} </span>
<form action="/register" method="post">
    Name: <input type="text" name="uname"/>
    Password: <input type="password" name="upass1">
    Password again: <input type="password" name="upass2">
    Address: <input type="text" name="uaddress"/>
    <input type="submit" name="submit" value="Register">

</form>
<a href="http://localhost:8080/login">Login</a>
</body>
</html>