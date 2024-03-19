package com.green.Board2.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentVO {
    private int commentNum;
    private String commentWriter;
    private String content;
    private String createDate;
    private int boardNum;
}
