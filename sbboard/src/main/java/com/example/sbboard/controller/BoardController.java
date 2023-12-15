package com.example.sbboard.controller;

import com.example.sbboard.model.Board;
import com.example.sbboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/register")
    public void register(){

    }
    @PostMapping("/register")
    public String register(Board board){
        Board board1=boardService.register(board);
        if(board1!=null){
            log.info(board1);
            return "redirect:/board/list";
        }
        return "redirect:/board/register";
    }

    @GetMapping({"/view","modify"})
    public void view(Long bno, Model model){
        model.addAttribute("board",boardService.getBoard(bno));
    }
    @PostMapping("/modify")
    public String modify(Board board){
        Board board1=boardService.modify(board);
        if(board1 != null){
            return "redirect:/board/view?bno="+board.getBno();
        }
        return "redirect:/board/modify?bno="+board.getBno();
    }
    @GetMapping("/remove")
    public String remove(Long bno){
        boardService.remove(bno);
        return "redirect:/board/list";
    }


    @GetMapping("/list")
    public void list(Model model, @PageableDefault(size=5,sort="bno",
    direction = Sort.Direction.DESC)Pageable pageable){
        model.addAttribute("msg","list page");
        Page<Board> boardList=boardService.getList(pageable);
        model.addAttribute("totalCount",boardService.totalCount());
        model.addAttribute("boardList",boardList);
    }

    @GetMapping("/writer/{writer}")
    @ResponseBody
    public List<Board> getWriter(@PathVariable("writer") String writer){
        List<Board> boardList=boardService.getByWriter(writer);
        return boardList;
    }

    @GetMapping("/content/{keyword}")
    @ResponseBody
    public List<Board> getKeyword(@PathVariable("keyword") String keyword){
        List<Board> boardList=boardService.getByContent(keyword);
        return boardList;
    }
}
