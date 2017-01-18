
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Welcome page</title>
</head>
<body>
<%@ page session="false" %>
<h1> Hello ${messageMap.userName} </h1>
<span> ${messageMap.success} </span>
<p></p>
<a href="http://localhost:8080/logout">Logout</a>
</body>
</html>