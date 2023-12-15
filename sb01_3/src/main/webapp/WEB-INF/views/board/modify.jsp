<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
<form action="/board/modify" method="post">
    <input type="hidden" name="bno" value="${board.bno}">
<table>
    <tr>
        <th>title</th>
        <td><input type="text" name="title" value="${board.title}"></td>
    </tr>
    <tr>
        <th>content</th>
        <td>
            <textarea name="content" rows="3" cols="50">
                ${board.content}
            </textarea>
        </td>
    </tr>
    <tr>
        <th>Writer</th>
        <td><input type="text" name="writer" value="${board.writer}"></td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <input id="modify" type="submit" value="modify">
            <input id="list" type="button" value="list">
        </td>
    </tr>
</table>
<%--</form>--%>

<script>
    $("#list").click(function (){
        self.location="/board/list";
    });
</script>
</body>
</html>