<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>
<div class="container mt-3">
    <h2>회원등록 페이지</h2>
     <form action="/member/register" method="post">
        <div class="mb-3 mt-3">
            <label for="username">username:</label>
            <input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
            <button type="button" id="userBtn" class="btn btn-secondary">중복확인</button>
        </div>
         <div class="mb-3 mt-3">
             <label for="password">Password:</label>
             <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
         </div>
        <div class="mb-3 mt-3">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" placeholder="Enter Writer" name="name">
        </div>
         <div class="mb-3 mt-3">
             <label for="nikname">NikName:</label>
             <input type="text" class="form-control" id="nikname" placeholder="Enter Nikname" name="nikname">
             <button type="button" id="nikBtn" class="btn btn-secondary">중복확인</button>
         </div>
        <button type="submit" class="btn btn-primary">Register</button>
        <button type="reset" class="btn btn-secondary">Reset</button>
    </form>
</div>

<script>
    $("#userBtn").click(function (){
        var username=$("#username").val();
        $.ajax({
            type:"get",
            url:"/member/confromUsername/"+username
        }).done(function (resp){
            if(resp=='success')
                alert(username+"은 사용가능합니다");
            else
                alert(username+"은 이미 존재합니다.")
        })
    })

    $("#nikBtn").click(function (){
        var nikname=$("#nikname").val();
        $.ajax({
            type:"get",
            url:"/member/confromNikname/"+nikname
        }).done(function (resp){
            if(resp=='success')
                alert(nikname+"은 사용가능합니다");
            else
                alert(nikname+"은 이미 존재합니다.")
        })
    })

</script>
<%@include file="../includes/footer.jsp"%>


