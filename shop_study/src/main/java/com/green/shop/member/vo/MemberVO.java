package com.green.shop.member.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
    private String memberId;
    private String memberPw;
    private String memberName;
    private String gender;
    private String memberTel;
    private String memberEmail;
    private String memberAddr;
    private String addrDetail;
    private String memberRoll;
    private String postCode;
    private String joinDate;
}
