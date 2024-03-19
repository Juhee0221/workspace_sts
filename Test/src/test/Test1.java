package test;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("수를 입력하세요: ");
        int a = scanner.nextInt();
        System.out.println("수를 입력하세요: ");
        int b = scanner.nextInt();
        System.out.println("수를 입력하세요: ");
        int c = scanner.nextInt();

        int max = 0;
        int mid = 0;
        int min = 0;

        if(a > b && b > c){
            max = a;
            mid = b;
            min = c;
        }
        else if(a > c && c > b){
            max = a;
            mid = b;
            min = c;
        }
        else if (b > a && a > c) {
            max = b;
            mid = a;
            min = c;
        }
        else if(b > c && c > a ){
            max = b;
            mid = c;
            min = a;
        }
        else if(c > a && a > b){
            max = c;
            mid = a;
            min = b;
        }
        else {
            max = c;
            mid = b;
            min = a;
        }
        System.out.println("max : " + max + " mid : " + mid + " min : " + min);
    }
}
