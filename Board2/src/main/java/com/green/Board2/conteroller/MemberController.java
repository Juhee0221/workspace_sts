package com.green.Board2.conteroller;

import com.green.Board2.VO.MemberVO;
import com.green.Board2.service.MemberService;
import com.green.Board2.service.MemberServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.autoconfigure.pulsar.PulsarConnectionDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member") // Controller 중간 경로.
public class MemberController {
    @Resource(name = "memberService")
    private MemberService memberService;

    //로그인 페이지로 이동
    @GetMapping("/loginForm")
    public String loginForm(){

        return "login";
    }

    //회원가입 페이지로 이동
    @GetMapping("/join")
    public String joinForm(){

        return "join";
    }

    //회원가입 후 첫번째 페이지로 이동
    @PostMapping("/join")
    public String insertJoinForm(MemberVO memberVO){

        //회원가입
        memberService.insertMember(memberVO);

        return "redirect:/member/loginForm";
    }

    //로그인
    @PostMapping("/login")
    public String login(MemberVO memberVO, HttpSession session){
        MemberVO loginInfo = memberService.login(memberVO);

        //로그인 정보가 조회가 됐으면.
        if(loginInfo != null){
            //세션에 로그인 정보를 저장
            session.setAttribute("loginInfo",loginInfo);
        }
        return "login_result";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginInfo");
        return "redirect:/member/loginForm";
    }


}
