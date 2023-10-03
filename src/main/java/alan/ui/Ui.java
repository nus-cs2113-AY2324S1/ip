package alan.ui;

import alan.data.exception.AlanException;
import alan.data.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static alan.common.Messages.MESSAGE_DELETE_TASK;
import static alan.common.Messages.MESSAGE_GOODBYE;
import static alan.common.Messages.MESSAGE_GREET;
import static alan.common.Messages.MESSAGE_MARK_TASK;
import static alan.common.Messages.MESSAGE_UNMARK_TASK;
import static alan.common.Messages.MESSAGE_LIST_COMMAND;

/**
 * Represents the text UI of the application
 */
public class Ui {
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    private static final String horizontalDivider = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Reads the user input
     *
     * @return string of the user's input text
     */
    public String getUserCommand() {
        System.out.print("Input: ");

        String userInput = in.nextLine();

        showToUser(horizontalDivider);

        return userInput;
    }

    public void printHorizontalLine() {
        showToUser(horizontalDivider);
    }

    public void showWelcomeMessage() {
        showToUser(
                horizontalDivider,
                MESSAGE_GREET,
                horizontalDivider);
    }

    public void showExitMessage() {
        showToUser(MESSAGE_GOODBYE);
    }

    /**
     * Prints all the <code>Task</code> objects in a list format
     *
     * @param taskList tasks in the list
     */
    public void printTasks(ArrayList<Task> taskList) {
        showToUser(MESSAGE_LIST_COMMAND);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print((i + DISPLAYED_INDEX_OFFSET) + ". ");
            System.out.println(taskList.get(i));
        }
    }

    public void showMarkTaskMessage(Task task) {
        showToUser(MESSAGE_MARK_TASK);
        System.out.println(task);
    }

    public void showUnmarkTaskMessage(Task task) {
        showToUser(MESSAGE_UNMARK_TASK);
        System.out.println(task);
    }

    public void showDeleteTaskMessage(Task task) {
        showToUser(MESSAGE_DELETE_TASK);
        System.out.println(task);
    }

    public void showNumberOfTasksMessage(int numberOfTasks) {
        if (numberOfTasks == 1) {
            System.out.println("Dude! You've got a solid " + numberOfTasks + " task lined up on your list now!");
        } else {
            System.out.println("Dude! You've got a solid " + numberOfTasks + " tasks lined up on your list now!");
        }
    }

    /**
     * Prints the last <code>Task</code> object added to the taskList
     *
     * @param taskList tasks in the list
     */
    public void showTaskAddedMessage(ArrayList<Task> taskList) {
        int numberOfTasks = taskList.size();
        int lastTaskIndex = taskList.size() - 1;

        System.out.println("added: " + taskList.get(lastTaskIndex));
        showNumberOfTasksMessage(numberOfTasks);
    }

    public void showFolderNotFoundMessage(String userWorkingDirectory) {
        System.out.println("Data Folder was not found!\nIt's ok... new data folder has been created in " + userWorkingDirectory);
    }

    public void showFileNotFoundMessage(java.nio.file.Path dataFolderPath) {
        System.out.println("tasks.txt was not found!\nIt's ok... new tasks.txt has been created in " + dataFolderPath);
    }

    public void showLoadingError() {
        System.out.println("Yo dude something ain't loading right :/");
    }

    public void showSavingError() {
        System.out.println("Sorry man I can't seem to save to the text file D:");
    }

    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }
}
