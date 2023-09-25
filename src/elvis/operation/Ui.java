package elvis.operation;

import elvis.task.Deadline;
import elvis.task.Event;
import elvis.task.Task;
import java.util.ArrayList;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the user interface of the application.
 * Provides methods to interact with the user and display messages.
 */
public class Ui {
    private static final int NUMBER_OF_UNDERSCORES = 60;
    private static Scanner in = new Scanner(System.in);  //Scanner for Input
    private static final DateTimeFormatter STD_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Requests input from the user via the console.
     *
     * @return The user's input as a trimmed string.
     */
    public static String inputRequester() {
        return in.nextLine().trim();
    }

    /**
     * Prints an error message to the console.
     *
     * @param exception The exception that occurred.
     */
    public static void errorMessagePrinter(Exception exception) {
        System.out.println("An error occurred: " + exception.getMessage());
    }

    /**
     * Prints out appropriate message when checking if file exists
     */
    public static void checkFileMessagePrinter() {
        System.out.println("Checking if \"tasks.txt\" already exists...");
    }

    /**
     * Prints out appropriate message when no file found
     */
    public static void noFileMessagePrinter() {
        System.out.println("File does not exist.\nCreating new file...\nFile created successfully.");
    }

    /**
     * Prints out appropriate message when creation of new file failed
     */
    public static void fileCreationFailMessagePrinter() {
        System.out.println("File creation failed.");
    }

    /**
     * Prints out appropriate message when file already exists
     */
    public static void fileExistMessagePrinter() {
        System.out.println("File already exists.\nOpening existing file...\n");
    }

    /**
     * Prints out appropriate message when file is not found
     */
    public static void fileNotFoundMessagePrinter() {
        System.out.println("File not found.");
    }

    /**
     * Prints out appropriate message when tasks are loaded
     */
    public static void taskLoadedMessagePrinter() {
        System.out.println("These are tasks loaded from before: ");
    }

    /**
     * Prints out appropriate message when file is corrupt
     */
    public static void corruptFileMessagePrinter() {
        System.out.println("😭 File is corrupted.\nUnable to read file");
    }

    /**
     * Prints out appropriate message when help is requested
     */
    public static void helpRequestMessagePrinter() {
        System.out.println("No worries, I'm here to help!");
    }

    /**
     * Prints out appropriate message when input is empty
     *
     * @param description
     */
    public static void emptyDescriptionMessagePrinter(String description) {
        if (description == null) {
            System.out.println("😭 OOPS!!! The description cannot be empty.");
        } else if (description.equals("delete")) {
            System.out.println("😭 OOPS!!! The description of a delete cannot be empty.");
        } else if (description.equals("mark")) {
            System.out.println("😭 OOPS!!! The description of a mark cannot be empty.");
        } else if (description.equals("unmark")) {
            System.out.println("😭 OOPS!!! The description of an unmark cannot be empty.");
        }else if (description.equals("find")) {
            System.out.println("😭 OOPS!!! The description of a find cannot be empty.");
        } else if (description.equals("todo")) {
            System.out.println("😭 OOPS!!! The description of a todo cannot be empty.");
        } else if (description.equals("deadline")) {
            System.out.println("😭 OOPS!!! The description of a deadline cannot be empty.");
        } else if (description.equals("event")) {
            System.out.println("😭 OOPS!!! The description of an event cannot be empty.");
        }
    }

    /**
     * Prints out appropriate message when there is nothing to list
     */
    public static void emptyListMessagePrinter() {
        System.out.println("😭 OOPS!!! Nothing to list.");
    }

