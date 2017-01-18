<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 1/16/17
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table>
    <c:forEach items="${personList}" var="person">
        <tr>
            <form action="/update" method="post">
                <td><input type="text" name="uname"  value="${person.getUsername()}" readonly/></td>
                <td><input type="text" name="upass"  value="${person.getPassword()}"/></td>
                <td><input type="text" name="uaddress"  value="${person.getAddress()}"/></td>
                <td><input type="submit" name="save" value="Save"></td>
                <td><input type="submit" name="delete" value="Delete"></td>
            </form>

        </tr>
    </c:forEach>
</table>

</body>
</html>
