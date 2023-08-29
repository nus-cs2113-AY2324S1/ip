import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "  ____                  \n"
                + " |  _ \\  ___  _ __ ___  \n"
                + " | | | |/ _ \\| '_ ` _ \\ \n"
                + " | |_| | (_) | | | | | |\n"
                + " |____/ \\___/|_| |_| |_|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Dom!");
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine();
            System.out.println("____________________________________________________________");
            System.out.println(" " + command);
            System.out.println("____________________________________________________________");
            if (command.equals("bye")) {
                System.out.println(("Bye. Hope to see you again soon"));
                break;
            }
        }
    }
}

