import java.util.Scanner;

public class Chatty {
    /*public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    }
     */

    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Chatty!\nWhat can I do for you?");
        System.out.println("____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Task[] tasks = new Task[100];
        int taskCount = 0;
        while (!input.equalsIgnoreCase("bye")){
            if (input.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
                System.out.println("____________________________________________________________\n");
                input = scanner.nextLine();
                continue;
            } else if (input.startsWith("mark")) {
                int idx=Integer.parseInt(input.substring(5)) - 1;
                tasks[idx].mark();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + tasks[idx].getDescription());
                System.out.println("____________________________________________________________\n");
                input = scanner.nextLine();
                continue;
            } else if (input.startsWith("unmark")) {
                int idx=Integer.parseInt(input.substring(7)) - 1;
                tasks[idx].unmark();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] " + tasks[idx].getDescription());
                System.out.println("____________________________________________________________\n");
                input = scanner.nextLine();
                continue;
            }
            System.out.println("____________________________________________________________");
            tasks[taskCount] = new Task(input);
            taskCount++;
            System.out.println("added: "+input);
            System.out.println("____________________________________________________________\n");
            input = scanner.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
