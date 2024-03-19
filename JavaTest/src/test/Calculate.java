package test;

public class Calculate {
    private int num1;
    private int num2;

    public void setNumber(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
    }

    int getSum(){
        return num1 + num2;
    }

    int getMax(){
        int sum = 0;

        if(num1 > num2){
            sum = num1;
        }
        else{
            sum = num2;
        }
        return sum;
    }
    double getAvg(){
        int num = 0;
        int cnt = 0;
        if(num1 > num2 ){
            for(int i = num2 + 1;  i < num1; i++){
                num = num + i;
                cnt++;
            }
        }else {
            for(int i = num1 + 1; i < num2; i++ ){
                num = num + i;
                cnt++;
            }
        }
        return (double)num/cnt;
    }
}
