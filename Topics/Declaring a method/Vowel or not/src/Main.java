import java.util.Scanner;

public class Main {

    public static boolean isVowel(char ch) {
        return "AEIOUaeiou".indexOf(ch) != -1;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char letter = scanner.nextLine().charAt(0);
        System.out.println(isVowel(letter) ? "YES" : "NO");
    }

    int process(int a, int b){ return 0;}
    void process(int b){ }
//    void process(int a, int b){ }
}