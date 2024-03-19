package test;

//해당 클래스 안에 요구사항에 맞는 메소드를 구현하시오.
public interface MathUtil {
    //매개변수로 받은 세 수의 합이 짝수이면서
    //동시에 5의 배수일 경우에만 리턴 true
    boolean isEven(int a, int b, int c);

    //1부터 매개변수로 받은 수까지의 합을 리턴
    double getSumFromOne(int num);

    //아래 요구사항을 만족하는 getArraySum()메소드를 정의하시오.
    //매개변수로 정수형 배열이 들어오면
    //해당 배열의 모든 요소의 합을 리턴
    int getArraySum(int [] a);


}
