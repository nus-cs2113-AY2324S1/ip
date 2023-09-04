import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Calebra\nWhat can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner input = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        String line = input.nextLine();
        while (!line.equals("bye")) {

            System.out.println("____________________________________________________________");
            if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else if (line.startsWith("mark")) {
                int taskIndex = Integer.parseInt(line.substring(5)) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + tasks[taskIndex].toString());
                }
            } else if (line.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(line.substring(7)) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:\n" + tasks[taskIndex].toString());
                }
            } else {
                tasks[taskCount] = new Task(line);
                taskCount++;
                System.out.print("Added: ");
                System.out.println(line);
            }
            System.out.println("____________________________________________________________\n");
            line = input.nextLine();
        }

        // When the user types "bye," exit the loop and display the goodbye message
        System.out.println("____________________________________________________________");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }
}
