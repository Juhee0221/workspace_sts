package test;

import com.sun.tools.javac.Main;

import javax.xml.transform.Source;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

//은행
public class Bank {
    //고객 목록
    List<Client> clientList;

    //생성자
    public Bank(){
        clientList = new ArrayList<>();

        Client c1 = new Client(1, "이순신", new Account("1111-1111", 10000, 1));
        Client c2 = new Client(2, "홍길동", new Account("1111-1112", 20000, 2));
        Client c3 = new Client(3, "유관순", new Account("1111-1113", 30000, 3));
        Client c4 = new Client(4, "장영실", new Account("1111-1114", 40000, 4));
        Client c5 = new Client(5, "신사임당", new Account("1111-1115", 50000, 5));

        clientList.add(c1);
        clientList.add(c2);
        clientList.add(c3);
        clientList.add(c4);
        clientList.add(c5);
    }

    //////////////------------ 경 계 선 ---------------//////////////////
    //위 경계선을 기준으로 위쪽의 코드는 수정하지 않습니다!

    //1. 매개변수로 전달되는 계좌번호과 회원이름과 동일한 정보를 가진
    // 고객 정보를 리턴하는 메서드를 정의하시오.
    // 만약 매개변수로 전달된 계좌번호와 일치하는 고객이 없다면 null을 리턴하시오.
    public Client list (String name, String accNUmber){

        for (Client c : clientList ){
            if (c.getName().equals(name) && c.getAccInfo().getAccNUmber().equals(accNUmber)){
                return c;
            }
            else {
                return null;
            }
        }
        return null;
    }


    //2. 매개변수로 전달되는 예금액을 초과하는 예금액을 가진 회원들의 목록을 리턴하는
    //메서드를 정의하시오.

    public String account (int balance){
        String result = "";
        for(int i = 0; i < clientList.size(); i++){
            if(balance < clientList.get(i).getAccInfo().getBalance()){
                result = clientList.toString();
                return result;
            }
        }
        return null;
    }

    // * 위 메서드를 호출하는 main 메서드를 가진 클래스는 구현할 필요 없습니다.
    //단, 위 메서드가 잘 실행되는지 확인하기 위해 main 메서드를 가진 클래스를 만들어
    //테스트 하는 것은 자유입니다.

}
