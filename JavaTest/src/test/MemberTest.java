package test;

public class MemberTest {
    public static void main(String[] args) {
        Member member = new Member();

        member.setInfo("자바", "java", "abcd1234", 20);
        member.showInfo();
        member.isLogin("java", "abcd1234");
        member.isLogin("java", "abcd");
    }
}
