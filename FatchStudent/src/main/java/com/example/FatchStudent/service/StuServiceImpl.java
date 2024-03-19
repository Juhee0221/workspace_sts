package com.example.FatchStudent.service;

import com.example.FatchStudent.VO.ClassVO;
import com.example.FatchStudent.VO.ScoreVO;
import com.example.FatchStudent.VO.StuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuService")
public class StuServiceImpl implements Stuservice{
    @Autowired
    private SqlSessionTemplate sqlSession;

    //클래스명
    @Override
    public List<ClassVO> selectClass() {
        return sqlSession.selectList("selectClass");
    }

    //학번정보
    @Override
    public List<StuVO> selectStudent(StuVO stuVO) {
        return sqlSession.selectList("selectStudent", stuVO);
    }

    @Override
    public StuVO selectStuDetail(int stuNum) {
        return sqlSession.selectOne("stuMapper.selectStuDetail", stuNum);
    }

    @Override
    public ScoreVO selectScoreInfo(int stuNum) {
        return sqlSession.selectOne("stuMapper.selectScoreInfo", stuNum);
    }

    @Override
    public void insertScore(ScoreVO scoreVO) {
        sqlSession.insert("stuMapper.insertScore", scoreVO);
    }


}
