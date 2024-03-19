package test;

//고객 정보


public class Client {
    //회원번호
    private int clientNumber;

    //회원명
    private String name;

    //회원이 소유한 계좌정보
    private Account accInfo;

    //생성자
    public Client(int clientNumber, String name, Account accInfo) {
        this.clientNumber = clientNumber;
        this.name = name;
        this.accInfo = accInfo;
    }

    //각 필드의 gettet, setter
    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccInfo() {
        return accInfo;
    }

    public void setAccInfo(Account accInfo) {
        this.accInfo = accInfo;
    }

    //toString
    @Override
    public String toString() {
        return "Client{" +
                "clientNumber=" + clientNumber +
                ", name='" + name + '\'' +
                ", accInfo=" + accInfo +
                '}';
    }
}
