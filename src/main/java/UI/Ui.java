package UI;

import task.Task;
import java.util.ArrayList;

public class Ui {
    public static final String line = "____________________________________________________________";
    public static final String logo =
            "\t   _____ _____ __  __  ____  _   _ \n" +
            "\t  / ____|_   _|  \\/  |/ __ \\| \\ | |\n" +
            "\t | (___   | | | \\  / | |  | |  \\| |\n" +
            "\t  \\___ \\  | | | |\\/| | |  | | . ` |\n" +
            "\t  ____) |_| |_| |  | | |__| | |\\  |\n" +
            "\t |_____/|_____|_|  |_|\\____/|_| \\_|\n" +
            "\t                                   \n";


    public void printLine() {
        System.out.println("\t" + line);
    }
    public void printGreeting() {
        printLine();
        System.out.println("\t" + "Hello I'm\n" + logo);
        System.out.println("\t" + "What can I do for you?");
        printLine();
    }

    public void  printUnknownInputMessage() {
        System.out.println("\t" + " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void printFarewell() {
        System.out.println("\t" + "Bye. Hope to see you again soon!");
    }

    public void printList(ArrayList<Task> tasks, Ui ui) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < Task.getNumberOfTask(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i));
        }
    }

    public void printAddTaskMessage(ArrayList<Task> tasks) {
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t  " + tasks.get(Task.getNumberOfTask() - 1));
    }

    public void printNumberOfTasks(ArrayList<Task> tasks) {
        System.out.println("\tNow you have " + Task.getNumberOfTask() + " tasks in the list.");
    }

    public void printMissingFileError() {
        printLine();
        System.out.println("\tsimon.txt does not exist, creating simon.txt in 'data' folder");
        printLine();
    }

    public void printMarkAsDone(Task task) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  [X] " + task.getDescription());
    }

    public void printUnmarkAsDone(Task task) {
        System.out.println("\tOkay, I've marked this task as not done yet:");
        System.out.println("\t  [ ] " + task.getDescription());
    }

    public void printDeleteTask(Task task) {
        System.out.println("\tNoted. I've removed this task: ");
        System.out.println("\t  " + task);
    }

    public void printOutOfBoundsError(ArrayList<Task> task) {
        System.out.println("\tSorry! The task number inputted is out of bounds");
        System.out.println("\tPlease key in a number from 1-" + Task.getNumberOfTask());
    }

    public void printEmptyDescriptionError(String task) {
        System.out.println("\t☹ OOPS!!! The description of a" + task + " cannot be empty.");
    }

    public void printEventFormat() {
        System.out.println("\tPlease include when the time of your event in the following format:");
        System.out.println("\tevent [description] /from [start time] /to [end time]");
    }

    public void printDeadlineFormat() {
        System.out.println("\tPlease include when the deadline of your task is in the following format:");
        System.out.println("\tdeadline [description] /by [deadline]");
    }
}
