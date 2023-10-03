package chattie;

import chattie.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with user interface like printing
 */
public class Ui {

    /**
     * Prints divider line
     */
    public static void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Greets the user to provide a friendly experience
     */
    public static void greetUser() {
        String line;
        Scanner in = new Scanner(System.in);

        printLine();
        System.out.println("\tHello! I'm Chattie! How was your day?");
        printLine();

        line = in.nextLine().toLowerCase();

        printLine();
        if(line.contains("good")) {
            System.out.println("\tGreat to hear that! :D");
        } else if (line.contains("bad")) {
            System.out.println("\tI'm sorry to hear that :(");
        } else {
            System.out.println("\tSounds like you had quite a day");
        }
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

    /**
     * Reads the user command and returns it as a string
     *
     * @return User command
     */
    public static String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Asks user to try again after showing specific ChattieException message
     */
    public void showError() {
        System.out.println("\tPlease try again");
    }

    /**
     * Prints error message for non-ChattieException errors
     */
    public void showError(String message) {
        System.out.println("\tOh no! " + message);
        showError();
    }

    /**
     * Prints after task has been unmarked
     *
     * @param task List of tasks
     * @param taskNum Position of unmarked task in the task list
     */
    public static void printUnmarkMessage(TaskList task, int taskNum) {
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t" + task.getTask(taskNum));
    }

    /**
     * Prints after task has been added
     *
     * @param task Task to be added
     * @param taskList List of tasks to be updated
     */
    public static void printAddMessage(TaskList taskList, Task task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + taskList.getSize() + " tasks in the list.");
    }

    /**
     * Prints after task has been deleted
     *
     * @param task Task to be deleted
     * @param taskList List of tasks to be updated
     */
    public void printDeleteMessage(TaskList taskList, Task task) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + task);
        System.out.println("\tNow you have " + taskList.getSize() + " tasks in the list.");
    }

    /**
     * Prints after task has been marked
     *
     * @param task List of tasks
     * @param taskNum Position of marked task in the task list
     */
    public void printMarkMessage(TaskList task, int taskNum) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + task.getTask(taskNum));
    }

    /**
     * Prints the entire task list
     *
     * @param list List of tasks
     */
    public void printTaskList(ArrayList<Task> list) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print("\t" + (i+1) + ". ");
            System.out.println(list.get(i));
        }
    }

    /**
     * Prints filtered list based on find command
     *
     * @param list Filtered list of tasks
     */
    public void printFilteredList(ArrayList<Task> list) {
        System.out.println("\tHere are the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print("\t" + (i+1) + ". ");
            System.out.println(list.get(i));
        }
    }

    /**
     * Prints exit message
     */
    public void printExitMessage() {
        System.out.println("\tByeeeee. Hope to see you again soon! :)");
    }

    /**
     * Prints user command
     *
     * @param command User command
     */
    public void printEchoMessage(String command) {
        System.out.println("\t" + command);
    }
}
