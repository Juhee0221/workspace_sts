package com.green.shop.item.sevice;

import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ImgVO;
import com.green.shop.item.vo.ItemVO;

import java.util.List;

public interface ItemService {

    //카테고리 목록 조회
    List<CategoryVO> selectCategory();

    //상품 목록조회
    List<ItemVO> selectItem();

    //상품 상세 목록 조회
    ItemVO selectItemDetail (int itemCode);
}
