package careo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    /** ArrayList of all tasks that have been added. */
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printIntroduction();

        Scanner scanner = new Scanner(System.in);
        String input;

        boolean shouldTerminate = false;

        while (!shouldTerminate) {
            input = scanner.nextLine();

            try {
                shouldTerminate = processInput(input);
            } catch (Exception e) {
                printHorizontalLine();
                System.out.println("    Invalid input!");
                System.out.println(
                        "    Please make sure that your input is in the proper format and try again");
                printHorizontalLine();
            }

        }

        printFarewell();
    }


    /**
     * Processes a line of text inputted by the user.
     *
     * @param input The input from the user.
     * @return Whether the process-input-loop should be exited.
     */
    private static boolean processInput(String input) {
        ArrayList<String> keywords = new ArrayList<>(
                Arrays.asList("mark", "unmark", "deadline", "event", "todo")
                );

        if (input.equals("bye")) {
            return true;
        } else if (keywords.contains(input)) {
            printHorizontalLine();
            System.out.println("    ☹ OOPS!!! The description of a " + input + " cannot be empty.");
            printHorizontalLine();
        } else if (input.equals("list")) {
            listTasks();
        } else if (input.startsWith("mark ")) {
            int taskIdx = Integer.parseInt(input.substring(5)) - 1;
            markOrUnmarkTask(MarkOrUnmark.MARK, taskIdx);
        } else if (input.startsWith("unmark ")) {
            int taskIdx = Integer.parseInt(input.substring(7)) - 1;
            markOrUnmarkTask(MarkOrUnmark.UNMARK, taskIdx);
        } else if (input.startsWith("deadline ")) {
            String relevantInput = input.substring(9);

            String[] parts = relevantInput.split("/");
            String taskDescription = parts[0].strip();
            String by = parts[1].substring(2).strip();

            addTask(new Deadline(taskDescription, by));
        } else if (input.startsWith("event ")) {
            String relevantInput = input.substring(6);

            String[] parts = relevantInput.split("/");
            String taskDescription = parts[0].strip();
            String from = parts[1].substring(4).strip();
            String to = parts[2].substring(2).strip();

            addTask(new Event(taskDescription, from, to));
        } else if (input.startsWith("todo ")) {
            String relevantInput = input.substring(5);

            String taskDescription = relevantInput.strip();

            addTask(new ToDo(taskDescription));
        } else if (input.startsWith("delete ")) {
            int taskIdx = Integer.parseInt(input.substring(7)) - 1;
            Task removedTask = tasks.remove(taskIdx);

            printHorizontalLine();
            System.out.println("    Noted. I've removed this task:");
            System.out.println("      " + removedTask.toString());
            if (tasks.size() == 1) {
                System.out.println("    Now you have 1 task in the list.");
            } else {
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
            }
            printHorizontalLine();
        } else {
            printHorizontalLine();
            System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            printHorizontalLine();
        }

        return false;
    }


    /**
     * Prints a list of all tasks and their status.
     */
    private static void listTasks() {
        int counter = 1;

        printHorizontalLine();
        for (Task task : tasks) {
            System.out.println("    " + counter + "." + task);
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
        System.out.println("      " + selectedTask);
        printHorizontalLine();
    }

    /**
     * Adds a new task to tasks and prints a confirmation.
     *
     * @param newTask The task that shall be added.
     */
    private static void addTask(Task newTask) {
        tasks.add(newTask);

        printHorizontalLine();
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + newTask.toString());
        if (tasks.size() == 1) {
            System.out.println("    Now you have 1 task in the list.");
        } else {
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        }

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