import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner fileNameReader = new Scanner(System.in);
        String fileName = fileNameReader.nextLine();
        radixSort alphabetize = new radixSort();
        alphabetize.sort(fileName);

    }
}
