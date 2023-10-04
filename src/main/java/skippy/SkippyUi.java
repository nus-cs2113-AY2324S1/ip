package skippy;

import java.util.List;
import java.util.Scanner;

import tasklist.TaskList;
import tasks.Task;


/**
 *
 * This class fully manages printing Ui output.
 */
public class SkippyUi {

    private static final String INDENT = "    ";

    private final String LINE = "____________________________________________________________";

    private final String SKIPPY = " _____________  __.____________________________.___.\n"
            + "/   _____/    |/ _|   |______   |______   |__  |   |\n"
            + "\\_____  \\|      < |   ||     ___/|     ___/|   |   |\n"
            + "/        \\    |  \\|   ||    |    |    |    |____   |\n"
            + "_______  /____|__ |___||____|    |____|    |_______|\n";

    private Scanner scanner;

    public SkippyUi(boolean withScanner) {
        if (withScanner) {
            this.scanner = new Scanner(System.in);
        }
    }

    /**
     * Returns the next line from user input in the terminal.
     *
     * @return line entered in terminal.
     */
    public String getNextLine() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner to prevent errors.
     */

    public void closeScanner() {
        this.scanner.close();
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public void printWelcomeMessage() {
        System.out.println(SKIPPY);
        System.out.println("Hi! I am Skippy the BunBun!");
        System.out.println("What can I do for you?");
        printLine();
    }

    public void printExitMessage() {
        printLine();
        System.out.println("Saving tasks...");
        System.out.println("Goodbye! Hope to see you again soon!");
        printLine();
    }

    public void printHelp() {
        printLine();
        System.out.println("Hello, I am Skippy the bunny and I'm your personal assistant.\n"
                + INDENT + "1. list: Lists all tasks\n"
                + INDENT + "2. todo <name>: Adds a ToDo task.\n"
                + INDENT + "3. deadline <name> /by <by when>: Adds a deadline task"
                + " with specified deadline.\n"
                + INDENT + "4. event <name> /from <from when> /to <to when>:"
                + " Adds an event with specified time period.\n"
                + INDENT + "5. mark <task number>: Marks the specified task as done.\n"
                + INDENT + "6. unmark <task number>: Marks the specified task as not done.\n"
                + INDENT + "7. delete <task number>: Deletes the specified task from the list.\n"
                + INDENT + "8. find <keyword>: Finds and lists the tasks that contains the keyword"
                + " in its name.\n"
                + INDENT + "9. bye: Exits the program.");
        printLine();
    }

    public void printInvalidInputMessage() {
        printLine();
        System.out.println("☹ OOPS!!! I'm sorry, " +
                "but I don't know what that means :-(");
        printLine();
    }

    public void printTaskList(TaskList taskList) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        List<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        printLine();
    }

    public void printAddedTask(Task task, TaskList taskList) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task.toString());
        System.out.println("Now you have "
                + taskList.getTasks().size() + " tasks in the list.");
        printLine();
    }

    public void printRemovedTask(Task task, TaskList taskList) {
        printLine();
        System.out.println("Got it. I've removed this task:");
        System.out.println(" " + task.toString());
        System.out.println("Now you have "
                + taskList.getTasks().size() + " tasks in the list.");
        printLine();
    }

    public void printMarkedTask(int taskIndex, TaskList taskList) {
        printLine();
        List<Task> tasks = taskList.getTasks();
        if (taskIndex >= 1 && taskIndex <= tasks.size()) {
            Task task = tasks.get(taskIndex - 1);
            System.out.println("Ok! I've marked this task as done:");
            System.out.println(" " + task.toString());
        } else {
            System.out.println("Invalid task index.");
        }
        printLine();
    }

    public void printUnmarkedTask(int taskIndex, TaskList taskList) {
        printLine();
        List<Task> tasks = taskList.getTasks();
        if (taskIndex >= 1 && taskIndex <= tasks.size()) {
            Task task = tasks.get(taskIndex - 1);
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(" " + task.toString());
        } else {
            System.out.println("Invalid task index.");
        }
        printLine();
    }

    public void printToDoTaskException() {
        printLine();
        System.out.println("☹ OOPS!!! " +
                "The description of a todo cannot be empty.");
        printLine();
    }

    public void printDeleteTaskException() {
        printLine();
        System.out.println("Invalid input. Please use 'delete <number>'.");
        printLine();
    }

    public void printBlankArgumentError(String arg) {
        System.out.println("☹ OOPS!!! " + arg + " cannot be blank.");
        printLine();
    }

    public void markBlankIndexException() {
        printLine();
        System.out.println("Please specify the task number");
        printLine();
    }

    public void printTaskFoundMessage(String keyword) {
        System.out.println("These tasks contain the keyword " + keyword + ":");
    }

    public void printTaskWithNumber(int taskNo, Task task) {
        System.out.println((taskNo + 1) + ". " + task.toString());
    }

    public void printMissingKeyword(String keyword) {
        System.out.println(keyword + " keyword is missing");
        printLine();
    }
}
