package careo;


import java.util.ArrayList;
import java.util.Scanner;


enum MarkOrUnmark {
    MARK,
    UNMARK
}


public class Duke {
    /** ArrayList of all tasks that have been added. */
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        printIntroduction();

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();

            if (input.equals("list")) {
                listTasks();
            } else if (input.startsWith("mark ")) {
                int taskIdx = Integer.parseInt(input.substring(5)) - 1;
                markOrUnmarkTask(MarkOrUnmark.MARK, taskIdx);
            } else if (input.startsWith("unmark ")) {
                int taskIdx = Integer.parseInt(input.substring(7)) - 1;
                markOrUnmarkTask(MarkOrUnmark.UNMARK, taskIdx);
            } else if (input.equals("bye")) {
                break;
            } else {
                addTask(input);
            }
        }

        printFarewell();
    }


    /**
     * Prints a list of all tasks and their status.
     */
    private static void listTasks() {
        int counter = 1;

        printHorizontalLine();
        for (Task task : tasks) {
            System.out.println(
                    "    " + counter + ".[" + task.getStatusIcon() + "] " +
                            task.getDescription());
            counter++;
        }
        printHorizontalLine();
    }


    /**
     * Marks or unmarks a task from tasks and prints a confirmation. Handles invalid indexes and
     * prints a warning in that case.
     *
     * @param markOrUnmark Whether the task should be marked or unmarked.
     * @param taskIdx      Index (zero-based) of the task that should be marked/unmarked in tasks.
     */
    private static void markOrUnmarkTask(MarkOrUnmark markOrUnmark, int taskIdx) {
        if (tasks.isEmpty()) {
            printHorizontalLine();
            System.out.println("    Sorry, there are no tasks yet!");
            System.out.println("    Add a task and then try again.");
            printHorizontalLine();

            return;
        }
        if (taskIdx >= tasks.size() || taskIdx < 0) {
            printHorizontalLine();
            System.out.println("    Sorry, the chosen task index is invalid!");
            System.out.println("    Task index must be between 1 (inclusive) and " + tasks.size() +
                    " (inclusive).");
            printHorizontalLine();

            return;
        }

        Task selectedTask = tasks.get(taskIdx);
        selectedTask.setIsDone(markOrUnmark == MarkOrUnmark.MARK);

        printHorizontalLine();
        if (markOrUnmark == MarkOrUnmark.MARK) {
            System.out.println("    Nice! I've marked this task as done:");
        } else {
            System.out.println("    OK, I've marked this task as not done yet:");
        }
        System.out.println("      [" + selectedTask.getStatusIcon() + "] " +
                selectedTask.getDescription());
        printHorizontalLine();
    }

    /**
     * Adds a new task to tasks and prints a confirmation.
     *
     * @param taskDescription Description of the task that shall be added.
     */
    private static void addTask(String taskDescription) {
        Task newTask = new Task(taskDescription);
        tasks.add(newTask);

        printHorizontalLine();
        System.out.println("    added: " + newTask.getDescription());
        printHorizontalLine();
    }

    /**
     * Prints a polite introduction and offer for help.
     */
    private static void printIntroduction() {
        printHorizontalLine();
        System.out.println("    Hello! I'm Careo");
        System.out.println("    What can I do for you?");
        printHorizontalLine();
    }


    /**
     * Prints a polite goodbye message.
     */
    private static void printFarewell() {
        printHorizontalLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printHorizontalLine();
    }


    /**
     * Prints a line of dashes indented by four spaces.
     */
    private static void printHorizontalLine() {
        System.out.println("    " + "-".repeat(76));
    }
}