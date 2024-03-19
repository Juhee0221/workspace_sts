package com.green.Board2.service;

import com.green.Board2.VO.BoardVO;
import com.green.Board2.VO.SearchVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    //정보값 입력
    @Override
    public void insertBoard(BoardVO boardVO) {
        sqlSession.insert("insertBoard", boardVO );
    }

    //첫 화면 목록 화면 출력 조회
    @Override
    public List<BoardVO> selectBoard(SearchVO searchVO) {
        return sqlSession.selectList("selectBoard",searchVO);
    }

    //제목 클릭시 상세정보 조회
    @Override
    public BoardVO selectBoardAll(int boardNum) {
        return sqlSession.selectOne("selectBoardAll", boardNum);
    }

    // 회원정보 삭제
    @Override
    public int deleteBoard(int boardNum) {
        return sqlSession.delete("deleteBoard", boardNum);
    }

    // 회원정보 수정
    @Override
    public void updateBoard(BoardVO boardVO) {
        sqlSession.update("updateBoard", boardVO);
    }
    //조회수 증가
    @Override
    public void updateReadCnt(int boardNum) {
        sqlSession.update("boardMapper.updateReadCnt", boardNum);
    }

    @Override
    public int selectBoardCnt() {
        return sqlSession.selectOne("boardMapper.selectBoardCnt");
    }


}
