package com.green.Board2.conteroller;

import com.green.Board2.VO.MemberVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class SessionTestController {

    //localhost:8081/test/
    //httpSession : 서버에 저장한 데이터를 공유함.
    // session.setAttribute
    // 세션이라는 모든페이지에서 접근할 수 있는 공유할 수 있는 공간에 데이터를 삽임.
    @GetMapping("/page1")
    public String page1(HttpSession session){

        //세션에 데이터 저장하기
        //html에 저장되지 않아도 세션에 저장되면 똑같이 사용가능.
        //model.getAttribute와 넣는 법 똑같음
        //"name" - 이름, "java" - 저장될 데이터.
        //한번만 담아놓으면 꺼내서 쓰기만 하면댐
        //세션은 웹 브라우저끼리는 공유 X , 세션이 꺼지기 전까지 공유 가능.
        //프로젝트 런하는걸 끊을 시에도 세션이 꺼짐

        session.setAttribute("name","java");
        session.setAttribute("age", 0);
        session.setAttribute("member", new MemberVO());
        //세션유지시간 설정 : 초단위
        //60초 * 30 = 30분
        session.setMaxInactiveInterval(60 * 30);


        return "page1";
    }

    @GetMapping("/page2")
    public String page2(HttpSession session){
        //세션에 담긴 데이터 확인
        //삭제와 동일하게 저장된 데이터 이름을 적어주면됨.
        //받아오는 자료형이 오브젝트이기 때문에
        //원하는 자료형으로 형변환시켜주면 완료.!!

        String name = (String)session.getAttribute("name");
        int age = (int)session.getAttribute("age");
        MemberVO vo = (MemberVO)session.getAttribute("member");


        //세션 데이터 삭제
        session.removeAttribute("age");

        //모든 세션 데이터 지우기
        //현재 세션에 저장된 모든 데이터를 지움.

        //세션 속 데이터 삭제는 자바에서만 가능.
        //세션에 담긴 데이터 보기 -> html, 자바
        session.invalidate();

        return "page2";
    }

    @GetMapping("/page3")
    public String page3(){
        return "page3";
    }
}
