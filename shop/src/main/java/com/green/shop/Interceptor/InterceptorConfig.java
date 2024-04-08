package com.green.shop.Interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//생성한 인터셉터 클래스의 적용 시점 정의
//WebMvc
//addInterceptors() 메서드를 오버라이딩

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    //interceptor가 실행되는 url
    //addInterceptor():이 메서드 한에 실행시킬 인터셉터의 객체를 전달
    //addPathPatterns(): 인터셉터의 기능이 실행되는 URL를 지정
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getPrintInterceptor())
                .order(2)
                .addPathPatterns("/item/**")
//                밑의 메서드만 제외
                .excludePathPatterns("/admin/**Fetch");
                // /admin/aaaFetch
                // /admin/bbbFetch
//                .addPathPatterns("/**/**");

        // ********** 인터셉터는 비동기 통신 메서드에는 사용 불가!
        // 그래서 비동기로 실행되는 머세드는 반드시 인터셉터 실행에서 제외
        
        registry.addInterceptor(getDBInterceptor())
                .order(1)
                .addPathPatterns("/item/test1")
                .addPathPatterns("/item/test3");
    }

    // @Bean :  메서드의 리턴 데이터를 객체로 생성
    @Bean
    public PrintInterceptor getPrintInterceptor(){
        return new PrintInterceptor();

    }
    @Bean
    public DBInterceptor getDBInterceptor(){
        return new DBInterceptor();
    }
}
