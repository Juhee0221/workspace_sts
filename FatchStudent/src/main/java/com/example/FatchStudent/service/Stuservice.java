package com.example.FatchStudent.service;

import com.example.FatchStudent.VO.ClassVO;
import com.example.FatchStudent.VO.ScoreVO;
import com.example.FatchStudent.VO.StuVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Stuservice {
    List<ClassVO> selectClass ();

    List<StuVO> selectStudent(StuVO stuVO);

    //학생 상세 정보조회
    StuVO selectStuDetail(int stuNum);

    //점수 정보조회
    ScoreVO selectScoreInfo(int stuNum);

    //점수 등록
    void insertScore(ScoreVO scoreVO);
}
