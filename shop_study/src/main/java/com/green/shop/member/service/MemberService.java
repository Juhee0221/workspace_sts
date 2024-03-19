package com.green.shop.member.service;

import com.green.shop.member.vo.MemberVO;

public interface MemberService {
    void insertMember(MemberVO memberVO);

    MemberVO memberLogin(MemberVO memberVO);
}
