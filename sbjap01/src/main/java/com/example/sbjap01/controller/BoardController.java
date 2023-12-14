package com.example.sbjap01.controller;

import com.example.sbjap01.model.Board;
import com.example.sbjap01.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/list")
    public void list(Model model){
        List<Board> boardList=boardService.getList();
        model.addAttribute("boardList", boardList);
    }
}
