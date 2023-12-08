<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>
<div class="container mt-3">
    <h2>Striped Rows</h2>
    <p>The .table-striped class adds zebra-stripes to a table:</p>
    <table class="table table-striped">
        <tbody>
        <tr>
            <th>username</th>
            <td>${member.username}</td>
        </tr>
        <tr>
            <th>name</th>
            <td>${member.name}</td>
        </tr>
        <tr>
            <th>Nikname</th>
            <td>${member.nikname}</td>
        </tr>
        <tr>
            <th>regdate</th>
            <td>${member.regdate}</td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="button" id="passUpdateBtn" class="btn btn-primary">비밀번호 변경</button>
                <button type="button" id="deleteBtn" class="btn btn-dark">탈퇴</button>${mgs}
            </td>
        </tr>
        </tbody>
    </table>
</div>
<script>
    document.querySelector("#deleteBtn").addEventListener("click",function (e){
        e.preventDefault();
        alert("정말 탈퇴하시겠습니까?")
        self.location="/member/remove"
    })
</script>
<%@include file="../includes/footer.jsp"%>
