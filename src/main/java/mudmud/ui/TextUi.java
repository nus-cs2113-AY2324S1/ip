package mudmud.ui;

import mudmud.task.Task;
import mudmud.tasklist.TaskList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The UI of the application.
 */
public class TextUi {

    private static final String NAME = "MudMud";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HHmm";

    /**
     * Prints out greeting when booting up the app.
     */
    public void tellGreeting() {
        showLine();
        System.out.println("\tOh hello! I'm " + NAME + ".");
        System.out.println("\tHow can I help you today?");
        showLine();
    }

    /**
     * Prints our goodbye message when exiting the app.
     */
    public void tellGoodbye() {
        System.out.println("\tGoodbye! I am going to sleep now.");
    }

    /**
     * Prints out the number of tasks currently in the app.
     *
     * @param tasksCount The number of tasks in the list.
     */
    public void printNumOfTasks(int tasksCount) {
        System.out.println("\tI took a peak at the list and you have " + tasksCount
                + (tasksCount == 1 ? " task" : " tasks") + " currently.");
    }

    /**
     * Prints out the most recent added task.
     *
     * @param tasks The task list.
     */
    public void printRecentTask(TaskList tasks) {
        System.out.println("\tI have added the following task into the list:");
        System.out.println("\t\t" + tasks.getRecentTask());
        printNumOfTasks(tasks.getTasksCount());
    }

    /**
     * Prints out all the task in the list.
     * @param tasks The task list.
     */
    public void printTasks(TaskList tasks) {
        System.out.println("\tHere are your tasks you have inputted:");
        for (int i = 1; i <= tasks.getTasksCount(); i++) {
            System.out.println("\t" + i + "." + tasks.getTask(i - 1));
        }
    }

    /**
     * Prints out a horizontal line.
     */
    public void showLine() {
        System.out.println("\t" + HORIZONTAL_LINE);
    }

    /**
     * Prints out the deleted task.
     *
     * @param removedTask The deleted task.
     */
    public void printDeletedTask(Task removedTask) {
        System.out.println("\tI have surgically removed this task:");
        System.out.println("\t\t" + removedTask);
    }

    /**
     * Prints out the task that is modified.
     *
     * @param task The task that is being modified.
     * @param isMarking Status of whether it is marked or unmarked.
     */
    public void printModifiedTask(Task task, boolean isMarking) {
        if (isMarking) {
            System.out.println("\tYay! You have completed this task:");
        } else {
            System.out.println("\tOh no! It seems that you haven't finish this task:");
        }

        System.out.println("\t\t" + task);
    }

    /**
     * Prints out all tasks from a filtered list.
     *
     * @param tasks The filtered task list.
     */
    public void printFilteredTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("\tSorry, it seems I can not find the tasks with the keyword.");
            return;
        }

        System.out.println("\tHere are the matching tasks in the list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("\t" + i + "." + tasks.get(i - 1));
        }
    }

    /**
     * Sends out an error message that the user trying to access an invalid index.
     *
     * @param tasksCount The number of tasks in the list.
     */
    public void handleIndexOutOfBoundsException(int tasksCount) {
        System.out.println("\tUmm, you tried to access a task that does not exist.");
        System.out.println("\tPerhaps you should put a valid number based on the number of tasks " +
                "you have currently. ");
        printNumOfTasks(tasksCount);
    }

    /**
     * Sends out an error message that the index can not be converted into an integer.
     *
     * @param number The index of the input.
     */
    public void handleNumberFormatException(String number) {
        System.out.println("\tPlease use a valid number for marking or unmarking a task.");
        System.out.println("\tThe number you tried to input is: " + number);
    }

    /**
     * Sends out an error message relating to the file.
     *
     * @param exception IoException
     */
    public void handleIOException(IOException exception) {
        System.out.println("\tSomething went wrong! Please try again!");
        System.out.println("\t" + exception.getMessage());
    }

    /**
     * Sends out an error message that the DateTime format is incorrect in the input.
     */
    public void handleDateTimeException(){
        System.out.println("\tPlease insert your specified date with the format below:");
        System.out.println("\t\t" + DATE_TIME_FORMAT);
    }

    /**
     * Asks for the user to type an input into the application.
     *
     * @return The typed input.
     */
    public String getInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine().trim();
    }
}
