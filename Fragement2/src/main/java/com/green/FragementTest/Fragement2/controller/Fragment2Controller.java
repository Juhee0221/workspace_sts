package com.green.FragementTest.Fragement2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Fragment2Controller {

    @GetMapping("/")
    public String fragment2(Model model){
        model.addAttribute("name", "홍길동");
        model.addAttribute("age", 20);

        return "content/list";
    }

    @GetMapping("/detail")
    public String fragment(){
        return "content/detail";
    }
}
