package test;

public class Test3 {
    public static void main(String[] args) {
        int [] a = {5,11,20,40,30};

        int max = a[0];
        int min = a[0];
        for(int i = 0; i < a.length; i++){
            if(a[i] > max){
                max = a[i];
            }
            else if (a[i] <= min) {
                min = a[i];
            }
        }
        System.out.println("큰 값 : " + max + " 작은 값 : " + min);
    }
}
