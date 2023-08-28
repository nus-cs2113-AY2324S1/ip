import java.util.Scanner;

public class Duke {

    public static void setMarkAsDone(Task[] tasks, String input) {
        int dividerPosition = input.indexOf(" ");
        int index = Integer.parseInt(input.substring(dividerPosition + 1));
        tasks[index - 1].markAsDone();

        System.out.println("\tYay! You have completed this task:");
        System.out.println("\t\t[" + tasks[index - 1].getStatusIcon() + "] "
                + tasks[index - 1].getDescription());
    }

    public static void printTasks(Task[] tasks, int tasksCount) {
        for (int i = 1; i <= tasksCount; i++) {
            System.out.println("\t" + i + ".[" + tasks[i - 1].getStatusIcon() + "] "
                    + tasks[i - 1].getDescription());
        }
    }

    public static void main(String[] args) {
        final String NAME = "MudMud";

        Scanner in = new Scanner(System.in);
        String input = "";

        Task[] tasks = new Task[100];
        int tasksCount = 0;

        System.out.println("\t____________________________________________________________");
        System.out.println("\tOh hello! I'm " + NAME + ".");
        System.out.println("\tPlease input a task to put into a list.");
        System.out.println("\tTo see the contents of the list, input \"list\".");
        System.out.println("\tTo exit the program, input \"bye\".");
        System.out.println("\t____________________________________________________________");

        while (!input.equals("bye")) {
            input = in.nextLine();

            System.out.println("\t____________________________________________________________");
            if (input.equals("bye")) {
                System.out.println("\tGoodbye! I am going to sleep now.");
            } else if (input.equals("list")) {
                printTasks(tasks, tasksCount);
            } else if (input.startsWith("mark")) {
                setMarkAsDone(tasks, input);
            } else {
                tasks[tasksCount] = new Task(input);
                tasksCount++;
                System.out.println("\tI have added \""  + input + "\" into the list.");
            }
            System.out.println("\t____________________________________________________________");
        }
    }
}