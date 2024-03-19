import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test3 {
    public static void main(String[] args) {
        //Main 메소드에는 예외 전가 X
        try{
            m1();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void m1 () throws IOException{
        m2();

    }
    // throws Exception :
    // 여기서 발생하는 예외를 직접 처리하지 않고, 예외를 전가 시키 겠다.
    // 예외 전가 : 다른 메소드에서 메소드 호출 시 예외를 가지고 감.
    public static void m2() throws IOException {

        // 텍스트 파일 가져오기
        Path file = Paths.get("C:\\javastudy\\text.txt");
        BufferedWriter writer = null;

        //오류가 나는 이유 , 예외가 날 수 있으니 예외처리를 해라!
        writer = Files.newBufferedWriter(file);
        writer.write('a');
        writer.write('b');
    }
}
