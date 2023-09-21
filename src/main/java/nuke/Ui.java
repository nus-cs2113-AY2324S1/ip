package nuke;

import nuke.command.exception.InvalidCommandArgumentException;
import nuke.command.exception.InvalidCommandTypeException;
import nuke.storage.exception.TaskFileCopyException;
import nuke.storage.exception.TaskLoadException;
import nuke.storage.exception.TaskSaveException;

import java.util.Scanner;

/**
 * Represents UI of Nuke.
 */
public class Ui {
    private final String IGNORE_ERROR_COMMAND = "ignore";

    private final Scanner USER_IN;

    /**
     * Constructs UI of Nuke.
     */
    public Ui() {
        USER_IN = new Scanner(System.in);
    }

    /**
     * Scans a line from user input.
     *
     * @return user input as a line of string
     */
    public String scanNextLine() {
        return USER_IN.nextLine();
    }

    /**
     * Returns if user inputs to ignore error.
     *
     * @return if the user input equals to the {@code IGNORE_ERROR_COMMAND}.
     */
    public boolean scanIgnore() {
        return scanNextLine().equals(IGNORE_ERROR_COMMAND);
    }

    /**
     * Scans until user input is not empty.
     */
    public void scanWhileEmpty() {
        //noinspection StatementWithEmptyBody
        while (scanNextLine().isEmpty());
    }

    public void printWelcome() {
        System.out.println(LOGO);
        System.out.println();
        System.out.println("[@] Hello! I'm Nuke.");
    }

    public void printGreetingQuestion() {
        System.out.println("[@] What can I do for you?");
        System.out.println();
    }

    public void printBlankLine() {
        System.out.println();
    }

    public void printBye() {
        System.out.println("[@] Bye. Hope to see you again soon!");
    }

    public void printAddedTask(String addedTask, int taskCnt) {
        System.out.println("[@] Got it. I've added this task:");
        System.out.println("  " + addedTask);
        System.out.printf("[@] Now you have %d task%s in the list.\n", taskCnt, taskCnt == 1 ? "": "s");
    }

    public void printListOfTasks(String[] tasks) {
        if(tasks.length == 0) {
            System.out.println("[@] There are no tasks in your list.");
            return;
        }
        System.out.println("[@] Here are the tasks in your list:");
        for (int i = 0; i < tasks.length; i++) {
            System.out.printf("%d.%s\n", i + 1, tasks[i]);
        }
    }

    public void printMarkedTask(String markedTask) {
        System.out.println("[@] Nice! I've marked this task as done:");
        System.out.println("  " + markedTask);
    }

    public void printUnmarkedTask(String unmarkedTask) {
        System.out.println("[@] OK, I've marked this task as not done yet:");
        System.out.println("  " + unmarkedTask);
    }

    public void printDeletedTask(String deletedTask, int taskCnt) {
        System.out.println("[@] Noted. I've removed this task:");
        System.out.println("  " + deletedTask);
        System.out.printf("[@] Now you have %d task%s in the list.\n", taskCnt, taskCnt == 1 ? "": "s");
    }

    public void printCommandError(String description, String detail) {
        System.out.println("[@] Wrong input; " + description);
        System.out.println("[@] " + detail);
    }

    public void printCommandTypeError(InvalidCommandTypeException e) {
        String desc = String.format("There is no command called '%s'.", e.type);
        String detail = "Existing command: bye, list, mark, unmark, todo, deadline, event, delete";
        printCommandError(desc, detail);
    }

    public void printCommandArgumentError(InvalidCommandArgumentException e) {
        String desc = e.reason;
        printCommandError(desc, e.correctUsage);
    }

    public void printTaskLoadError(TaskLoadException e) {
        System.out.println();
        System.out.println("[@] Error occurred while loading the tasks!");
        System.out.println("[@] I backed up your save file.");
        System.out.println("[@] Path: " + e.backupFilePath);
        System.out.println();
    }

    public void handleTaskFileCopyError(TaskFileCopyException e) {
        printTaskFileCopyError(e, IGNORE_ERROR_COMMAND);
        if (!scanIgnore()) {
            throw new RuntimeException(e);
        }
    }

    public void printTaskFileCopyError(TaskFileCopyException e, String ignoreCommand) {
        System.out.println();
        System.out.println("[@] Error occurred while loading the tasks!");
        System.out.println("[@] I tried to back up your save file, but it failed as well.");
        System.out.println("[@] You can back up your save file manually.");
        System.out.println("[@] Path: " + e.filePath);
        System.out.println();
        System.out.printf("[@] Ignore and continue to run by entering '%s'.", ignoreCommand);
        System.out.println("[@] Otherwise I will quit.");
        System.out.println();
    }

    public void handleTaskSaveError(TaskSaveException e) {
        printTaskSaveError(e.tasks);
        scanWhileEmpty();
    }

    public void printTaskSaveError(String[] tasks) {
        System.out.println("[@] Error occurred while saving the tasks!");
        System.out.println("[@] I will show all the tasks you have added.");
        printListOfTasks(tasks);
        System.out.println();
        System.out.println("[@] Please back up your save file manually.");
        System.out.println("[@] Continue to quit by entering non-empty input.");
        System.out.println();
    }

    public static final String LOGO =
            "     _.-^^---....,,--      \n" +
            " _--                  --_  \n" +
            "<                        >)\n" +
            "|                         |\n" +
            " \\._                   _./ \n" +
            "    ```--. . , ; .--'''    \n" +
            "          | |   |          \n" +
            "       .-=||  | |=-.       \n" +
            "       `-=#$%&%$#=-'       \n" +
            "          | ;  :|          \n" +
            " _____.,-#%&$@%#&#~,._____ ";
}
