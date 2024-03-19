package com.green.shop.cart.vo;

import com.green.shop.item.vo.ImgVO;
import com.green.shop.item.vo.ItemVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartVO {
    private int cartCode;
    private int cartCnt;
    private int cartDate;
    private int itemCode;
    private String memberId;
}
