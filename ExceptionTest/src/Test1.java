import java.util.InputMismatchException;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {

        System.out.println("프로그램 시작");
        //예외처리
        Scanner sc = new Scanner(System.in);

        //예외가 발생할 수 있는 코드를 삽입
        //예외가 발생하면, catch에 매개변수로 매개가 발생한 정보를 넘겨줌
        //예외를 담을 수 있는 통들은 클래스로 만들어져 있음.
        //예외가 발생하면 콘솔에 어떤 통에 예외를 담아서 주는지 알려줌.
        //여러 가지 이유로 예외로 발생하기 때문에 넘겨주는 자료형으로 받아
        // 예외 처리를 해주어야 됨
        try {
            int num1 = sc.nextInt();
            int num2 = sc.nextInt();
            System.out.println(num1 / num2);
            System.out.println(1111);
            //ArithmeticException: 수학적 논리
            //Exception : 모든 예외의 관련된 정보를 받는 부모 클래스
            //예외가 발생하는 이유에 따라 처리하는 방법도 달라지기 때문에
            //catch문을 사용하여 그에 맞는 예외처리를 해주어야됨.

        }catch (ArithmeticException e){ // 예외의 대한 정보
            //예외 발생 시 실행 할 코드
            System.out.println("모든 수는 0으로 나눌 수 없음.");
        }catch (InputMismatchException e){
            System.out.println("숫자를 입력 받으세요.");
        }finally {
            //예외 발생 유무와 상관 없이 무조건 꼭 실행되야 하는 코드
        }



        System.out.println("프로그램 종료");
        
        
    }
}
