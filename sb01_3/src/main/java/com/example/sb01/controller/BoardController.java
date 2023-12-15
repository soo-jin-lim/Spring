package com.example.sb01.controller;

import com.example.sb01.dto.BoardDTO;
import com.example.sb01.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public void list(Model model) {
        model.addAttribute("boardList", boardService.getList());
    }

    @GetMapping("/register")
    public void registerGet() {

    }

    @PostMapping("/register")
    public String registerPost(BoardDTO dto) {
        int result = boardService.register(dto);
        if (result == 1) {
            return "redirect:/board/list";
        }
        return "redirect:/board/register";
    }
    @GetMapping("/remove")
    public String remove(@RequestParam("bno") int bno){
        int result=boardService.remove(bno);
        if(result==1){
            return "redirect:/board/list";
        }
        return "redirect:/board/view?bno="+bno;
    }

    @PostMapping("/modify")
    public String modify(BoardDTO dto) {
        int result=boardService.modify(dto);
        if(result==1){
            return "redirect:/board/view?bno="+dto.getBno();
        }
        return "redirect:/board/modify?bno="+dto.getBno();
    }


    @GetMapping({"/view", "/modify"})
    public void get(@RequestParam("bno") int bno, Model model) {
        model.addAttribute("board", boardService.getBoard(bno));
    }
}
