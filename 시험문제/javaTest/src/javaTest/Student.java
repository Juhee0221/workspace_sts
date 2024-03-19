package test;

public class Student {
    private String sutName;
    private int korScore;
    private int engScore;
    private int allScore;

    public Student(String sutName, int korScore, int engScore){
        this.sutName = sutName;
        this.korScore = korScore;
        this.engScore = engScore;
    }

    public String getSutName() {
        return sutName;
    }

    public void setSutName(String sutName) {
        this.sutName = sutName;
    }

    public int getKorScore() {
        return korScore;
    }

    public void setKorScore(int korScore) {
        this.korScore = korScore;
    }

    public int getEngScore() {
        return engScore;
    }

    public void setEngScore(int engScore) {
        this.engScore = engScore;
    }

    public int getAllScore() {
        return korScore + engScore;
    }

    public void setAllScore(int allScore) {
        this.allScore = allScore;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sutName='" + sutName + '\'' +
                ", korScore='" + korScore + '\'' +
                ", engScore='" + engScore + '\'' +
                ", allScore='" + getAllScore() + '\'' +
                '}';
    }
}

