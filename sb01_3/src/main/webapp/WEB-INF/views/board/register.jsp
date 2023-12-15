<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/board/register" method="post">
<table>
    <tr>
        <th>title</th>
        <td><input type="text" name="title"></td>
    </tr>
    <tr>
        <th>content</th>
        <td><textarea name="content" rows="3" cols="50"></textarea></td>
    </tr>
    <tr>
        <th>Writer</th>
        <td><input type="text" name="writer"></td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <input type="submit" value="register">
            <input type="reset" value="reset">
        </td>
    </tr>
</table>
</form>
</body>
</html>