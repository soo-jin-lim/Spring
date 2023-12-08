<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-12-08
  Time: 오전 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>
<div class="container mt-3">
    <h4>${msg}</h4>
<form action="/member/login" method="post">
    <div class="mb-3 mt-3">
        <label for="username" class="form-label">Username:</label>
        <input type="email" class="form-control" id="username" placeholder="Enter email" name="username">
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">Password:</label>
        <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
    </div>
    <button type="submit" class="btn btn-primary">Login</button>
    <button type="button" class="btn btn-info">Join</button>
</form>
</div>
<script>
    document.querySelector(".btn-info").addEventListener("click",function (e){
        e.preventDefault();
        self.location="/member/join";
    })
</script>
<%@include file="../includes/footer.jsp"%>