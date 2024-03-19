package com.green.shop.security;

import com.green.shop.member.service.MemberService;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 평소에 제작하던 serviceImpl와 비슷한 형태

@Service("UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource(name = "memberService")
    private MemberService memberService;

    // security가 로그인 프로세스를 진행하면 가장 먼저 호출 되는 메소드
    // 실제 로그인을 처리하는 메소드는 아님!
    // Security 가 로그인을 처리할 수 있도록
    // 로그인 정보를 Security 에게 전달 하는 역할.
    @Override
    // throws ~ : 예외 전가, 직접 처리 X
    public UserDetails loadUserByUsername(String username) {
        //인터페이스를 구현한 클래스는 리턴 타입이 인터페이스라도
        //클래스를 리턴할 수 있음.
        //로그인을 시도하는 유저의 정보를 조회
        // username : 로그인 할려는 사람의 ID
        // ID, PW , 권한
        MemberVO loginInfo = memberService.memberLogin(username);

        if(loginInfo == null){
            // 매개변수 필요.
            throw new BadCredentialsException("error");
        }

        // 로그인 하려는 유저 정보를 Security 에게 넘겨 줌
        // 패키지에 맞는 User 선택
        User user = (User)User.builder()
                        .username(loginInfo.getMemberId())
                        .password("{noop}" + loginInfo.getMemberPw())
                        .roles(loginInfo.getMemberRoll())
                        .build();
        return user;
    }
}

