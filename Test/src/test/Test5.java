package test;

import javax.xml.transform.Source;
import java.util.Scanner;

public class Test5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = new int[3];
        int[] base = new int[3];
        int cnt = 0;
        int num = 0;
        int snt = 0;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 9 + 1);
            for (int j = 0; j < i; j++) {
                if (arr[i] == arr[j]) {
                    i--;
                    break;
                }
            }
        }
        System.out.print("만들어진 숫자 : ");
        for (int b : arr) {
            System.out.print(b + " ");
        }

        System.out.println("숫자를 정했습니다. 게임을 시작합니다.");

        while (true) {
            for (int i = 0; i < base.length; i++) {
                System.out.println(" 숫자입력 >> ");

                base[i] = sc.nextInt();
                break;
            }

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < base.length; j++) {
                    if (arr[i] == base[j] && i == j) {
                        //스트라이크일때?
                        cnt++;
                    }
                    else if (arr[i] == base[j] && i != j) {
                        //볼일때?
                        num++;
                    }
                }
                snt++;
            }
            System.out.println(cnt + "스트라이크" + num + "볼");
            if (cnt == 3) {
                System.out.println( snt + "만에 정답을 맞췄습니다.");
                break;
            }

        }

    }

}
