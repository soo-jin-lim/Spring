<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-12-04
  Time: 오후 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>

<div class="container mt-3">
    <h2>게시물 수정 페이지</h2>
    <p>${error}<br></p>
    <form action="/board/modify" method="post">
        <input type="hidden" name="bno" value="${boardDTO.bno}">
        <div class="mb-3 mt-3">
            <label for="title">Title:</label>
            <input type="text" class="form-control" id="title" value="${boardDTO.title}" name="title">
        </div>
        <div class="mb-3 mt-3">
            <label for="content">Content:</label>
            <textarea class="form-control" rows="5" id="content" name="content">${boardDTO.content}</textarea>
        </div>
<%--        <div class="mb-3">--%>
<%--            <label for="writer">Password:</label>--%>
<%--            <input type="text" class="form-control" id="writer" placeholder="Enter Writer" name="writer">--%>
<%--        </div>--%>
<%--        <div class="mb-3">--%>
<%--            <label class="form-date-label">PostDate</label>--%>
<%--            <input class="form-date-input" type="date" name="postdate">--%>
<%--        </div>--%>
        <button type="submit" class="btn btn-primary">modify</button>
        <button type="button" class="btn btn-secondary">list</button>
    </form>
</div>
<script>
    $(function (){
        $(".btn-secondary").click(function (){
            location.href="/board/list"
        })
    })
</script>
<script>
    const formObj=document.querySelector("form")
    document.querySelector(".btn-primary").addEventListener("click",function (e){
        e.preventDefault();
        formObj.action="/board/modify"
        formObj.method="post"
        formObj.submit()
    })
    document.querySelector(".btn-secondary").addEventListener("click",function (e){
       e.preventDefault()
       self.location=`/board/list?${pageRequestDTO.link}`
    })


</script>

<%@include file="../includes/footer.jsp"%>