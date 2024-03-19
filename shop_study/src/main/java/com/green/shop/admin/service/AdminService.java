package com.green.shop.admin.service;

import com.green.shop.item.vo.ItemVO;

public interface AdminService {

    //상품 등록
    //   +
    //상품 이미지 등록
    void itemInsert(ItemVO itemVO);



    //다음에 들어갈 ITEM_CODE 조회
    int selectNextItemCode();
}
