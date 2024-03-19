package com.green.shop.cart.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@ToString
public class CartViewVO {
    private int cartCode;
    private int cartCnt;
    private int cartDate;
    private int itemCode;
    private String memberId;
    private int itemPrice;
    private int totalPrice;

    /* memberVO*/

    private String memberName;
    private String memberTel;
    private String address;

    /* cateVO */
    private int cateCode;
    private String cateName;

    /* imgVO */
    private String originFileName;
    private String attachedFileName;
    private String isMain;
    private int imgCode;

    /* item VO */

    private String itemName;
    private String itemIntro;
    /* 1:N 관계 / imgVO를 여러개 가지고 있음 */

}
