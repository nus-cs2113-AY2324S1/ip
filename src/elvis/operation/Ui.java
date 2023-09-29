package elvis.operation;

import elvis.task.Deadline;
import elvis.task.Event;
import elvis.task.Task;

import java.util.Scanner;

/**
 * This is the Ui class that is in-charge of interaction with the user
 * Prints out appropriate messages such as errors and tasks added
 */
public class Ui {
    private static final int NUMBER_OF_UNDERSCORES = 60;
    private static Scanner in = new Scanner(System.in);  //Scanner for Input

    public static String inputRequester() {
        return in.nextLine().trim();
    }

    public static void errorMessagePrinter(Exception exception) {
        System.out.println("An error occurred: " + exception.getMessage());
    }

    public static void checkFileMessagePrinter() {
        System.out.println("Checking if \"tasks.txt\" already exists...");
    }

    public static void noFileMessagePrinter() {
        System.out.println("File does not exist.\nCreating new file...\nFile created successfully.");
    }

    public static void fileCreationFailMessagePrinter() {
        System.out.println("File creation failed.");
    }

    public static void fileExistMessagePrinter() {
        System.out.println("File already exists.\nOpening existing file...\n");
    }

    public static void fileNotFoundMessagePrinter() {
        System.out.println("File not found.");
    }

    public static void taskLoadedMessagePrinter() {
        System.out.println("These are tasks loaded from before: ");
    }

    public static void corruptFileMessagePrinter() {
        System.out.println("😭 File is corrupted.\tUnable to read file");
    }

    public static void helpRequestMessagePrinter() {
        System.out.println("No worries, I'm here to help!");
    }

    public static void emptyDescriptionMessagePrinter(String description) {
        if (description == null) {
            System.out.println("😭 OOPS!!! The description cannot be empty.");
        } else if (description.equals("delete")) {
            System.out.println("😭 OOPS!!! The description of a delete cannot be empty.");
        } else if (description.equals("mark")) {
            System.out.println("😭 OOPS!!! The description of a mark cannot be empty.");
        } else if (description.equals("unmark")) {
            System.out.println("😭 OOPS!!! The description of an unmark cannot be empty.");
        } else if (description.equals("todo")) {
            System.out.println("😭 OOPS!!! The description of a todo cannot be empty.");
        } else if (description.equals("deadline")) {
            System.out.println("😭 OOPS!!! The description of a deadline cannot be empty.");
        } else if (description.equals("event")) {
            System.out.println("😭 OOPS!!! The description of an event cannot be empty.");
        }
    }

    public static void emptyListMessagePrinter() {
        System.out.println("😭 OOPS!!! Nothing to list.");
    }

    public static void unknownInputMessagePrinter() {
        System.out.println("😭 OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void listMessagePrinter() {
        System.out.println("Here are the tasks in your list: ");
    }

    public static void noSuchTaskMessagePrinter() {
        System.out.println("No such Task!");
    }

    public static void taskRemovedMessagePrinter() {
        System.out.println("Noted. I've removed this task:");
    }

    public static void taskMarkedMessagePrinter() {
        System.out.println("Nice! I've marked this task as done:");
    }

    public static void taskUnmarkedMessagePrinter() {
        System.out.println("OK, I've marked this task as not done yet:");
    }

    public static void taskAddedMessagePrinter() {
        boolean isToDo = true;
        int arrayLastIndex = TaskList.getArraySize() - 1;
        char taskType = TaskList.getTaskType(arrayLastIndex);
        System.out.println("Got it. I've added this task:");
        System.out.print("[" + taskType + "]" +
                "[" + TaskList.getTaskStatus(arrayLastIndex) + "] " + TaskList.getTaskDescription(arrayLastIndex));
        if (taskType == 'D') {
            isToDo = false;
            System.out.println(" (by: " + TaskList.getTask(arrayLastIndex).getDate() + ")");
        } else if(taskType == 'E') {
            isToDo = false;
            System.out.println(" (from: " + TaskList.getTask(arrayLastIndex).getStartTime() + " to: " +
                    TaskList.getTask(arrayLastIndex).getEndTime() + ")");
        }
        if (isToDo) {   //If "todo" is printed last, need additional line separator
            System.out.print(System.lineSeparator());
        }
        System.out.println("Now you have " + (TaskList.getArraySize()) + " task(s) in the list.");
    }

    //Prints task eg "[T][X] read book" without the preceding index eg"1."
    public static void print(int nthTask) {
        boolean isToDo = true;
        Task task = TaskList.getTask(nthTask);
        System.out.print("[" + TaskList.getTaskType(nthTask) + "]");
        System.out.print("[" + TaskList.getTaskStatus(nthTask) + "] " + TaskList.getTaskDescription(nthTask));

        // Additional details required for Deadline and Event
        if (task instanceof Deadline) {
            isToDo = false;
            Deadline deadlineTask = (Deadline) task;
            System.out.println(" (by: " + deadlineTask.getDate() + ")");
        } else if (task instanceof Event) {
            isToDo = false;
            Event eventTask = (Event) task;
            System.out.println(" (from: " + eventTask.getStartTime() + " to: " + eventTask.getEndTime() + ")");
        }

        if (isToDo) {   //If "todo" is printed last, need additional line separator
            System.out.print(System.lineSeparator());
        }
    }

    public static void taskCountPrinter() {
        System.out.println("Now you have " + (TaskList.getArraySize()) + " task(s) in the list.");
    }

    public static void printHorizontalLines() {
        for (int i = 0; i < NUMBER_OF_UNDERSCORES; i++) {
            System.out.print("_");
        }
        System.out.print(System.lineSeparator());
    }
}
