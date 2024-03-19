package com.example.vmSystem.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarVO {
    private int modelCode;
    private String modelName;
    private int modelPrice;
    private String modelMaker;
}
