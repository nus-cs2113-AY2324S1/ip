import java.util.Scanner;

public class Echo {

    public static void Echo() {
        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine().trim();
            if (line.contains("bye")) {
                break;
            }
            Duke.printHorizontalLines();
            System.out.println(line);
            Duke.printHorizontalLines();
        }

        return;
    }
}
