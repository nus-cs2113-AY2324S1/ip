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

        while (true) {
            Scanner in = new Scanner(System.in);
            command = in.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println("____________________________________________________________");
            System.out.println(command);
            System.out.println("____________________________________________________________");

        }

        System.out.println("____________________________________________________________");
        System.out.println("Goodbye, hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
