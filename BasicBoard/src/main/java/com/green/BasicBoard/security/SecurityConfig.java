package com.green.BasicBoard.security;


//스프링 시큐리티 인증, 인가에 대한 프로세스를 정의

import com.green.BasicBoard.vo.MemberVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//이 클래스가 시큐리티 설정파일임을 인지시켜주는 역할
// /member/*
// ex) /member/a 는 가능 하지만 /member/a/c 두번의 댑스는 불가능.
// /member/**
// /member/a , /member/a/b , /member/a/b/... 하위에 몇 단계가 있어도 가능.
@EnableWebSecurity

//객체 생성 어노테이션 (@Controller , @Service)
@Configuration //설정
public class SecurityConfig {

    //암호화에 사용하는 객체 생성
    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }
    // @Bean : 객체 생성 어노테이션
    // 메소드의 실행 결과 리턴되는 데이터를 객체로 생성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
            // csrf  공격에 대한 방어를 햐자 하겠가
        security.csrf(AbstractHttpConfigurer::disable)
                    //authorizeHttpRequests 메소드 안에서 인증 및 인가 관리
                .authorizeHttpRequests(
                    c -> {
                        // "/"의 요청에 대해서는 누구나 다 가능하다.
                        c.requestMatchers(
                                        new AntPathRequestMatcher("/"),
                                        new AntPathRequestMatcher("/loginForm") ,
                                        new AntPathRequestMatcher("/joinForm"),
                                        new AntPathRequestMatcher("/join"),
                                        new AntPathRequestMatcher("/login") ,
                                        new AntPathRequestMatcher("/sample")

                                        ).permitAll()
                                // ADMIN 이라는 권한이 있는 계정만 가능
                                .requestMatchers(
                                        new AntPathRequestMatcher("/admin")
                                ).hasRole("ADMIN")
                                .requestMatchers(
                                        new AntPathRequestMatcher("/manager")
                                ).hasRole("MANAGER")
                                .requestMatchers(
                                        new AntPathRequestMatcher("/boardWriteForm")
                                ).hasAnyRole("USER" , "MANAGER")
                                // 다른 경우에서는 인증이 되야된다
                                .anyRequest().authenticated();
                    }
                ).formLogin(

                        formLogin -> {

                            //로그인 페이지 url 설정
                            formLogin.loginPage("/loginForm")
                                    //로그인 시 전달되는 id 및 name 속성값 지정
                                    .usernameParameter("memberId")
                                    .passwordParameter("memberPw")
                                    //로그인 기능이 실행되는 url
                                    .loginProcessingUrl("/login")
                                    //로그인 성공 시 이동할 url
                                    // 두번째 매개변수로 true를 넣으면 항상 지정한 url로 이동!
                                    // 두번째 매개변수가 없으면 이전 페이지로 이동.
                                    // 이번 페이지가 없다면 지정한 url로 이동
                                    .defaultSuccessUrl("/")
                                    .failureUrl("/loginForm");
                        }
                )
                .logout(
                    logout -> {
                        logout.logoutUrl("/logout")
                                // 로그아웃 성공 시 이동할 url
                                .logoutSuccessUrl("/")
                                // 로그아웃 성공 시 세션 데이터 삭제
                                .invalidateHttpSession(true);
                    }

                )
                // 예외 발생시 처리해야 하는 내용을 작성
                .exceptionHandling(
                        ex -> {
                            ex.accessDeniedPage("/deny");
                        }
                );

        return security.build();
    }






//        //csrf : csrf 공격에 대해 어떻게 대처할거냐??
//        security.csrf(AbstractHttpConfigurer::disable)
//                //아래 메소드 안에 인증, 인가에 대한 모든 설정작성
//                .authorizeHttpRequests(
//                    c -> {
//                        // "/"의 요청에 대해서는 누구나 다 가능하다.
//                        c.requestMatchers(
//                                new AntPathRequestMatcher("/"),
//                                new AntPathRequestMatcher("/loginForm"),
//                                new AntPathRequestMatcher("/joinForm")
//                                ).permitAll()
//                                // 다른 경우에서는 인증이 되야된다
//                                .anyRequest().authenticated();
//                    }
//                )
//                //로그인 설정 + 로그인 할때 form방식으로 쓰겠다.
//                .formLogin(
//                        formLogin -> {
//                            //로그인 페이지로 이동하는 url 설정
//                            formLogin.loginPage("/loginForm")
//                                    // 실제 로그인 기능을 실행하는 url 설정
//                                    .loginProcessingUrl("/login")
//                                    // 로그인 성공 시 기본적으로 이동하는 페이지
//                                    .defaultSuccessUrl("/");
//                        }
//                );
//
//        return security.build();



}
