<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-12-11
  Time: 오전 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/upload/uploadForm" method="post" enctype="multipart/form-data">
    <input type="file" name="uploadFile" multiple>
    <button>파일업로드</button>
</form>
</body>
</html>
