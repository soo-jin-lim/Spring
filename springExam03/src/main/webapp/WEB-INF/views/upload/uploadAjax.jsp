<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp"%>
<style>
    .uploadResult {
        width:100%;
        background-color:grey;
    }
    .uploadResult ul {
        display:flex;
        flex-flow:row;
        justify-content:center;
        align-items:center;
    }
    .uploadResult ul li {
        list-style:none;
        padding: 10px;
    }
    .uploadResult ul li img {
        width:100px;
    }
</style>
<style>
    .bigPictureWrapper {
        position:absolute;
        display:none;
        justify-content:center;
        align-items:center;
        top:0%;
        width:100%;
        height:100%;
        background-color:grey;
        z-index:100;
    }

    .bigPicture {
        position: relative;
        justify-content: center;
        align-items: center;
    }
</style>

<div class='bigPictureWrapper'>
    <div class='bigPicture'>
    </div>
</div>

<div class="uploadDiv">
    <input type="file" name="uploadFile" multiple>
</div>
<div class="uploadResult">
    <ul></ul>
</div>
<button id="uploadBtn">file upload</button>

<script>
    function showImage(fileCallPath){
        console.log("---------------")
        console.log(fileCallPath);
        $(".bigPictureWrapper").css("display","flex").show();
        $(".bigPicture")
            .html("<img src='display?fileName="+encodeURI(fileCallPath)+"'>")
            .animate({width:'100%', height: '100%'}, 1000);
    }
    $(".bigPictureWrapper").on("click", function(e){
        $(".bigPicture").animate({width:'0%', height: '0%'}, 1000);
        setTimeout(function() {
            $(".bigPicture").hide();
        }, 1000);
    });

    var regex=new RegExp("(.*?)\.(exe|sh|zip|alz)$")
    var maxSize=5242880 // 5mb

    function checkExtension(fileName, fileSize){
        if(fileSize >= maxSize){
            alert("파일 사이즈 초과")
            return false;
        }
        if(regex.test(fileName)){
            alert("해당 종류의 파일은 업로드할 수 없습니다.")
            return false;
        }
        return true;
    }

    var cloneObj=$(".uploadDiv").clone();

  $("#uploadBtn").click(function (){
    var formData=new FormData();
    var inputFile=$("input[name='uploadFile']");
    var files=inputFile[0].files;
    console.log(files)

    for(var i=0; i<files.length; i++){

        if(checkExtension(files[i].name, files[i].size)) {
            formData.append("uploadFile", files[i])
        }
    }

    $.ajax({
      url:"/upload/uploadAjax",
      type:'post',
      processData: false,
      contentType:false,
      data:formData,
      dataType:'json'
    }).done(function (result){

      alert("success");
      console.log(result);
      showUploadFile(result);
      $(".uploadDiv").html(cloneObj.html());
    });
  });

  var uploadResult=$(".uploadResult ul");

  function showUploadFile(uploadResultArr){
      var str="";
      $(uploadResultArr).each(function (i, obj){
          if(!obj.image) {
              // 일반 파일 표시형식
              var fileCallPath = encodeURIComponent(obj.uploadpath+"/"+ obj.uuid +"_"+obj.filename);

              var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");

              str += "<li><a href='/upload/download?fileName="+fileCallPath+"'>"+
                  "<img src='/resources/img/attach.png'>"+obj.filename+"</a>"+
                  "<span data-file=\'"+fileCallPath+"\' data-type='file'> x </span>"+
                  "</li>"
          }else{
              var fileCallPath =  encodeURIComponent( obj.uploadpath+ "/s_"+obj.uuid +"_"+obj.filename);

              var originPath = obj.uploadpath+ "\\"+obj.uuid +"_"+obj.filename;

              originPath = originPath.replace(new RegExp(/\\/g),"/");

              str += "<li><a href=\"javascript:showImage(\'"+originPath+"\')\">"+
                  "<img src='display?fileName="+fileCallPath+"'></a>"+
                  "<span data-file=\'"+fileCallPath+"\' data-type='image'> x </span>"+
                  "<li>";
          }
      });
      uploadResult.append(str);
  }




</script>
<%@include file="../includes/footer.jsp"%>
