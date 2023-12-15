<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td>bno</td>
        <td>title</td>
        <td>writer</td>
        <td>visitcount</td>
        <td>postdate</td>
    </tr>
    <c:forEach var="board" items="${boardList}">
        <tr>
            <td>${board.bno}</td>
            <td><a href="/board/view?bno=${board.bno}">${board.title}</a>a</td>
            <td>${board.writer}</td>
            <td>${board.visitcount}</td>
            <td><fmt:formatDate value="${board.postdate}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>