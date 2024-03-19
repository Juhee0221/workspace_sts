package com.green.Board2.service;

import com.green.Board2.VO.MemberVO;

public interface MemberService {

    // insert값 넣는 인터페이스 회원등록
    void insertMember(MemberVO memberVO);

    //로그인
    MemberVO login(MemberVO memberVO);

}
