<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2023-12-04
  Time: 오후 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>

<style>
    .uploadResult {
        width: 100%;
        background-color: grey;
    }

    .uploadResult ul {
        display: flex;
        flex-flow: row;
        justify-content: center;
        align-items: center;
    }

    .uploadResult ul li {
        list-style: none;
        padding: 10px;
    }

    .uploadResult ul li img {
        width: 100px;
    }
</style>

<style>
    .bigPictureWrapper {
        position: absolute;
        display: none;
        justify-content: center;
        align-items: center;
        top:0%;
        width:100%;
        height:100%;
        background-color: grey;
        z-index: 100;
    }

    .bigPicture {
        position: relative;
        display:flex;
        justify-content: center;
        align-items: center;
    }
</style>

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

        <div class='uploadResult'>
            <ul>

            </ul>
        </div>


        <div class="mb-3">
            <label class="form-date-label">PostDate</label>
            <input class="form-date-input" type="date" value="${boardDTO.postdate}" readonly>
        </div>
        <c:if test="${boardDTO.writer==member.nikname}">
            <button type="button" id="modify" class="btn btn-primary">modify</button>
            <button type="button" id="remove" class="btn btn-danger">remove</button>
        </c:if>
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
        <input type="text" class="form-control" id="replyer" value=${member.nikname} name="replyer">
    </div>
    <button type="button" class="btn btn-primary" id="replyBtn">댓글 추가</button>
    <br>
    <div id="replyResult"></div>
</div>

<script>

    var bno=$("#bno").val()
    $.getJSON("/board/getAttachList",{bno, bno}, function (arr){
        console.log(arr);
        var str="";
        $(arr).each(function (i, attach){
            //image type
            if(attach.filetype){
                var fileCallPath =  encodeURIComponent( attach.uploadpath+ "/s_"+attach.uuid +"_"+attach.filename);

                str += "<li data-path='"+attach.uploadpath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.filename+"' data-type='"+attach.filetype+"' ><div>";
                str += "<img src='/upload/display?fileName="+fileCallPath+"'>";
                str += "</div>";
                str +"</li>";
            }else{
                str += "<li data-path='"+attach.uploadpath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.filename+"' data-type='"+attach.filetype+"' ><div>";
                str += "<span> "+ attach.filename+"</span><br/>";
                str+= " <a href='/upload/download?fileName="+fileCallPath+"'>"
                str += "<img src='/resources/img/attach.png'></a>";
                str += "</div>";
                str +"</li>";
            }
        });
        $(".uploadResult ul").html(str);
    })

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
                if("${sessionScope.member.nikname}"==val.replyer) {
                    str += "<td><a href='javascript:rdel(" + val.rno + ")'>삭제</td>"
                }
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

    $("#modify").click(function (e){
        location.href="board/modify?bno="+`${boardDTO.bno}&${pageRequestDTO.link}`
    })
</script>

<%@include file="../includes/footer.jsp"%>