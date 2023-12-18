package com.example.springexam03.controller;

import com.example.springexam03.dto.BoardDTO;
import com.example.springexam03.dto.PageRequestDTO;
import com.example.springexam03.service.BoardService;
import com.example.springexam03.vo.BoardAttachVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/register")
    public void registerGet(){

    }

    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        log.info(boardDTO);
        if(bindingResult.hasErrors()){
            log.info("error...........");
            redirectAttributes.addFlashAttribute("error",bindingResult.getAllErrors());
            return "redirect:/board/register";
        }
        int result=boardService.register(boardDTO);
        if(result!=1){
            redirectAttributes.addFlashAttribute("error","데이터 입력 실패");
            return "redirect:/board/register";
        }
        return "redirect:/board/list";
    }
    @GetMapping({"/view","/modify"})
    public void view(@RequestParam("bno") int bno,
                     PageRequestDTO pageRequestDTO,Model model){
        BoardDTO boardDTO=boardService.view(bno);
        log.info("controller view():"+boardDTO);
        model.addAttribute("boardDTO",boardDTO);
    }
    @PostMapping("/modify")
    public String modify(@ModelAttribute BoardDTO boardDTO, MultipartFile[] uploadFile,
                         RedirectAttributes redirectAttributes){

        String uploadFolder="d:/upload";
        List<BoardAttachVO> attachVOList=new ArrayList<>();

        String uploadFolderPath = getFolder();
        // make folder --------
        File uploadPath = new File(uploadFolder, uploadFolderPath);
        log.info("upload path: " + uploadPath);

        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }
        for(MultipartFile multipartFile:uploadFile){
            BoardAttachVO attachVO=new BoardAttachVO();
            log.info("++++++++++++++++++++++++++++++");
            log.info("Upload file name:"+multipartFile.getOriginalFilename());
            log.info("Upload file size:"+multipartFile.getSize());

            String uploadFileName=multipartFile.getOriginalFilename();
            attachVO.setFilename(uploadFileName);
            uploadFileName=uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);

            UUID uuid=UUID.randomUUID();
            uploadFileName=uuid.toString()+"_"+uploadFileName;

            File saveFile=new File(uploadPath, uploadFileName);
            try{
                attachVO.setUuid(uuid.toString());
                attachVO.setUploadpath(uploadFolderPath);
                multipartFile.transferTo(saveFile);

                if(checkImageType(saveFile)){
                    attachVO.setFiletype(true);
                    FileOutputStream thumbnail=new FileOutputStream(
                            new File(uploadPath, "s_"+uploadFileName));
                    Thumbnailator.createThumbnail(multipartFile.getInputStream(),
                            thumbnail, 100,100);
                    thumbnail.close();
                }
                attachVOList.add(attachVO);
                deleteFile(boardDTO.getAttachVOList());
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        boardDTO.setAttachVOList(attachVOList);
        int result=boardService.modify(boardDTO);
        redirectAttributes.addAttribute("bno",boardDTO.getBno());
        if(result!=1){
            redirectAttributes.addFlashAttribute("error","데이터 업데이트 실패");
            return "redirect:/board/modify";
        }
        return  "redirect:/board/view";
    }
    @GetMapping("/remove")
    public String remove(@RequestParam("bno") int bno,
                         RedirectAttributes redirectAttributes){
        log.info("remove.........");
        int result=boardService.remove(bno);
        if(result !=1) {
            redirectAttributes.addFlashAttribute("bno", bno);
            redirectAttributes.addFlashAttribute("error","데이터 삭제 실패");
            return "redirect:/board/view";
        }
        return "redirect:/board/list";
    }


    @GetMapping("/list")
    public void pagingList(@Valid PageRequestDTO pageRequestDTO,
                           BindingResult bindingResult,Model model){
        log.info(pageRequestDTO);
        if(bindingResult.hasErrors()){
            pageRequestDTO=PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO",boardService.getPagingList(pageRequestDTO));
    }

    //@GetMapping("/list")
    public void list(Model model){
        List<BoardDTO> boardDTOS=boardService.getList();
        int totalCount=boardService.totalCount();
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("boardList",boardDTOS);
    }

    @GetMapping(value = "/getAttachList")
    @ResponseBody
    public ResponseEntity<List<BoardAttachVO>> getAttachList(int bno) {
        log.info("getAttachList " + bno);
        return new ResponseEntity<>(boardService.attachList(bno), HttpStatus.OK);

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

    private void deleteFile(List<BoardAttachVO> attachVOList) {
        for(BoardAttachVO attachVO:attachVOList) {
            String fileName=attachVO.getUploadpath()+"/"
                    +attachVO.getUuid()+"_"+attachVO.getFilename();

            log.info("deleteFile: " + fileName);
            File file;
            try {
                file = new File("d:\\upload\\"
                        + URLDecoder.decode(fileName, "UTF-8"));
                file.delete();
                if (attachVO.isFiletype()) {// 이미지 파일 원본 지우기
                    String largeFileName = file.getAbsolutePath()
                            .replace("s_", "");
                    log.info("largeFileName: " + largeFileName);
                    file = new File(largeFileName);
                    file.delete();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();

            }
        }
    }


}
