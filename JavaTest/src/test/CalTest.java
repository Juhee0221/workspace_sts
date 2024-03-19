package test;

public class CalTest {
    public static void main(String[] args) {

        Calculate calculate = new Calculate();

        calculate.setNumber(10 , 100);

        System.out.println("두 수의 합 : " + calculate.getSum());
        System.out.println("두 정수 중 큰 수를 출력 : " + calculate.getMax());
        System.out.println("두 정수 사이의 정수들의 평균을 출력 :" + calculate.getAvg());
    }
}

