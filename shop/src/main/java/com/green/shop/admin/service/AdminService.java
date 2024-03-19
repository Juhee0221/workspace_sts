package com.green.shop.admin.service;

import com.green.shop.admin.vo.SearchVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.cart.vo.CartViewVO;
import com.green.shop.item.vo.ItemVO;

import java.util.List;

public interface AdminService {

    //상품 등록
    //   +
    //상품 이미지 등록
    void itemInsert(ItemVO itemVO);



    //다음에 들어갈 ITEM_CODE 조회
    int selectNextItemCode();


    List<BuyVO> selectAdminList(SearchVO searchVO);

    //구매 목록 조회(관리자용)
    List<BuyVO> selectBuyList();

    BuyVO selectDetailHistory(BuyVO buyVO);

    //상품 정보 조회
    List<ItemVO> selectDetailItem();

    ItemVO selectAdmin(int itemCode);

    int updateAdmin(ItemVO itemVO);
}
