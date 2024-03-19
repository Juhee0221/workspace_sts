package com.green.BasicBoard.vo;

import lombok.*;

// @Data
// @AllArgsConstructor
// @Builder

// 생성자를 구현하는 어노테이션
//@NoArgsConstructor // 매개변수가 없는 기본 생성자
//@AllArgsConstructor // 멤버 변수 모두를 매개변수로 받는 생성자
//@RequiredArgsConstructor

// 기본생성자, setter , getter , toString
@Data
@Builder //다수의 생성자를 만들어주는 역할
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
    // final 사용시 기본값 null 값이 들어감.
    // 초기값이 필요하기 때문에 매개 변수를 필요로 함.
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberRoll;
}

class BuilderTest {
    public void Test(){
        MemberVO v1 = MemberVO.builder()
                        .memberId("java")
                        .build();

        MemberVO v2 = MemberVO.builder()
                        .memberId("java")
                        .memberName("hong")
                        .build();

        MemberVO v3 = MemberVO.builder()
                            .memberName("hong")
                            .memberPw("1111")
                            .memberRoll("USER")
                            .build();

        MemberVO v4 = MemberVO.builder().build();
    }
}