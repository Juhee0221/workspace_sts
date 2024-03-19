package com.green.shop.buy.controller;

import com.green.shop.buy.service.BuyService;
import com.green.shop.buy.vo.BuyListVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.cart.service.CartServiceImpl;
import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/buy")
public class BuyController {
    @Resource(name = "buyService")
    private BuyService buyService;
    @Resource(name = "cartService")
    private CartServiceImpl cartService;

    @GetMapping("/buyCarts")
    public String insertBuy(CartVO cartVO, HttpSession session){
        //구매 상세 테이블에 insert할 ITEM_CODE , BUY_CNT , TOTAL_PRICE 조회
        List<CartViewVO> cartViewList = cartService.selectCartListForBuy(cartVO);

        //구매자 ID
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        String memberId = loginInfo.getMemberId();


        int buyPrice = 0;

        for(CartViewVO e : cartViewList){
            //totalPrice를 하나씩 뽑아 buyPrice를 구함
            buyPrice = buyPrice + e.getTotalPrice();
        }

        //다음에 들어갈 buy_code 결정
        int buyCode = buyService.selectNextBuyCode();


        //상세장바구니에 buy_code 값 넣어주기
//        buy.setBuyDetailCode(buy_code);

        //구매 정보 및 구매상세 테이블에 insert!
        BuyVO buyVO = new BuyVO();

        // buy_code 값 설정
        buyVO.setBuyCode(buyCode);

        //멤버 아이디 세팅
        buyVO.setMemberId(memberId);

        //구매 정보 테이블에 들어갈 BUY_PRICE(구매 총 가격)
        buyVO.setBuyPrice(buyPrice);

        // 저장할 리스트 객체 생성
        List<BuyListVO> buyDetailList = new ArrayList<>();

        for(CartViewVO cartView : cartViewList){
            BuyListVO vo = new BuyListVO();

            vo.setItemCode(cartView.getItemCode());
            vo.setBuyCnt(cartView.getCartCnt());
            vo.setTotalPrice(cartView.getTotalPrice());

            buyDetailList.add(vo);
        }
        buyVO.setBuyList(buyDetailList);

        buyService.insertBuy(buyVO, cartVO);


        return "redirect:/";

    }
    @PostMapping("/insertProductBuy")
    public String insertProductBuy(CartViewVO cartViewVO,HttpSession session){

        List<BuyListVO> buyDetailList = new ArrayList<>();
        BuyVO buyVO = new BuyVO();
        BuyListVO buyListVO = new BuyListVO();

        //멤버아이디 세팅
        // 구매자 ID
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        String memberId = loginInfo.getMemberId();
        buyVO.setMemberId(memberId);

        //코드 세팅
        int buyCode = buyService.selectNextBuyCode();
        buyVO.setBuyCode(buyCode);


        int a = cartViewVO.getCartCnt();
        int b = cartViewVO.getItemPrice();

        buyVO.setBuyPrice(a * b);
        //디테일 total
        buyListVO.setTotalPrice(a * b);

        //디테일 아이템 코드
        buyListVO.setItemCode(cartViewVO.getItemCode());

        buyListVO.setBuyCnt(cartViewVO.getCartCnt());
        buyListVO.setBuyCode(buyVO.getBuyCode());
        System.out.println(cartViewVO);

        buyService.insertProductBuy(buyVO, buyListVO);

        return "redirect:/";
    }

    //구매 이력 페이지
    @GetMapping("/history")
    public String history(@RequestParam(name = "page", required = false ,defaultValue = "buy")
                                        String page, Model model ,HttpSession session){
        //로그인 정보
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        model.addAttribute("page", page);

        //구매목록조회
        List<BuyVO> buyList = buyService.selectBuyList(loginInfo.getMemberId());

        for (BuyVO e : buyList){
            System.out.println(e);
        }

        model.addAttribute("buyList", buyList);

        return "content/buy/history";
    }
}
