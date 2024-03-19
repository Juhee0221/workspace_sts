package test;

public class MathUtillImpl implements MathUtil{
    @Override
    public boolean isEven(int a, int b, int c){
        int sum = a+b+c;

        if (sum%2 == 0 && sum%5 == 0){
            return true;

        }else {
            return false;
        }

    }

    @Override
    public double getSumFromOne(int num){
        int sum = 0;
        for(int i = 1; i <= num; i++ ){
            sum = sum + i;
        }
        return sum;
    }

    @Override
    public int getArraySum(int[] a) {
        int sum = 0;
        for(int i = 0; i<a.length; i++){
            sum = sum + a[i];
        }
        return sum;
    }
}
