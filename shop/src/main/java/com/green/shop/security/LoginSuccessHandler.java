package com.green.shop.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

//로그인 성공시 실행되는 클래스
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    //로그인 성공 시 자동으로 호출되는 메소드
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!");
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        //로그인 성공시 이동할 페이지
        setDefaultTargetUrl("/");

        // 로그인 시 필요한 코드 여기서 작성
        // ex > session에 데이터 저장
        // 최근 로그인 ,내역 관리
        HttpSession session = request.getSession();

//        session.setAttribute();
//        session.getAttribute();

        //로그인 정보를 통한 로직 구성
        //로그인 한 사람의 정보를 security 에서 받아옴.
        User user = (User)authentication.getPrincipal();

        //security에서 권한 정보를 빼올 수 있음
        List<GrantedAuthority> authoList = new ArrayList<>(user.getAuthorities());
        List<String> aList = new ArrayList<>();

        for (GrantedAuthority authority : authoList){
            // 권한 정보 넣어 주기 / 이해하기 쉬운 문자열로 권한 목록 만들기
            aList.add(authority.getAuthority());
        }
        //권한 목록을 받아 왔을때 원하는 권한이 있을 때를 물어봄
        boolean b = aList.contains("ADMIN");

        if(b){
            // 권한이 있을때 원하는 페이지로 이동.
            redirectStrategy.sendRedirect(request, response, "/admin/regItem");
        }

        //이전 페이지 혹은 가려던 페이지가 있는 경우
        // 가고 싶은 원하는 페이지가 있을때는 여기서 수정!!

        if(savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }else{
            redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
        }
    }




}
