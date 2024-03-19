package test;

import java.util.Scanner;

public class Test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("수를 입력하세요 : ");
        int a = sc.nextInt();
        int b = 0;

        int c = a % 1000 / 100;
        int d = a % 100 / 10;
        int e = a % 10;

        if(c == 3 || c == 6 || c == 9){
            b++;
        }
        if(d == 3 || d == 6 || d == 9){
            b++;
        }
        if(e == 3 || e == 6 || e == 9){
            b++;
        }

        switch (b){
            case 0:
                System.out.println("박수 0번");
                break;
            case 1:
                System.out.println("박수 1번");
                break;
            case 2:
                System.out.println("박수 2번");
                break;
            case 3:
                System.out.println("박수 3번");
        }



    }
}
