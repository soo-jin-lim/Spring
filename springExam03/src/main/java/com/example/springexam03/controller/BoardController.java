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
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/register")
    public void registerGet(){

    }
    @GetMapping({"/view","/modify"})
    public void view(@RequestParam("bno") int bno,
                     PageRequestDTO pageRequestDTO,Model model){
        BoardDTO boardDTO=boardService.view(bno);
        log.info("controller view():"+boardDTO);
        model.addAttribute("boardDTO",boardDTO);
    }
    @PostMapping("/modify")
    public String modify(BoardDTO boardDTO,
                         RedirectAttributes redirectAttributes){
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
