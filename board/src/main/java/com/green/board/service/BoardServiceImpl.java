package com.green.board.service;

import com.green.board.vo.BoardVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BoardService")
public class BoardServiceImpl implements BoardService {
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public int insertBoard(BoardVO boardVO){
        int board = sqlSession.insert("insertBoard", boardVO);
        return board;
    }

    @Override
    public List<BoardVO> selectBoardList() {
        return sqlSession.selectList("selectBoardList");
    }

    @Override
    public BoardVO selectBoard(int boardNo) {

        return sqlSession.selectOne("selectBoard", boardNo);
    }

    @Override
    public int deleteBoard(int boardNo) {
        return sqlSession.delete("deleteBoard", boardNo);
    }

    @Override
    public void updateBoard(BoardVO boardVO) {
        sqlSession.update("boardMapper.updateBoard", boardVO);
    }


//    @Override
//    public BoardVO selectBoard(int borderNo) {
//        return sqlSession.selectOne("selectBoard", borderNo);
//    }

}
