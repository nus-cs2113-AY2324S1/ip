import java.util.*;
public class Duke {
    public static String BOT_NAME = "Amy"; // a constant bot name
    public static void echo() { // Level-1 Echo
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine(); // Read user input
            System.out.println("____________________________________________________________");
            System.out.println(userInput); // Echo the user's input
            if (userInput.equals("bye")) {
                break; // Exit the loop if the user enters "bye"
            }
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + BOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        echo();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }
}
