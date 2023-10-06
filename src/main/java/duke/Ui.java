package duke;
import java.util.Scanner;
import java.util.ArrayList;


public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    // Display a welcome message
    public void showWelcome() {
        String LINE = "__________________________________________\n";
        System.out.println(LINE
                + "Hello I'm MatinBot\n"
                + "What can I do for you?\n"
                + LINE);
    }

    // Display an error message
    public void showError(String message) {
        System.out.println(message);
    }

    // Display a loading error message
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    // Display a line divider
    public void showLine() {
        System.out.println("__________________________________________");
    }

    // Read a command from the user
    public String readCommand() {
        return scanner.nextLine();
    }

    // Display a farewell message
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

//                if (task instanceof Deadline) {
//                    taskInfo += " (by: " + ((Deadline) task).getBy() + ")";
//                } else if (task instanceof Event) {
//                    taskInfo += " (from: " + ((Event) task).getFrom() + " to: " + ((Event) task).getTo() + ")";
//                }

                System.out.println(taskInfo);
            }
        }
    }


    // Display a task added message
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have "
                + taskCount
                + " tasks in the list.");
    }

    // Display a task marked as done message
    public void showTaskMarkedDone(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    // Display a task marked as undone message
    public void showTaskMarkedUndone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task.toString());
    }

    // Display a task deleted message
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:\n" + task.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
}

