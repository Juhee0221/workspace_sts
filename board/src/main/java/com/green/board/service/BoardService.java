package com.green.board.service;

import com.green.board.vo.BoardVO;

import java.util.List;

public interface BoardService {

    //게시판 등록
    int insertBoard(BoardVO boardVO);

    //게시판 조회
    List<BoardVO> selectBoardList();

    //조회된 데이터 가져오기
    BoardVO selectBoard(int boardNo);

    //게시글 삭제
    int deleteBoard(int boardNo);

    //게시글 정보 수정
    void updateBoard(BoardVO boardVO);
}
