import java.util.Scanner;

public class Duke {

    public static String divideInput(String input) {
        int dividerPosition = input.indexOf(" ");
        return input.substring(dividerPosition + 1);
    }

    public static void setMarkAsDone(Task[] tasks, String input) {
        int index = Integer.parseInt(divideInput(input)) - 1;
        tasks[index].markAsDone();

        System.out.println("\tYay! You have completed this task:");
        System.out.print("\t\t");
        System.out.println(tasks[index]);
    }

    public static void setUnmarkAsDone(Task[] tasks, String input) {
        int index = Integer.parseInt(divideInput(input)) - 1;
        tasks[index].unmarkAsDone();

        System.out.println("\tOh no! It seems that you haven't finish this task:");
        System.out.print("\t\t");
        System.out.println(tasks[index]);
    }

    public static void printTasks(Task[] tasks, int tasksCount) {
        System.out.println("\tHere are your tasks you have inputted:");
        for (int i = 1; i <= tasksCount; i++) {
            System.out.print("\t" + i + ".");
            System.out.println(tasks[i - 1]);
        }
    }

    public static void main(String[] args) {
        final String NAME = "MudMud";
        final String HORIZONTAL_LINE = "____________________________________________________________";

        Scanner in = new Scanner(System.in);
        String input = "";

        Task[] tasks = new Task[100];
        int tasksCount = 0;

        System.out.println("\t" + HORIZONTAL_LINE);
        System.out.println("\tOh hello! I'm " + NAME + ".");
        System.out.println("\tHow can I help you today?");
        System.out.println("\t" + HORIZONTAL_LINE);

        while (!input.equals("bye")) {
            input = in.nextLine();

            System.out.println("\t" + HORIZONTAL_LINE);

            if (input.equals("bye")) {
                System.out.println("\tGoodbye! I am going to sleep now.");
            } else if (input.equals("list")) {
                printTasks(tasks, tasksCount);
            } else if (input.startsWith("mark")) {
                setMarkAsDone(tasks, input);
            } else if (input.startsWith("unmark")) {
                setUnmarkAsDone(tasks, input);
            } else {
                tasks[tasksCount] = new Task(input);
                tasksCount++;
                System.out.println("\tI have added \""  + input + "\" into the list.");
            }

            System.out.println("\t" + HORIZONTAL_LINE);
        }
    }
}