package com.example.StudentManager.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.ParameterizedType;

@Getter
@Setter
@ToString
public class StuVO {
    private int stuNo;
    private String stuName;
    private int korScore;
    private int engScore;
    private String intro;
}
