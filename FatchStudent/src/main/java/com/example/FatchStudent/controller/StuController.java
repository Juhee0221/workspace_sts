package com.example.FatchStudent.controller;

import com.example.FatchStudent.VO.ClassVO;
import com.example.FatchStudent.VO.DetailVO;
import com.example.FatchStudent.VO.ScoreVO;
import com.example.FatchStudent.VO.StuVO;
import com.example.FatchStudent.service.Stuservice;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StuController {
    @Resource(name = "stuService")
    private Stuservice stuservice;

    @GetMapping("/")
    String select (StuVO stuVO,Model model){

        model.addAttribute("class", stuservice.selectClass());
        model.addAttribute("stu", stuservice.selectStudent(stuVO));

        return "stu_manage";
    }

    @ResponseBody
    @PostMapping("/fetchSelect")
    public List<StuVO> fetchSelect(StuVO stuVO){
        List<StuVO> stuList = stuservice.selectStudent(stuVO);
        return stuList;
    }

    @ResponseBody
    @PostMapping("/stuSelect")
    public DetailVO stuSelect(@RequestParam(name = "stuNum")int stuNum){
        System.out.println("z");

        //클릭한 학생의 상세 정보 조회
        StuVO stuInfo = stuservice.selectStuDetail(stuNum);
        //클릭한 학생의 점수 정보 조회
        ScoreVO scoreInfo = stuservice.selectScoreInfo(stuNum);
        //조회한 데이터를 가지고 자바스크립트로 이동

        //두개의 데이터를 가진 VO
        DetailVO result = new DetailVO();
        //두개의 데이터를 각 seter로 데이터 값 설정
        result.setStuVO(stuInfo);
        result.setScoreVO(scoreInfo);

        System.out.println("a");

        return result;
    }
    @ResponseBody
    @PostMapping("/scoreInsert")
    public void scoreInsert(ScoreVO scoreVO){
        stuservice.insertScore(scoreVO);
    }
}
