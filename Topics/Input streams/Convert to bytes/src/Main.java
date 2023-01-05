import java.io.InputStream;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        int result = inputStream.read();
        while (result != -1) {
            System.out.print(result);
            result = inputStream.read();
        }
    }
}