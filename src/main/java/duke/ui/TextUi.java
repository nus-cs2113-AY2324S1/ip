package duke.ui;

import duke.task.Task;
import duke.tasklist.TaskList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextUi {

    private static final String NAME = "MudMud";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    public void tellGreeting() {
        showLine();
        System.out.println("\tOh hello! I'm " + NAME + ".");
        System.out.println("\tHow can I help you today?");
        showLine();
    }

    public void tellGoodbye() {
        System.out.println("\tGoodbye! I am going to sleep now.");
    }

    public void printNumOfTasks(int tasksCount) {
        System.out.println("\tI took a peak at the list and you have " + tasksCount
                + (tasksCount == 1 ? " task" : " tasks") + " currently.");
    }

    public void printRecentTask(TaskList tasks) {
        System.out.println("\tI have added the following task into the list:");
        System.out.println("\t\t" + tasks.getRecentTask());
        printNumOfTasks(tasks.getTasksCount());
    }

    public void printTasks(TaskList tasks) {
        System.out.println("\tHere are your tasks you have inputted:");
        for (int i = 1; i <= tasks.getTasksCount(); i++) {
            System.out.println("\t" + i + "." + tasks.getTask(i - 1));
        }
    }

    public void showLine() {
        System.out.println("\t" + HORIZONTAL_LINE);
    }

    public void printDeletedTask(Task removedTask) {
        System.out.println("\tI have surgically removed this task:");
        System.out.println("\t\t" + removedTask);
    }

    public void printModifiedTask(Task completedTask, boolean isMarking) {
        if (isMarking) {
            System.out.println("\tYay! You have completed this task:");
        } else {
            System.out.println("\tOh no! It seems that you haven't finish this task:");
        }

        System.out.println("\t\t" + completedTask);
    }

    public void handleIndexOutOfBoundsException(int tasksCount) {
        System.out.println("\tUmm, you tried to access a task that does not exist.");
        System.out.println("\tPerhaps you should put a valid number based on the number of tasks " +
                "you have currently. ");
        printNumOfTasks(tasksCount);
    }

    public void handleNumberFormatException(String input) {
        System.out.println("\tPlease use a valid number for marking or unmarking a task.");
        System.out.println("\tThe number you tried to input is: " + input);
    }

    public void handleIOException(IOException exception) {
        System.out.println("Something went wrong! Please try again!");
        System.out.println(exception.getMessage());
    }

    public String getInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine().trim();
    }
}
