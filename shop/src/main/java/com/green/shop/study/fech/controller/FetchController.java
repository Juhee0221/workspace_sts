package com.green.shop.study.fech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/fetch")
public class FetchController {


    @GetMapping("/main")
    public String main(){

        return "test/fetch/main";
    }
    //넘어오는 데이터를 받을 때 사용하는 어노테이션
    //@RequestParam
    // - url에 데이터가 함께 넘어올 떄 사용
    // - ex ) localhost:8081/aaa?a=b
    // - form 태그를 사용
    //@RequestBody
    // - url이 아닌 body 영역에 데이터가 담겨서 올때
    @ResponseBody
    @PostMapping("/fetch1")
    public void fetch(@RequestBody MemberVO memberVO){
        System.out.println("fetch1 메서드 실행~");
        System.out.println(memberVO);
    }

    @ResponseBody
    @PostMapping("/fetch2")
    //맵을 통해서 데이터를 받을 수 있음!
    public void fetch2(@RequestBody HashMap<String, String> data){
        System.out.println("fetch2 메서드 실행~");
//        System.out.println(memberVO);
        System.out.println(data);
        System.out.println(data.get("id"));
    }

    //자바스크립트에서 배열이 넘어오면 ArrayList로 받을 수 있음.
    @ResponseBody
    @PostMapping("/fetch3")
    //맵을 통해서 데이터를 받을 수 있음!
    public void fetch3(@RequestBody ArrayList<MemberVO> list){
        System.out.println("메서드 실행~");
        System.out.println(list);
    }

    @ResponseBody
    @PostMapping("/fetch4")
    //맵을 통해서 데이터를 받을 수 있음!
    public void fetch4(@RequestBody HashMap<String, Object> map){
        System.out.println("메서드 실행~");
        System.out.println(map);

        //1. cartCode;
        System.out.println(map.get("cartCode"));

        int cartCode = (int)map.get("cartCode");

        //2. memberId
        HashMap<String, String> memberInfo = (HashMap<String, String>)map.get("memberInfo");
        System.out.println(memberInfo.get("memberId"));
        System.out.println(map.get("memberInfo"));
    }

}
