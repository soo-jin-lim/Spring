package com.example.springexam03.controller;

import com.example.springexam03.dto.BoardDTO;
import com.example.springexam03.dto.PageRequestDTO;
import com.example.springexam03.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/board")// ("/Board)로 시작하는 모든 경로를 처리
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;//BoardService를 주입받음

    @GetMapping("/register")//게시글 등록 페이지를 보여주는 메서드
    public void registerGet(){

    }
    @GetMapping({"/view","/modify"})//게시글 상세보기 페이지랑 게시글 수정 페이지를 보여주는 메서드
    public void view(@RequestParam("bno") int bno,
                     PageRequestDTO pageRequestDTO,Model model){
        BoardDTO boardDTO=boardService.view(bno);//게시글 번호를 이용하여 해당 게시글의 정보를 가져옵니다.
        log.info("controller view():"+boardDTO);
        model.addAttribute("boardDTO",boardDTO);//게시글의 정보를 모델에 담아 뷰로 전달
    }
    @PostMapping("/modify")//게시글 수정을 처리하는 메서드
    public String modify(BoardDTO boardDTO,
                         RedirectAttributes redirectAttributes){
        int result=boardService.modify(boardDTO);// 게시글 등록을 수행하고, 등록된 게시글 목록 페이지로 리다이렉트
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
    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){

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

}
