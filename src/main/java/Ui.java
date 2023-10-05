import Tasks.*;
import java.util.ArrayList;

public class Ui {
    public static void showWelcomeMessage() {
        System.out.println("Hey! I'm Dukey, your virtual assistant!\nWhat can I do for you?\n");
    }

    public static void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }



    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println("_____________________________________________________");
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            System.out.println((index++) + "." + task);
        }
        System.out.println("_____________________________________________________");
    }

    // Add other UI-related methods here
}

