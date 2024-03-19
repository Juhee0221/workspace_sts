package test;

public class Member {
    private String name;
    private String id;
    private String pw;
    private int age;

    void setInfo(String name, String id, String pw, int age){
        this.name = name;
        this.id = id;
        this.pw = pw;
        this.age = age;

    }

    void showInfo(){
        System.out.println(" 이름 : " + name
                          +" 아이디 : " + id
                          +" 비밀번호 : " + pw
                          +" 나이 : " + age);
    }
    boolean isLogin(String id, String pw){

        if(this.id == id && this.pw == pw){
            System.out.println("로그인 가능");
            return true;
        }
        else{
            System.out.println("로그인 불가능");
            return false;
        }
    }
}
