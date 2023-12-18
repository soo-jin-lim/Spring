package com.example.springexam03.controller;

import com.example.springexam03.dto.AttachFileDTO;
import jakarta.servlet.annotation.MultipartConfig;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Log4j2
//@MultipartConfig(
//        location = "d:/upload/temp",
//        maxFileSize = 1024*1024*10,
//        maxRequestSize = 1024*1024*20,
//        fileSizeThreshold = 1024*1024*10
//)
@RequestMapping("/upload")
public class FileUploadController {
    String uploadFolder="d:/upload";

    @GetMapping("/uploadAjax")
    public void uploadAjaxGet(){

    }
    @PostMapping("/uploadAjax")
    @ResponseBody
    public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile){
        List<AttachFileDTO> dtoList=new ArrayList<>();

        String uploadFolderPath=getFolder(); //yyyy/mm/dd=>2023/12/11
        log.info("uploadPath:"+uploadFolderPath);
        File uploadPath=new File(uploadFolder,uploadFolderPath);
        //d:/upload/2023/12/11  d:/upload
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        for(MultipartFile multipartFile:uploadFile){

            AttachFileDTO attachFileDTO=new AttachFileDTO();

            log.info("-----------");
            log.info("Upload file name:"+multipartFile.getOriginalFilename());
            log.info("Upload file size:"+multipartFile.getSize());
            String uploadFileName=multipartFile.getOriginalFilename();

            attachFileDTO.setFilename(uploadFileName);

            UUID uuid=UUID.randomUUID();
            uploadFileName=uuid.toString()+"_"+uploadFileName;

            //File saveFile=new File(uploadPath,multipartFile.getOriginalFilename());
            File saveFile=new File(uploadPath,uploadFileName);
            //saveFile=d:/upload/2023/12/12/uuid_abc.png
            try{
                multipartFile.transferTo(saveFile);
                //
                attachFileDTO.setUuid(uuid.toString());
                attachFileDTO.setUploadpath(uploadFolderPath);

                //check image type file
                if(checkImageType(saveFile)){
                    FileOutputStream thumbnail=new FileOutputStream(
                            new File(uploadPath,"s_"+uploadFileName));
                    Thumbnailator.createThumbnail(multipartFile.getInputStream(),
                            thumbnail,100,100);
                    thumbnail.close();

                    attachFileDTO.setImage(true);
                }
                dtoList.add(attachFileDTO);
            }catch (Exception e){
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping("/deleteFile")
    @ResponseBody
    public ResponseEntity<String> deleteFile(String fileName, String type) {
        log.info("deleteFile: " + fileName);
        File file;
        try {
            file = new File("d:\\upload\\"
                    + URLDecoder.decode(fileName, "UTF-8"));
            file.delete();
            if (type.equals("image")) {// 이미지 파일 원본 지우기
                String largeFileName = file.getAbsolutePath()
                        .replace("s_", "");
                log.info("largeFileName: " + largeFileName);
                file = new File(largeFileName);
                file.delete();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }

    @GetMapping(value = "/download",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(
            @RequestHeader("User-Agent") String userAgent,
            @RequestParam("fileName") String fileName) {
        log.info("download file: " + fileName);
        Resource resource = new FileSystemResource("d:\\upload\\" + fileName);
        if (resource.exists() == false) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("resource: " + resource);
        String resourceName = resource.getFilename();
        String resourceOriginalName=resourceName.substring(resourceName.lastIndexOf("_")+1);
        HttpHeaders headers = new HttpHeaders();
        try {
            //headers.add("Content-Disposition","attachment; filename=" +
            //new String(resourceName.getBytes("UTF-8"), "ISO-8859-1"));
            String downloadName = null;

            if (userAgent.contains("Trident")) {
                log.info("IE browser");
                downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8")
                        .replaceAll("\\+", " ");
            }else if(userAgent.contains("Edge")) {
                log.info("Edge browser");
                downloadName =  URLEncoder.encode(resourceOriginalName,"UTF-8");
            }else {
                log.info("Chrome browser");
                downloadName = new String(resourceOriginalName
                        .getBytes("UTF-8"), "ISO-8859-1");
            }

            log.info("downloadName: " + downloadName);

            headers.add("Content-Disposition",
                    "attachment; filename=" + downloadName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }

    @GetMapping("/display")
    @ResponseBody
    public ResponseEntity<byte[]> getFile(String fileName){
        log.info("fileName:"+fileName);
        File file=new File("d:\\upload\\"+fileName);
        File file1=new File(uploadFolder,fileName);
        log.info(file);
        log.info(file1);
        ResponseEntity<byte[]> result=null;
        try{
            HttpHeaders headers=new HttpHeaders();
            headers.add("Content-Type", Files.probeContentType(file.toPath()));
            result=new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),headers,
                    HttpStatus.OK);
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/uploadForm")
    public void uploadFormGet(){

    }
    @PostMapping("/uploadForm")
    public void uploadFormPost(MultipartFile[] uploadFile){
        for(MultipartFile multipartFile:uploadFile){
            log.info("-----------");
            log.info("Upload file name:"+multipartFile.getOriginalFilename());
            log.info("Upload file size:"+multipartFile.getSize());

            File saveFile=new File(uploadFolder,multipartFile.getOriginalFilename());
            try{
                multipartFile.transferTo(saveFile);
            }catch (Exception e){
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    //yyyy/mm/dd => 2023/12/11
    private String getFolder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);
        return str.replace("-", File.separator); //"2023\\12\\12"
    }
    // 파일타입 체크
    private boolean checkImageType(File file) {
        try {
            String contentType = Files.probeContentType(file.toPath());
            log.info("contentType:"+contentType);
            return contentType.startsWith("image");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
