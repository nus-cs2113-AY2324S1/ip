import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Herbert herbert = new Herbert();

        Scanner scan = new Scanner(System.in);

        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            System.out.println();

            // User has inputted "bye"
            if (herbert.processLine(line) == 1) {
                break;
            }
        }
    }

}
