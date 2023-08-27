import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        printIntroduction();

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();

            if (input.equals("list")) {
                listTasks();
            } else if (input.startsWith("mark")) {
                int taskIdx = Integer.parseInt(input.substring(5)) - 1;
                markTask(taskIdx);
            } else if (input.startsWith("unmark")) {
                int taskIdx = Integer.parseInt(input.substring(7)) - 1;
                unmarkTask(taskIdx);
            } else if (input.equals("bye")) {
                break;
            } else {
                addTask(input);
            }
        }

        printFarewell();
    }


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

    private static void markTask(int taskIdx) {
        if (taskIdx >= tasks.size() || taskIdx < 0) {
            printHorizontalLine();
            System.out.println("    Sorry, the chosen task index is invalid!");
            System.out.println("    Task index must be between 1 (inclusive) and " + tasks.size() +
                    " (inclusive).");
            printHorizontalLine();

            return;
        }

        Task selectedTask = tasks.get(taskIdx);
        selectedTask.setIsDone(true);

        printHorizontalLine();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      [" + selectedTask.getStatusIcon() + "] " +
                selectedTask.getDescription());
        printHorizontalLine();
    }

    private static void unmarkTask(int taskIdx) {
        if (taskIdx >= tasks.size() || taskIdx < 0) {
            printHorizontalLine();
            System.out.println("    Sorry, the chosen task index is invalid!");
            System.out.println("    Task index must be between 1 (inclusive) and " + tasks.size() +
                    " (inclusive).");
            printHorizontalLine();

            return;
        }

        Task selectedTask = tasks.get(taskIdx);
        selectedTask.setIsDone(false);

        printHorizontalLine();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      [" + selectedTask.getStatusIcon() + "] " +
                selectedTask.getDescription());
        printHorizontalLine();
    }

    private static void addTask(String taskDescription) {
        Task newTask = new Task(taskDescription);
        tasks.add(newTask);

        printHorizontalLine();
        System.out.println("    added: " + newTask.getDescription());
        printHorizontalLine();
    }

    private static void printIntroduction() {
        printHorizontalLine();
        System.out.println("    Hello! I'm Careo");
        System.out.println("    What can I do for you?");
        printHorizontalLine();
    }


    private static void printFarewell() {
        printHorizontalLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        System.out.println("    " + "-".repeat(76));
    }
}