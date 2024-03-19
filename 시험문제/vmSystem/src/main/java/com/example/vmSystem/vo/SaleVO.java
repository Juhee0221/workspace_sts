package com.example.vmSystem.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SaleVO {
    private int saleCode;
    private String modelBuyer;
    private String buyerTel;
    private String modelColor;
    private String saleDate;
    private int modelCode;
    private CarVO carVO;
}
