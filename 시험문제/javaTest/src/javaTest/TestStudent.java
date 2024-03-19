package test;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

public class TestStudent {
    public static void main(String[] args) {

        List<Student> stu= new ArrayList<>();

        Student stu1 = new Student("김자바", 80 ,70);
        Student stu2 = new Student("김나비", 20 ,90);
        Student stu3 = new Student("홍길동", 50 ,70);

        stu.add(stu1);
        stu.add(stu2);
        stu.add(stu3);

        System.out.println("모든 학생정보 출력 : ");
        for (int i = 0; i < stu.size(); i++){
            System.out.println(stu.get(i));
        }

        //총점 150점 이상인 학생의 모든 정보를 출력
        System.out.println("총점 150점 이상인 학생:");
        for(int i = 0; i < stu.size(); i++ ){
            if(stu.get(i).getAllScore() >= 150){
                System.out.println(stu.get(i));
            }
        }
        //모든 학생에 대한 평균점수를 출력
        System.out.println("평균 점수 : ");

        int sum = 0;
        int cnt = 0;
        double result = 0;

        for (int i =0; i<stu.size(); i++){
            sum = sum + stu.get(i).getAllScore();
            cnt++;
        }
        result = sum/(double)cnt;
        System.out.println(result);

        //총점이 1등인 학생의 모든정보를 출력
        System.out.println("총점이 1등인 학생: ");
        int first = 0;
        int a = 0;
        for(int i =0; i < stu.size(); i++){
            if(stu.get(i).getAllScore() > first) {
                first = stu.get(i).getAllScore();
                a = i;
            }
        }
        System.out.println(stu.get(a));
    }
}
