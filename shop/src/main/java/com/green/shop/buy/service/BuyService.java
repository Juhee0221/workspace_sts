package com.green.shop.buy.service;

import com.green.shop.buy.vo.BuyListVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.cart.vo.CartVO;

import java.util.List;

public interface BuyService {

    void insertBuy(BuyVO buyVO, CartVO cartVO);

    int selectNextBuyCode();

    void insertProductBuy(BuyVO buyVO, BuyListVO buyListVO);

    // 구매 목록 조회(사용자용)
    List<BuyVO> selectBuyList(String memberId);
}
