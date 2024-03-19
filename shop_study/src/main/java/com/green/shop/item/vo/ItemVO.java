package com.green.shop.item.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ItemVO {
    private int itemCode;
    private String itemName;
    private int itemPrice;
    private int itemStock;
    private String itemIntro;
    private String regDate;
    private int cateCode;
    /* 1:N 관계 / imgVO를 여러개 가지고 있음 */
    private List<ImgVO> imgList;
}
