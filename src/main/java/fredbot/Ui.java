package fredbot;

import fredbot.task.Task;

import java.util.Scanner;
import java.util.Set;

/**
 * Represent a class for printing to standard output on terminal
 * and reading input
 */
public class Ui {
    public static final String INDENT = "    ";
    public static final String DIVIDER = "    ____________________________________________________________\n";
    public static final String GREETING = "     Hello! I'm Fredbot\n" +
            "     What can I do for you?";
    public static final String MARK_TASK_MESSAGE = "Nice! I've marked this task as done:\n";
    static final String UNMARK_TASK_MESSAGE = "Nice! I've marked this task as not done yet:\n";
    public static final String TASK_LIST_MESSAGE = "Here are the tasks in your list\n";
    public static final String REMOVE_TASK_MESSAGE = "Noted. I've removed this task:\n";
    public static final String FAREWELL = "     Bye. Hope to see you again soon!";
    public static final String TASK_FOUND_MESSAGE = "Here are the matching tasks in your list:\n";
    public static final String ADD_TASK_MESSAGE = "    You have successfully added the task:\n";

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a message with lines as dividers given a specified string
     * @param message string specified to be printed with lines
     */
    public void printMessage(String message) {
        System.out.print(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }
    public void showWelcome() {
        printMessage(INDENT + GREETING);
    }

    public void showGoodBye() {
        printMessage(FAREWELL);
    }

    /**
     * Prints the task that is added to stdout with a message at the bottom showing the number of task currently
     * @param tasks an object of the tasklist class representing the current tasklist
     */
    public void printAddTask(TaskList tasks) {
        printMessage(ADD_TASK_MESSAGE + INDENT + INDENT + tasks.getTask(Task.getNumTask() - 1).toString() +
                System.lineSeparator() + INDENT + "Now you have " + (Task.getNumTask()) + " tasks in the list\n");
    }

    /**
     * Prints the task that is removed to stdout with a message at the bottom showing the number of task currently
     * @param task an object of the Task class representing the removed task
     */
    public void printRemoveTask(Task task) {
        printMessage(INDENT + REMOVE_TASK_MESSAGE + INDENT + task.toString() + System.lineSeparator() +
                INDENT + "Now you have " + (Task.getNumTask()) + " tasks in the list\n");
    }

    /**
     * Prints the task that is marked to stdout with a message at the top saying that it is marked/unmarked
     * @param task an object of the Task class representing the marked/unmarked task
     */
    public void printMarkTask(Task task) {
        String message;
        if (task.getDone()) {
            message = INDENT + MARK_TASK_MESSAGE;
            message += INDENT + "[X] " + task.getTaskDesc();
        } else {
            message = INDENT + UNMARK_TASK_MESSAGE;
            message += INDENT + "[ ] " + task.getTaskDesc();
        }
        printMessage(message);
    }

    /**
     * Print all task to the stdout with a visual index given to each task
     * @param tasks an object of tasklist class that contains all the current tasks
     */
    public void printAllTasks(TaskList tasks) {
        StringBuilder taskList = new StringBuilder();
        taskList.append(INDENT).append(TASK_LIST_MESSAGE);
        int numTask = Task.getNumTask();
        for (int i = 0; i < numTask; i++) {
            String number = (i + 1) + ".";
            taskList.append(INDENT).append(number).append(tasks.getTask(i).toString()).append("\n"); // Can be formatted
        }
        printMessage(taskList.toString());
    }

    public void printFoundTasks(TaskList tasks, Set<Integer> set) {
        StringBuilder taskList = new StringBuilder();
        taskList.append(INDENT).append(TASK_FOUND_MESSAGE);
        for (Integer value: set) {
            String number = (value + 1) + ".";
            taskList.append(INDENT).append(number).append(tasks.getTask(value).toString()).append("\n");
        }
        printMessage(taskList.toString());
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String ErrorMessage) {
        printMessage(INDENT + ErrorMessage);
    }

    public void showFatalError(String ErrorMessage) {
        printMessage(INDENT + "Fatal Error" + ErrorMessage);
    }
}
