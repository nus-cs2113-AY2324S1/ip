import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________");
        System.out.println("Hello from BotBuddy!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        String command;
        String[] tasks = new String[100];
        int noOfTasks = 0;
        while (true) {
            Scanner in = new Scanner(System.in);
            command = in.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                break;
            }
            if (command.equalsIgnoreCase("list")) {
                if (noOfTasks == 0) {
                    System.out.println("____________________________________________________________");
                    System.out.println("There are currently no tasks!");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                // print out tasks
                System.out.println("____________________________________________________________");
                for (int i = 0; i < noOfTasks; i++) {
                    System.out.println(i+1 + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
                continue;
            }
            tasks[noOfTasks] = command;
            noOfTasks++;
            System.out.println("____________________________________________________________");
            System.out.println("added: " + command);
            System.out.println("____________________________________________________________");

        }

        System.out.println("____________________________________________________________");
        System.out.println("Goodbye, hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
