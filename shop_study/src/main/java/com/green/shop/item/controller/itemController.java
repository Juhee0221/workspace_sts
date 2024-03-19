package com.green.shop.item.controller;

import com.green.shop.item.sevice.ItemService;
import com.green.shop.item.vo.ItemVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//상품과 관련된 모든 페이지 이동 처리 컨트롤러
@Controller
@RequestMapping("/item")
public class itemController {
    @Resource(name = "itemService")
    private ItemService itemService;

    //상품 목록 페이지
    @GetMapping("/list")
    public String list(Model model){
        List<ItemVO> itemList = itemService.selectItem();

        model.addAttribute("itemList", itemList);
        return "content/item/item_list";
    }
    @GetMapping("/detailList")
    public String detailList(@RequestParam(name = "itemCode") int itemCode, Model model) {
        ItemVO item = itemService.selectItemDetail(itemCode);
        System.out.println(item);
        model.addAttribute("item", item);


        return "content/item/item_detail";
    }

}
