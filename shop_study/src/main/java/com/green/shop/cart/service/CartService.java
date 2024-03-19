package com.green.shop.cart.service;

import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;

import java.util.List;

public interface CartService {

    void insertCart(CartVO cartVO);

    List<CartViewVO> selectView(String memberId);

    void deleteCart(int cateCode);

    void updateCartCnt(CartViewVO cartViewVO);
}
