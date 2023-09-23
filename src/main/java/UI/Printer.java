package UI;

import task.Task;
import java.util.ArrayList;

public class Printer {
    public static final String line = "____________________________________________________________";
    public static final String logo =
            "\t   _____ _____ __  __  ____  _   _ \n" +
            "\t  / ____|_   _|  \\/  |/ __ \\| \\ | |\n" +
            "\t | (___   | | | \\  / | |  | |  \\| |\n" +
            "\t  \\___ \\  | | | |\\/| | |  | | . ` |\n" +
            "\t  ____) |_| |_| |  | | |__| | |\\  |\n" +
            "\t |_____/|_____|_|  |_|\\____/|_| \\_|\n" +
            "\t                                   \n";


    public static void printGreeting() {
        System.out.println("\t" + line);
        System.out.println("\t" + "Hello I'm\n" + logo);
        System.out.println("\t" + "What can I do for you?");
        System.out.println("\t" + line);
    }

    public static void  printUnknownInputMessage() {
        System.out.println("\t" + line);
        System.out.println("\t" + " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println("\t" + line);
    }

    public static void printFarewell() {
        System.out.println("\t" + line);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("\t" + line);
    }

    public static void printList(ArrayList<Task> tasks) {
        System.out.println("\t" + line);
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < Task.getNumberOfTask(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("\t" + line);
    }

    public static void printAddTaskMessage(ArrayList<Task> tasks) {
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t  " + tasks.get(Task.getNumberOfTask() - 1));
    }

    public static void printNumberOfTasks(ArrayList<Task> tasks) {
        System.out.println("\tNow you have " + Task.getNumberOfTask() + " tasks in the list.");
    }
}
