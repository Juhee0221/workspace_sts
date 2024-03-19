package test;

public class Test2 {
    public static void main(String[] args) {
        int [] a = new int[6];

        System.out.println("랜덤숫자 :");

        for(int i = 0; i < a.length; i ++){
            a[i] = (int)(Math.random() * 46 + 1);
            System.out.print(a[i] + ",");
        }
    }
}
