package com.green.Board2.service;

import com.green.Board2.VO.CommentVO;

import java.util.List;

public interface CommentService {

    void insertComment(CommentVO commentVO);

    List<CommentVO> selectComment(int boardNum);

}
