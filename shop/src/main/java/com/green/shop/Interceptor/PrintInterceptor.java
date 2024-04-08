package com.green.shop.Interceptor;

import com.green.shop.member.vo.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

//Interceptor 클래스
//HandlerInterceptor 인터페이스를 구현한 클래스는 Interceptor 클래스로 인식

//해당 클래스에는 반복되는 기능을 정의
//반복되는 기능의 호출 시점에 따라서
//HandlerInterceptor 인터페이스의 메서드를 오버라이딩
//오버라이딩 할 수 있는 메소드
//preHandle():
//postHandle():
//afterCompletion():
public class PrintInterceptor implements HandlerInterceptor {

    @Override
    //preHandle 주로 권한 체크용으로 사용되었음.
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("PrintInterceptor:preHandle() 메소드 실행~~  ");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("PrintInterceptor:preHandle() 메소드 실행~~  ");

        //컨트롤러에서 전달한 데이터 확인
        Map<String , Object> modelData = modelAndView.getModel();
        
        String name = modelData.get("name").toString();
        MemberVO member = (MemberVO) modelData.get("member");
        
        modelAndView.addObject("addr", "울산");
    }

}
