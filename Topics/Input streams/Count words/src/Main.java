import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // start coding here
        String stringResult = reader.readLine().trim();
        System.out.println(stringResult.isBlank() ? 0 : stringResult.split(" +").length);
        reader.close();
    }
}