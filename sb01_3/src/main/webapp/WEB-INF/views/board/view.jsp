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
<%--<form action="/board/register" method="post">--%>
<table>
    <tr>
        <th>Bno</th>
        <td><input type="text" name="bno" value="${board.bno}" readonly></td>
    </tr>
    <tr>
        <th>title</th>
        <td><input type="text" name="title" value="${board.title}" readonly></td>
    </tr>
    <tr>
        <th>content</th>
        <td>
            <textarea name="content" rows="3" cols="50" readonly>
                ${board.content}
            </textarea>
        </td>
    </tr>
    <tr>
        <th>Writer</th>
        <td><input type="text" name="writer" value="${board.writer}" readonly></td>
    </tr>
    <tr>
        <th>Visit Count</th>
        <td><input type="text" name="writer" value="${board.visitcount}" readonly></td>
    </tr>
    <tr>
        <th>Post Date</th>
        <td><input type="text" name="writer" value="<fmt:formatDate value="${board.postdate}"/>" readonly></td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <input id="modify" type="button" value="modify">
            <input id="remove" type="button" value="remove">
            <input id="list" type="button" value="list">
        </td>
    </tr>
</table>
<%--</form>--%>

<script>
    $("#modify").click(function (){
        self.location=`/board/modify?bno=${board.bno}`;
    });
    $("#remove").click(function (){
        self.location=`/board/remove?bno=${board.bno}`;
    });
    $("#list").click(function (){
        self.location="/board/list";
    });
</script>
</body>
</html>