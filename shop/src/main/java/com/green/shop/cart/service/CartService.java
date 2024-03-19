package com.green.shop.cart.service;

import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;

import java.util.List;

public interface CartService {

    void insertCart(CartVO cartVO);

    List<CartViewVO> selectView(String memberId);

    //한개의 상품 삭제
    void deleteCart(int cateCode);

    void updateCartCnt(CartViewVO cartViewVO);

    //장바구니 상품들 삭제
    void deleteCarts(CartVO cartVO);

    //장바구니에 담긴 상품 구매를 위한 장바구니 목록 조회
    List<CartViewVO> selectCartListForBuy(CartVO cartVO);

}
