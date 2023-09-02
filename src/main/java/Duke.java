import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int tasksCount = 0;

    public static String divideInput(String input) {
        int dividerPosition = input.indexOf(" ");
        return input.substring(dividerPosition + 1);
    }

    public static void addTask(String input) {
        tasks[tasksCount] = new Task(input);
        tasksCount++;
    }

    public static void addTodo(String input) {
        tasks[tasksCount] = new Todo(divideInput(input));
        tasksCount++;
    }

    public static void addDeadline(String input) {
        input = divideInput(input);
        int byPosition = input.indexOf("/by");

        tasks[tasksCount] = new Deadline(input.substring(0, byPosition - 1),
                input.substring(byPosition + 4));
        tasksCount++;
    }

    public static void addEvent(String input) {
        input = divideInput(input);
        int fromPosition = input.indexOf("/from");
        int toPosition = input.indexOf("/to");

        tasks[tasksCount] = new Event(input.substring(0, fromPosition - 1),
                input.substring(fromPosition + 6, toPosition - 1),
                input.substring(toPosition + 4));
        tasksCount++;
    }

    public static void setMarkAsDone(String input) {
        int index = Integer.parseInt(divideInput(input)) - 1;
        tasks[index].markAsDone();

        System.out.println("\tYay! You have completed this task:");
        System.out.print("\t\t");
        System.out.println(tasks[index]);
    }

    public static void setUnmarkAsDone(String input) {
        int index = Integer.parseInt(divideInput(input)) - 1;
        tasks[index].unmarkAsDone();

        System.out.println("\tOh no! It seems that you haven't finish this task:");
        System.out.print("\t\t");
        System.out.println(tasks[index]);
    }

    public static void printRecentTask(Task task) {
        System.out.println("\tI have added the following task into the list:");
        System.out.println("\t\t" + task);
        System.out.println("\tI took a peak at the list and you have " + tasksCount
                + (tasksCount == 1 ? " task" : " tasks") + " currently.");
    }

    public static void printTasks() {
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
                printTasks();
            } else if (input.startsWith("mark")) {
                setMarkAsDone(input);
            } else if (input.startsWith("unmark")) {
                setUnmarkAsDone(input);
            } else if (input.startsWith("todo")) {
                addTodo(input);
                printRecentTask(tasks[tasksCount - 1]);
            } else if (input.startsWith("deadline")) {
                addDeadline(input);
                printRecentTask(tasks[tasksCount - 1]);
            } else if (input.startsWith("event")) {
                addEvent(input);
                printRecentTask(tasks[tasksCount - 1]);
            } else {
                addTask(input);
                printRecentTask(tasks[tasksCount - 1]);
            }

            System.out.println("\t" + HORIZONTAL_LINE);
        }
    }
}