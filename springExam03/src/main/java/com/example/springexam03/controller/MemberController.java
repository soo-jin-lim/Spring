package com.example.springexam03.controller;

import com.example.springexam03.dto.MemberDTO;
import com.example.springexam03.service.MemberService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/confromedUsername/{username}")
    @ResponseBody
    public ResponseEntity<String> conformedUsername(@PathVariable("username")
                                                      String username){
        log.info(username);
        int result= memberService.conformedUsername(username);
        String str=null;
        if(result>0){
            str="fail";
        }else {
            str = "success";
        }
         return new ResponseEntity<>(str, HttpStatus.OK);
    }
    @GetMapping("/confromedNikname/{nikname}")
    @ResponseBody
    public ResponseEntity<String> conformNikname(@PathVariable("nikname")
                                                  String nikname){
        log.info(nikname);
        int result= memberService.conformedNikname(nikname);
        String str=null;
        if(result>0){
            str="fail";
        }else
            str="success";
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @GetMapping("/login")
    public void login(){

    }

    @GetMapping("/join")
    public void registerGet(){
    }

    @PostMapping("/join")
    public String registerPost(MemberDTO dto){
        int result=memberService.register(dto);
        if(result != 1){
            return "redirect:/member/join";
        }
        return "redirect:/member/login";
    }
    @PostMapping("/login")
    public String loginPro(String username, String password,
                           HttpServletRequest request,
                           RedirectAttributes redirectAttributes){
        MemberDTO dto=memberService.loginPro(username, password);
        if(dto!=null){
            HttpSession session=request.getSession();
            session.setAttribute("member",dto);
            return "redirect:/board/list";
        }
        redirectAttributes.addFlashAttribute("msg","로그인 실패");
        return "redirect:/member/login";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest req){
        HttpSession session=req.getSession();
        session.removeAttribute("member");
        //session.invalidate();
        return "redirect:/board/list";
    }
    @GetMapping("/info")
    public void info(){
    }
    public String remove(HttpServletRequest req,
                         RedirectAttributes redirectAttributes){
        HttpSession session=req.getSession();
        MemberDTO dto=(MemberDTO) session.getAttribute("member");
        int result=memberService.remove(dto.getUsername());
        if(result==1){
            session.invalidate();
            return "redirect:/board/list";
        }
        redirectAttributes.addFlashAttribute("msg","탈퇴실패");
        return "redirect:/member/info";
    }

}
