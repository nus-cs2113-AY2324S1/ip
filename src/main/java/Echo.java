import java.util.Scanner;

public class Echo {
    public static void printHorizontalLines() {
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }

    public static void Echo() {
        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine().trim();
            if (line.contains("bye")) {
                break;
            }
            printHorizontalLines();
            System.out.println(line);
            printHorizontalLines();
        }

        return;
    }
}
