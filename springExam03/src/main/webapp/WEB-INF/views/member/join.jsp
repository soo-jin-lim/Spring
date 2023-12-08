<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>
<div class="container mt-3">
<form action="/member/join" method="post">
  <div class="mb-3 mt-3">
    <label for="username" class="form-label">Username:</label>
    <input type="email" class="form-control" id="username" placeholder="Enter email" name="username">
    <button type="button" id="usernameBtn" class="btn btn-secondary">아이디 중복 확인</button>
  </div>
  <div class="mb-3">
    <label for="password" class="form-label">Password:</label>
    <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
  </div>
  <div class="mb-3">
    <label for="name" class="form-label">Name:</label>
    <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
  </div>
  <div class="mb-3">
    <label for="nikname" class="form-label">Nikname:</label>
    <input type="text" class="form-control" id="nikname" placeholder="Enter nikname" name="nikname">
    <button type="button" id="niknameBtn" class="btn btn-secondary">닉네임 중복 확인</button>
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
  <button type="reset" class="btn btn-default">Reset</button>
</form>
</div>
<script>
  // document.querySelector("#usernameBtn").addEventListener("click",function (){
  // })
  $("#usernameBtn").click(function (){
      var username=$("#username").val();
      $.ajax({
        type:"get",
        url:"/member/confromedUsername/"+username
      }).done(function (resp){
        console.log(resp);
        if(resp=='success'){
          alert(username+"은 사용 가능합니다.")
        }else{
          alert(username+"은 이미 존재합니다.")
        }
      })
  })
  $("#niknameBtn").click(function (){
    console.log("aaaaaaaaaa");
    var nikname=$("#nikname").val();
    $.ajax({
      type:"get",
      url:"/member/confromedNikname/"+nikname
    }).done(function (resp){
      console.log(resp);
      if(resp=='success'){
        alert(nikname+"은 사용 가능합니다.")
      }else{
        alert(nikname+"은 이미 존재합니다.")
      }
    })
  })
</script>
<%@include file="../includes/footer.jsp"%>
