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
    <h2>게시물 상세보기 페이지</h2>
    <p>${error}<br></p>
    <div class="mb-3 mt-3">
        <label for="bno">Bno:</label>
        <input type="text" class="form-control" id="bno" value="${boardDTO.bno}" readonly>
    </div>

        <div class="mb-3 mt-3">
            <label for="title">Title:</label>
            <input type="text" class="form-control" id="title" value="${boardDTO.title}" readonl>
        </div>
        <div class="mb-3 mt-3">
            <label for="content">Content:</label>
            <textarea class="form-control" rows="5" id="content" readonl>${boardDTO.content}</textarea>
        </div>
        <div class="mb-3">
            <label for="writer">Writer:</label>
            <input type="text" class="form-control" id="writer" value="${boardDTO.writer}" readonly>
        </div>
    <div class="mb-3">
        <label for="visitcount">VisitCount:</label>
        <input type="text" class="form-control" id="visitcount" value="${boardDTO.visitcount}" readonly>
    </div>
        <div class="mb-3">
            <label class="form-date-label">PostDate</label>
            <input class="form-date-input" type="date" value="${boardDTO.postdate}" readonly>
        </div>
        <button type="button" id="modify" class="btn btn-primary">modify</button>
        <button type="button" id="remove" class="btn btn-danger">remove</button>
        <button type="button" id="list" class="btn btn-info">list</button>
</div>
<div class="container mt-5">
    <h3>댓글 작성</h3>
    <div class="form-group">
        <label for="reply">Reply:</label>
        <textarea class="form-control" rows="3" id="reply" name="reply"></textarea>
    </div>
    <div class="form-group">
        <label for="replyer">Replyer:</label>
        <input type="text" class="form-control" id="replyer" name="replyer">
    </div>
    <button type="button" class="btn btn-primary" id="replyBtn">댓글 추가</button>
    <br>
    <div id="replyResult"></div>
</div>

<script>
    var init=function (){
        var bno=$("#bno").val();
        $.ajax({
            type:"get",
            url:"/replies/list/"+bno,
            dataType:"json",
        }).done(function (resp){
            console.log(resp);
            var str="<table class='table table-bover mt-3'>";
            $.each(resp, function (key, val){
                // console.log(key);
                // console.log(val);
                var date=new Date(val.postdate);
                var year=date.getFullYear();
                var month=date.getMonth()+1;
                var day=date.getDate()+1;
                var h=date.getHours();
                var m=date.getMinutes();
                var s=date.getSeconds()
                var postdate=year+"."+formatTwoDisgits(month)
                    +"."+formatTwoDisgits(day)
                    +" "+formatTwoDisgits(h)
                    +":"+formatTwoDisgits(m)
                    +":"+formatTwoDisgits(s);
                // console.log(year)

                str+="<tr>"
                str+="<td>"+val.rno+"</td>"
                str+="<td>"+val.reply+"</td>"
                str+="<td>"+val.replyer+"</td>"
                str+="<td>"+postdate+"</td>"
                str+="<td><a href='javascript:rdel("+val.rno+")'>삭제</td>"
                str+="</tr>"
            })
            str+="</table>"
            $("#replyResult").html("")
            $("#replyResult").html(str)
        })
    }
    function rdel(rno){
        $.ajax({
            type:"delete",
            url:"/replies/"+rno
        }).done(function (resp){
            alert(resp);
            init();
        }).fail(function (){
            alert("댓글 삭제 실패")
        })
    }
    $("#replyBtn").click(function (){
        var data={"bno":$("#bno").val(),
            "reply":$("#reply").val(),
            "replyer":$("#replyer").val()
        }
        $.ajax({
            type:"post",
            url:"/replies/new",
            contentType:"application/json;charset=utf-8",
            data:JSON.stringify(data),
        }).done(function (resp){
            alert(resp)
            console.log(resp)
            console.log("댓글 추가 성공")
            init();
        }).fail(function (){
            console.log("댓글 추가 실패")
        })
    })
    function formatTwoDisgits(num){
        return num < 10? "0"+num:num;
    }
    init();
</script>


<script>
    document.querySelector("#modify").addEventListener("click",function (e){
        self.location=`/board/modify?bno=${boardDTO.bno}&${pageRequestDTO.link}`
    })
    document.querySelector("#remove").addEventListener("click",function (e){
        self.location=`/board/remove?bno=${boardDTO.bno}`
    })
    document.querySelector("#list").addEventListener("click",function (e){
        self.location=`/board/list?bno=${boardDTO.bno}&${pageRequestDTO.link}`
    })
</script>

<%@include file="../includes/footer.jsp"%>