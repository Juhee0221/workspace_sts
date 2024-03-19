package com.green.shop.admin.controller;

import com.green.shop.item.sevice.ItemService;
import com.green.shop.item.sevice.ItemServiceImpl;
import com.green.shop.item.vo.CategoryVO;
import com.green.shop.study.fech.controller.MemberVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// RestController - 자동으로 비동기 통신
@RequestMapping("/rest")
public class ReactController {
    @Resource(name = "itemService")
    private ItemService itemService;

    @GetMapping("/test1")
    public String test1(){

        return "test.html";
    }

    @GetMapping("/test2")
    public List<CategoryVO> test2(){

        itemService.selectCategory();

        return itemService.selectCategory();
    }

}
