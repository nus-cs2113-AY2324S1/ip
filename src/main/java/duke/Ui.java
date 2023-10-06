package duke;
import java.util.Scanner;
import java.util.ArrayList;


public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String LINE = "__________________________________________\n";
        System.out.println(LINE
                + "Hello I'm MatinBot\n"
                + "What can I do for you?\n"
                + LINE);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public void showLine() {
        System.out.println("__________________________________________");
    }

    // Read a command from the user
    public String readCommand() {
        return scanner.nextLine();
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    // Display a list of tasks
    public void showTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("☹ OOPS!!! The list is empty");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String taskInfo = (i + 1) + ". " + task.toString();
                System.out.println(taskInfo);
            }
        }
    }

    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("☹ OOPS!!! No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                Task task = matchingTasks.get(i);
                System.out.println((i + 1) + ". " + task);
            }
        }
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have "
                + taskCount
                + " tasks in the list.");
    }

    public void showTaskMarkedDone(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    public void showTaskMarkedUndone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task.toString());
    }

    public void showTaskDeleted(Task task, int taskCount) {
        if (task != null) {
            System.out.println("Noted. I've removed this task:\n" + task.toString());
        }
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
}

