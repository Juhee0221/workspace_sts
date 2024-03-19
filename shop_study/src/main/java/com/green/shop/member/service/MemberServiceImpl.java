package com.green.shop.member.service;

import com.green.shop.member.vo.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void insertMember(MemberVO memberVO) {
        sqlSession.insert("insertMember", memberVO);
    }

    @Override
    public MemberVO memberLogin(MemberVO memberVO) {
        return sqlSession.selectOne("memberMapper.memberLogin", memberVO);
    }
}
