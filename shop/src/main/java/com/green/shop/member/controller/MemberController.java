package com.green.shop.member.controller;

import com.green.shop.member.service.MemberService;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Resource(name = "memberService")
    private MemberService memberService;

    @PostMapping("/insertMember")
    public String insertMember(MemberVO memberVO){

        System.out.println(memberVO);

        memberVO.setMemberTel(memberVO.getMemberTel().replace(",", "-"));
        memberVO.setMemberEmail(memberVO.getMemberEmail().replace(",",""));

        memberService.insertMember(memberVO);

        return "redirect:/item/list";
    }
    //로그인 페이지 화면으로 이동
    @GetMapping("/login")
    public String loginForm(@RequestParam(name = "errorMsg" , required = false, defaultValue = "success") String errorMsg, Model model){

        model.addAttribute("errorMsg", errorMsg);

        return "content/member/login";
    }

//    //입력한 정보가 맞는지 세션에 저장하는 페이지로 이동
//    @PostMapping("/login")
//    public String login(MemberVO memberVO, HttpSession session){
////
////        MemberVO loginInfo = memberService.memberLogin(memberVO);
////
////        //로그인 정보가 맞으면.
////        if(loginInfo != null){
////            session.setAttribute("loginInfo", loginInfo);
////        }
//        return "content/member/loginForm";
//    }

    //비동기 로그인
//    @ResponseBody
//    @PostMapping("/loginFetch")
//    public String loginFetch(MemberVO memberVO, HttpSession session){
//
//        MemberVO loginInfo = memberService.memberLogin(memberVO);
//
//        //로그인 정보가 맞으면.
//        if(loginInfo != null){
//            session.setAttribute("loginInfo", loginInfo);
//        }
//        return loginInfo == null ? "" : loginInfo.getMemberId();
//    }

    @GetMapping("/logout")
    public String logout(HttpSession session){

        session.removeAttribute("loginInfo");

        return "redirect:/item/list";
    }
}
