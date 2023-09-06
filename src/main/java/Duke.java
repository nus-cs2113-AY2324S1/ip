import java.util.*;
public class Duke {
    public static String BOT_NAME = "Amy"; // a constant bot name
    public static String[] taskList = new String[100]; // contains tasks
    public static int numberOfTasks = 0; // number of tasks
    public static void add_List() { // Level-2 add,list
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine(); // Read user input
            System.out.println("____________________________________________________________");
            if (userInput.equals("bye")) {
                break; // Exit the loop if the user enters "bye"
            } else if (userInput.equals("list")) {
                for (int i = 0; i < numberOfTasks; i++) {
                    System.out.println((i + 1) + ". " + taskList[i]); // list if the user enters "list'
                }
            } else {
                System.out.println("added: " + userInput); // print add
                taskList[numberOfTasks] = userInput;
                numberOfTasks++;
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
        add_List();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
