import Tasks.*;
import java.util.ArrayList;

public class Ui {
    public void showWelcomeMessage() {
        System.out.println("Hey! I'm Dukey, your virtual assistant!\nWhat can I do for you?\n");
    }

    public static void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void outputHeader() {
        printLine();
        System.out.println("Here are the matching tasks in your list:\n");
    }

    public static void printLine() {
        System.out.println("_____________________________________________________");
    }

    public static void showLoadingError() {
        System.out.println("Error loading tasks from text file to Dukey");
    }
    public void showTaskList(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            System.out.println((index++) + "." + task);
        }
        printLine();
    }

}

