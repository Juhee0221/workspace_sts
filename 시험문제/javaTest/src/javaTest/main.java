package test;

public class main {
    public static void main(String[] args) {
        MathUtillImpl mathUtill = new MathUtillImpl();

        System.out.println(mathUtill.isEven(10,10,10));
        System.out.println(mathUtill.getArraySum(new int[]{1,2,3,4}));
        System.out.println(mathUtill.getSumFromOne(2));


    }
}