    /**
     * Prints out appropriate message when input is invalid
     */
    public static void unknownInputMessagePrinter() {
        System.out.println("😭 OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints out appropriate message when beginning to list
     */
    public static void listMessagePrinter() {
        System.out.println("Here are the tasks in your list: ");
    }

    /**
     * Prints out appropriate message when showing how many tasks are in array
     */
    public static void taskCountPrinter() {
        System.out.println("Now you have " + (TaskList.getArraySize()) + " task(s) in the list.");
    }

    /**
     * Prints out appropriate message when help is requested
     */
    public static void noSuchTaskMessagePrinter() {
        System.out.println("No such Task!");
    }

    /**
     * Prints out appropriate message when task is removed
     */
    public static void taskRemovedMessagePrinter() {
        System.out.println("Noted. I've removed this task:");
    }

    /**
     * Prints out appropriate message when task is marked
     */
    public static void taskMarkedMessagePrinter() {
        System.out.println("Nice! I've marked this task as done:");
    }

    /**
     * Prints out appropriate message when task is unmarked
     */
    public static void taskUnmarkedMessagePrinter() {
        System.out.println("OK, I've marked this task as not done yet:");
    }

    /**
     * Prints out appropriate message when invalid datetime input
     */
    public static void invalidDateTimeMessagePrinter() {
        System.out.println("😭 OOPS!!! Invalid Date/Time input");
    }

    /**
     * Prints a message to inform the user that no matching tasks were found.
     *
     * @param hasMatch       Indicates whether any matching tasks were found.
     * @param matchingTasks  A list of indices of matching tasks.
     */
    public static void findTaskMessagePrinter(boolean hasMatch, ArrayList<Integer> matchingTasks) {
        if (!hasMatch) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.print((i+1) + ".");
                print(i);
            }
        }
    }

    /**
     * Prints out appropriate message whenever task is added
     * Contents of the message depends on the type of task
     */
    public static void taskAddedMessagePrinter() {
        boolean isToDo = true;
        int arrayLastIndex = TaskList.getArraySize() - 1;
        char taskType = TaskList.getTaskType(arrayLastIndex);
        System.out.println("Got it. I've added this task:");
        System.out.print("[" + taskType + "]" +
                "[" + TaskList.getTaskStatus(arrayLastIndex) + "] " + TaskList.getTaskDescription(arrayLastIndex));

        if (taskType == 'D') {
            isToDo = false;
            LocalDateTime dateTime = TaskList.getTask(arrayLastIndex).getDateTime();
            System.out.println(" (by: " + dateTime.format(STD_FORMAT) + ")");
        } else if(taskType == 'E') {
            isToDo = false;
            LocalDateTime startDateTime = TaskList.getTask(arrayLastIndex).getStartDateTime();
            LocalDateTime endDateTime = TaskList.getTask(arrayLastIndex).getEndDateTime();
            System.out.println(" (from: " + startDateTime.format(STD_FORMAT) +
                    " to: " + endDateTime.format(STD_FORMAT) + ")");
        }

        if (isToDo) {   //If "todo" is printed last, need additional line separator
            System.out.print(System.lineSeparator());
        }
        System.out.println("Now you have " + (TaskList.getArraySize()) + " task(s) in the list.");
    }

    /**
     * Prints task details to the console, including type, status, description, and, if applicable,
     * date and time information.
     * Prints task eg "[T][X] read book" without the preceding index eg "1."
     *
     * @param nthTask The index of the task to print.
     */
    public static void print(int nthTask) {
        boolean isToDo = true;
        Task task = TaskList.getTask(nthTask);
        System.out.print("[" + TaskList.getTaskType(nthTask) + "]");
        System.out.print("[" + TaskList.getTaskStatus(nthTask) + "] " + TaskList.getTaskDescription(nthTask));

        // Additional details required for Deadline and Event
        if (task instanceof Deadline) {
            isToDo = false;
            Deadline deadlineTask = (Deadline) task;
            System.out.println(" (by: " + deadlineTask.getDateTime().format(STD_FORMAT) + ")");
        } else if (task instanceof Event) {
            isToDo = false;
            Event eventTask = (Event) task;
            System.out.println(" (from: " + eventTask.getStartDateTime().format(STD_FORMAT) +
                    " to: " + eventTask.getEndDateTime().format(STD_FORMAT) + ")");
        }

        if (isToDo) {   //If "todo" is printed last, need additional line separator
            System.out.print(System.lineSeparator());
        }
    }

    /**
     * Prints horizontal lines made up of underscores to the console.
     */
    public static void printHorizontalLines() {
        for (int i = 0; i < NUMBER_OF_UNDERSCORES; i++) {
            System.out.print("_");
        }
        System.out.print(System.lineSeparator());
    }
}
