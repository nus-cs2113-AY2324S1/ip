import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        int length = 10;

        // Print a horizontal line of hyphens
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println("Hi! I'm Joshua");
        System.out.println("What can I do for you?");
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("bye")) {
                System.out.println("Bye! See you soon.");
                break; // Exit the loop and terminate the program
            }
        }
        scanner.close();

        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
