import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "       _       _        \n"
                + "      | |     | |       \n"
                + "      | | __ _| | _____ \n"
                + "  _   | |/ _` | |/ / _ \\\n"
                + " | |__| | (_| |   <  __/\n"
                + "  \\____/ \\__,_|_|\\_\\___|\n";
        // Bot Greet
        System.out.println(logo);
        System.out.println("Hello! I'm Jake");
        System.out.println("What can I do for you?");
        // Bot Echo
        Scanner readIn = new Scanner(System.in);
        String userInput = "";
        while (true) {
            userInput = readIn.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println(userInput);
        }
        // Bot Exit 
        System.out.println("Bye. Hope to see you again soon!");
        readIn.close();
    }
}
