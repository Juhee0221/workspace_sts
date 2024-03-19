package com.green.Board2.service;

import com.green.Board2.VO.BoardVO;
import com.green.Board2.VO.SearchVO;

import java.util.List;

public interface BoardService {

    //insert
    void insertBoard(BoardVO boardVO);

    //목록조회
    List<BoardVO> selectBoard(SearchVO searchVO);

    //상세조회
    BoardVO selectBoardAll(int boardNum);

    //삭제
    int deleteBoard(int boardNum);

    //수정
    void updateBoard(BoardVO boardVO);

    //조회수 증가
    void updateReadCnt(int boardNum);

    //게시글 수 조회
    int selectBoardCnt();
}
