package com.green.shop.cart.controller;

import com.green.shop.cart.service.CartService;
import com.green.shop.cart.service.CartServiceImpl;
import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import com.green.shop.member.service.MemberService;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Resource(name = "cartService")
    private CartServiceImpl cartService;


    //장바구니 상품 등록
    @ResponseBody
    @PostMapping("/insert")
    public void insertCart(CartVO cartVO, HttpSession session){

        //세션에 저장되어 있는 아이디 가져오기.
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        cartVO.setMemberId(loginInfo.getMemberId());

        cartService.insertCart(cartVO);
    }

    //장바구니 목록 조회
    @GetMapping("/list")
    public String list(Model model, HttpSession session){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        List<CartViewVO> cartList = cartService.selectView(loginInfo.getMemberId());
        model.addAttribute("cartList", cartList);

        //총 가격을 계산 한 후 html 전달
        int sum = 0;
        for(CartViewVO cart  : cartList){
            sum = sum + cart.getTotalPrice();
        }
        // model.addAttribute("finalPrice", sum);

        return "content/cart/cart_list";
    }
    @GetMapping("delete")
    public String delete(@RequestParam(name = "cartCode")int cateCode){
        System.out.println(cateCode);

        cartService.deleteCart(cateCode);
        return "redirect:/cart/list";
    }

    @ResponseBody
    @PostMapping("update")
    public void update(CartViewVO cartViewVO){
        cartService.updateCartCnt(cartViewVO);
    }

}
