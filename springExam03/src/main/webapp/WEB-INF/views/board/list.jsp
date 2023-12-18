<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>

<div class="container mt-5">
    <h3>SEARCH🔍</h3>
    <form class="form-inline" method="get">
        <div class="form-group">
            <label>Types:</label>
            <input type="checkbox" name="types" value="t" ${pageRequestDTO.checkType("t")?"checked":""}>제목
            <input type="checkbox" name="types" value="c" ${pageRequestDTO.checkType("c")?"checked":""}>내용
        </div>
        <div class="form-group">
            <label>Keyword:</label>
            <input type="text" name="keyword" value="${pageRequestDTO.keyword}">
        </div>
        <div class="form-group">
            <label>PostDate</label>
            <input type="date" name="from" value="${pageRequestDTO.from}">
            <input type="date" name="to" value="${pageRequestDTO.to}">
        </div>
        <br>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>
    <h2>🎄Christmas List🎄</h2>
    <p>게시물 수 : ${responseDTO.total}</p>
    <table class="table">
        <thead>
        <tr>
            <th>Bno</th>
            <th>Title</th>
            <th>Writer</th>
            <th>Visitcount</th>
            <th>Replycount</th>
            <th>Postdate</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${responseDTO.dtoList}" var="board">
            <tr>
                <td>${board.bno}</td>
                <td><a href="view?bno=${board.bno}&${pageRequestDTO.link}">${board.title}</a></td>
                <td>${board.writer}</td>
                <td>${board.visitcount}</td>
                <td>${board.replycount}</td>
                <td>${board.postdate}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5">
                <ul class="pagination justify-content-center">
                    <c:if test="${responseDTO.prev}">
                        <li class="page-item"><a class="page-link" data-num="${responseDTO.start-1}">Previous</a></li>
                    </c:if>
                    <c:forEach begin="${responseDTO.start}" end="${responseDTO.end}" var="num">
                        <li class="page-item ${num==responseDTO.page?'active':''}">
                            <a class="page-link" data-num="${num}">${num}</a></li>
                    </c:forEach>
                    <c:if test="${responseDTO.next}">
                        <li class="page-item"><a class="page-link" data-num="${responseDTO.end+1}">Next</a></li>
                    </c:if>
                </ul>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<script>
    document.querySelector(".pagination").addEventListener("click",function (e){
        e.preventDefault();
        e.stopPropagation();
        const target=e.target;
        if(target.tagName !='A'){
            return;
        }
        const num=target.getAttribute("data-num");
        const formObj=document.querySelector("form");
        formObj.innerHTML += `<input type='hidden' name='page' value='\${num}'>`
        formObj.submit();
    }, false)
</script>
<%@include file="../includes/footer.jsp"%>
