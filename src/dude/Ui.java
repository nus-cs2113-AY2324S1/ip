package dude;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void drawLine() {
        for (int i = 0; i < 30; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public void showGreeting() {
        String logo = "###            #        \n"
                + "#  #           #        \n"
                + "#  #  #  #   ###   ##   \n"
                + "#  #  #  #  #  #  # ##  \n"
                + "#  #  #  #  #  #  ##    \n"
                + "###    ###   ###   ##   \n";

        System.out.println("Hello from\n" + logo);
        drawLine();
        System.out.println("Hello! I'm your best dude:)");
        System.out.println("What can I do for you?");
        drawLine();
    }

    public void showFarewell() {
        drawLine();
        System.out.println("Bye. Hope to see you again soon!");
        drawLine();
    }

    public void showMessage(String message) {
        drawLine();
        System.out.println(message);
        drawLine();
    }

    public void showError(String message) {
        drawLine();
        System.out.println("☹ OOPS!!! " + message);
        drawLine();
    }

    public void showTasks(ArrayList<Task> tasks) {
        drawLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        drawLine();
    }

    public void showAddedTask(Task task, int taskCount) {
        drawLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + (taskCount == 1 ? " task" : " tasks") + " in the list.");
        drawLine();
    }

    public void showRemovedTask(Task task, int taskCount) {
        drawLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + (taskCount == 1 ? " task" : " tasks") + " in the list.");
        drawLine();
    }

    public void showMarkedTask(Task task, boolean isDone) {
        drawLine();
        String message = isDone ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:";
        System.out.println(message);
        System.out.println("   " + task);
        drawLine();
    }

    public void showFoundTasks(ArrayList<Task> matchingTasks) {
        drawLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + ". " + matchingTasks.get(i));
        }
        drawLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
