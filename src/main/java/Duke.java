import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String logoRyan = " ____  _          \n"
                + "|  _ \\| | ___   __\n"
                + "| |_) | |/ _ \\ / _|\n"
                + "|  __/| | (_) | (__\n"
                + "|_|   |_|\\___/ \\___|\n";

        System.out.println("Hello from\n" + logoRyan);

        // String[] storageArray() = new String[100];

        String line = "Hello! I'm Ryan Loh \nWhat can I do for you?\n";

        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();
        while (!userInput.equals("Bye")) {
            System.out.println("      " + userInput);
            userInput = scanner.nextLine(); 
        }
        String lineBreak = "------------------------------ \n";

        System.out.println(line + lineBreak + "Bye. Hope to see you again soon!\n");

        // Close the scanner when you're done with it
        scanner.close();

    }
}
