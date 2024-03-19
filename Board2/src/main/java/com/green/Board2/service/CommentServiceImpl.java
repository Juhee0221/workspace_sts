package com.green.Board2.service;

import com.green.Board2.VO.CommentVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void insertComment(CommentVO commentVO) {
        sqlSession.insert("insertComment",commentVO);
    }

    @Override
    public List<CommentVO> selectComment(int boardNum) {
        return sqlSession.selectList("selectComment",boardNum);
    }
}
