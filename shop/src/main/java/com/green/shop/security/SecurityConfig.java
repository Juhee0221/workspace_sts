package com.green.shop.security;



import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//인증 인가에 대한 설정을 위한 클래스
//@EnableWebSecurity : security 설정 파일 임을 알려 주는 Annotation
//@Configuration : 설정
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailHandler loginFailHandler;

    //1.인증과 인가에 대한 설정 내용이 있는 메소드 구현
    //반드시 리턴타입은 SecurityFilterChain
    //2. 메소드의 매개변수로 HttpSecurity 필요.

    // 리턴 : security.build();
    // 메소드 안에 인증과 인가의 정보를 모두 다 삽입
    @Bean //객체를 생성하는 annotation.
    // @Bean : 리턴되는 데이터를 객체로 만들어줌.
    // throws Exception : 직접 예외처리를 하지 않고 임의로 다른 곳에  예외처리를 하겠다.
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {

        // 보안을 뚫는 해킹에 대해 어떻게 대처를 할 것 인가.
        // 토큰 사용시 : csrf 공격을 무효화 시킴
        // AbstractHttpConfigurer :: disable : 공격에 대한 대처 방법을 사용 하지 않겠다.
        security.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        //인증 및 인가 설정이 다 들어옴
                        c -> {
                            //1. 인증처리
                            //permitAll : requestMatchers()에 요청 경로에 대한 것을 다 승인 : 인증 X(로그인)
                            c.requestMatchers(
                                        //로그인 안해도 들어올 수 있는 페이지
                                        // CSS , IMG , JAVASCRIPT 도 보여지지 않기 때문에
                                        // security(인증)에서 제외 시켜줘야됨
                                        new AntPathRequestMatcher("/"),
                                        new AntPathRequestMatcher("/item/list"),
                                        new AntPathRequestMatcher("/member/loginForm"),
                                        new AntPathRequestMatcher("/member/join"),
                                        new AntPathRequestMatcher("/member/login")
                                    ).permitAll()
                                    //hasRole : 어떤 권한이 있어야 경로를 들어갈 수 있음(인가에 대한 설정)
                                    .requestMatchers(
                                            // /admin/** : ADMIN과 관련된 모든 페이지.
                                            new AntPathRequestMatcher("/admin/**")

                                    ).hasRole("ADMIN")
                                    .anyRequest().authenticated();
                        }
                )
                .formLogin(
                        //
                        //로그인 방식을 적음
                        formLogin ->{
                            // 로그인
                            // loginProcessingUrl :
                            formLogin.loginPage("/member/loginForm")
                                    // 아래의 요청이 들어 오면(로그인 경로와 들어 오는 경로를) 알아서 로그인.
                                    // 약속 : ID - username , pw - password
                                    // 데이터로 가져오는 아이디와 비밀번호 이름을 위와 같이 적어줘야됨.
                                    .loginProcessingUrl("/member/login")

                                    // 로그인 성공 하면 보낼 주소 작성
                                    // 로그인이 성공하면 내가 가고자하는 페이지 또는 이전페이지.
                                    // true : 성공하면 무조건 적어놓은 url로 이동.
                                    //.defaultSuccessUrl("/")
//
//                                    // 로그인 실패시 이동하는 url
                                    //.failureUrl("/member/loginForm")

                                    // 우리가 정한 컬럼명으로 데이터를 가져오고 싶을때는
                                    // 데이터 이름을 이런식으로 들고 오겠다고 알려줘야됨.
                                    .usernameParameter("memberId")
                                    .passwordParameter("memberPw")

                                    //로그인 성공 시 실행 시킬 클래스를 객체
                                    .successHandler(loginSuccessHandler)
                                    //로그인 실패 시 실행 시킬 클래스를 객체
                                    .failureHandler(loginFailHandler);

                                    //로그인을 하기 위해서는 데이터베이스에서 원하는 정보를 조회 후 security에게 전달해줘야됨.

                        }
                )
                .logout(
                        //로그아웃과 관련된 설정 작성
                        logout -> {

                        }

                );
        // security 의 인증과 인가 값을 return 시켜주겠다.
        // 반드시 예외처리를 해야됨 .
        return  security.build();
    }

    //정적인 자원은 security가 인증 및 인가 관리에서 제외하도록 설정하는 메소드
    //정적인 파일 : javaScript , css , 이미지
    //resources 폴더 밑에 있는 모든파일
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){

        return web -> web.ignoring().requestMatchers(
                new AntPathRequestMatcher("/upload/**"),
                new AntPathRequestMatcher("/css/**"),
                new AntPathRequestMatcher("/js/**"),
                new AntPathRequestMatcher("/images/**"),
                new AntPathRequestMatcher("/favicon.ico"),
                new AntPathRequestMatcher("/error")
        );
    }
}
